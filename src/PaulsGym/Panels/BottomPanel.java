package PaulsGym.Panels;

import java.awt.Point;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import Defaults.Sizes;
import Defaults.Colors;
import Defaults.Energy;
import Defaults.Exercise;
import Defaults.Fonts;
import PaulsGym.MyEnergyBar;
import PaulsGym.MyLabel;
import java.util.Timer;
import java.util.TimerTask;

public class BottomPanel extends javax.swing.JPanel {
    
    private MyEnergyBar energyBar;
    private MyLabel upperBodyStrengthLabel;
    private MyLabel lowerBodyStrengthLabel;
    private MyLabel totalStrengthLabel;
    private MyLabel timerLabel;
    private Timer timer;
    private int timePlayed;
    
    public BottomPanel() {
        overrideProgressColor();
        initComponents();
        myInitComponents();
    }
    
    // My Components
    private void myInitComponents() {
        
        timePlayed = 0;
        
        // Creates the three strength labels
        upperBodyStrengthLabel = new MyLabel("Upper Body Strength: ", Sizes.LONG_LABEL, new Point(900, 50), Fonts.BOLD, 0);
        lowerBodyStrengthLabel = new MyLabel("Lower Body Strength: ", Sizes.LONG_LABEL, new Point(900, 100), Fonts.BOLD, 0);
        totalStrengthLabel = new MyLabel("Total Strength: ", Sizes.LONG_LABEL, new Point(900, 150), Fonts.BOLD, 0);
        
        // Creates the label for the timer
        timerLabel = new MyLabel("Workout Duration: ", Sizes.LONG_LABEL, new Point(500, 100), Fonts.BOLD);
        
        // Creats the energy bar
        energyBar = new MyEnergyBar(new Point(100,100), Sizes.NORMAL_ENERGYBAR);
        
        // Adds all the labels to the panel
        this.add(lowerBodyStrengthLabel);
        this.add(upperBodyStrengthLabel);
        this.add(totalStrengthLabel);
        this.add(timerLabel);
        
        // Adds the energy bar to the panel
        this.add(energyBar);
        
  
        // Creats a timer that regenerates a certain amount of energy every how ever many seconds
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if (energyBar.getEnergy() <= Energy.STARTING_ENERGY - Energy.REGEN_RATE) {
                   energyBar.updateEnergy(-Energy.REGEN_RATE);
               } else {
                   energyBar.resetEnergy();
               }
            }
        }, 0, Energy.REGEN_PERIOD);
        
        // Shows the user how long they have been working out
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timePlayed += 1;
                if (timePlayed < 60) {
                    if (timePlayed < 10) {
                        timerLabel.addText(" 0:0" + timePlayed);
                    } else {
                        timerLabel.addText(" 0:" + timePlayed);
                    }
                } else {
                    if (timePlayed % 60 < 10) {
                        timerLabel.addText((timePlayed / 60) + ":0" + timePlayed % 60);
                    } else {
                        timerLabel.addText((timePlayed / 60) + ":" + timePlayed % 60);
                    }
                }
            }
        }, 1000, 1000);
        
        
    }
    
    // Overrides a super ugly default color for the energy bar that makes it orange
    private void overrideProgressColor() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("nimbusOrange", Colors.BLUE);
    }
    
    // Updates the strength labels with their new values after doing a workout
    public void updateStrengthLabels(Exercise exercise, int strengthLevel) {
        
        if (exercise.getName() == Exercise.BENCH) {
            upperBodyStrengthLabel.addToStrengthLevel(strengthLevel);
        } else if (exercise.getName() == Exercise.SQUAT) {
            lowerBodyStrengthLabel.addToStrengthLevel(strengthLevel);
        } else if (exercise.getName() == Exercise.DEADLIFT) {
            upperBodyStrengthLabel.addToStrengthLevel(strengthLevel / 2);
            lowerBodyStrengthLabel.addToStrengthLevel(strengthLevel / 2);
        }
        
        int totalStrength = upperBodyStrengthLabel.getNumber() + lowerBodyStrengthLabel.getNumber();
        
        totalStrengthLabel.setStrengthLevel(totalStrength);
    }
    
    // Energy bar functions
    public void updateEnergyBar(int energy) {
        energyBar.updateEnergy(energy);
    }
    
    public void resetEnergyBar() {
        energyBar.resetEnergy();
    }
    
    // Getters
    public int getUpperBodyStrengthLevel() {
        return upperBodyStrengthLabel.getNumber();
    }
    
    public int getLowerBodyStrengthLevel() {
        return lowerBodyStrengthLabel.getNumber();
    }
    
    public int getTotalStrengthLevel() {
        return totalStrengthLabel.getNumber();
    }

    public Timer getTimer() {
        return timer;
    }
    
    public int getEnergy() {
        return energyBar.getEnergy();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(Defaults.Sizes.BOTTOM_PANEL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
