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
    
    private void myInitComponents() {
        timePlayed = 0;
        
        upperBodyStrengthLabel = new MyLabel("Upper Body Strength: ", Sizes.LONG_LABEL, new Point(900, 50), Fonts.BOLD, 0);
        this.add(upperBodyStrengthLabel);
        
        lowerBodyStrengthLabel = new MyLabel("Lower Body Strength: ", Sizes.LONG_LABEL, new Point(900, 100), Fonts.BOLD, 0);
        this.add(lowerBodyStrengthLabel);
        
        totalStrengthLabel = new MyLabel("Total Strength: ", Sizes.LONG_LABEL, new Point(900, 150), Fonts.BOLD, 0);
        this.add(totalStrengthLabel);
        
        timerLabel = new MyLabel("Workout Duration: ", Sizes.LONG_LABEL, new Point(500, 100), Fonts.BOLD);
        this.add(timerLabel);
        
        energyBar = new MyEnergyBar(new Point(100,100), Sizes.NORMAL_ENERGYBAR);
        this.add(energyBar);
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if (energyBar.getEnergy() <= Energy.DEFAULT_ENERGY - Energy.REGEN_RATE) {
                   energyBar.updateEnergy(-Energy.REGEN_RATE);
               } else {
                   energyBar.resetEnergy();
               }
            }
        }, 0, Energy.REGEN_PERIOD);
        
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
    
    private void overrideProgressColor() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("nimbusOrange", Colors.BLUE);
    }
    
    public void updateEnergyBar(int energy) {
        energyBar.updateEnergy(energy);
    }
    
    public void resetEnergyBar() {
        energyBar.resetEnergy();
    }
    
    public int getEnergy() {
        return energyBar.getEnergy();
    }
    
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
    
    public int getUpperBodyStrengthLevel() {
        return upperBodyStrengthLabel.getNumber();
    }

    public Timer getTimer() {
        return timer;
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
