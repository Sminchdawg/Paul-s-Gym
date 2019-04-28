package PaulsGym;
import Defaults.Energy;
import Defaults.Exercise;
import Defaults.MaxPercent;
import PaulsGym.Panels.StartPanel;
import PaulsGym.Panels.GymPanel;
import Defaults.Sizes;
import Defaults.Strength;
import Defaults.Workout;
import PaulsGym.Panels.BottomPanel;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends javax.swing.JFrame{
    private StartPanel startPanel;
    private GymPanel gymPanel;
    private BottomPanel bottomPanel;
    private Exercise exercise;
    private Workout workout;
    
    public MainFrame() {
        initComponents();
        myInitComponents();
    }
    
    // My components
    public void myInitComponents(){
        // Adds the start panel to the board with an action that creates the gym screen on press
        startPanel = new StartPanel(new StartPanelActions(){
        @Override
        public void enterGym() {
            createGym();
        }
        });
        startPanel.setSize(Sizes.START_PANEL);
        startPanel.setLocation(0,0);
        this.add(startPanel);
    }
    
    
    // This generates the gym screen where the user will spend most of their time
    private void createGym() {
        startPanel.setVisible(false);
        
        // Generates the gym panel with a ton of different actions that the gympanel has to have since it is making a new GymPanelActions
        gymPanel = new GymPanel(new GymPanelActions() {
            
            // These are the main three exercise button actions
            @Override
            public void performBench() {
                exercise = new Exercise(Exercise.BENCH, 0);
            }

            @Override
            public void performSquat() {
                exercise = new Exercise(Exercise.SQUAT, 1);
            }

            @Override
            public void performDeadlift() {
                exercise = new Exercise(Exercise.DEADLIFT, 2);
            }
            
            // This is an action that the three exercises share
            @Override
            public void combinedExerciseAction() {
                gymPanel.shouldShowOptions(true);
                gymPanel.setGreen(exercise);
                gymPanel.shouldShowMaxOptions(false);
            }
            
            /*
                This is an action that all three exercises uses
                It checks all the low, min, and max and lets the user know if 
                they dont have enough energy, by setting the button red
            */
            @Override
            public void notEnoughEnergyAction() {
                for (int i = 0; i < 3; i++) {
                    if (!enoughEnergy(Energy.WORKOUT_ENERGY.get(i))) {
                        gymPanel.setRed(new Workout("Default", i));
                    }
                }
            }

            // This is the action for eating the tuna sandwich
            @Override
            public void eatTunaSandwich() {
                bottomPanel.resetEnergyBar();
                gymPanel.setOptionsDefault();
            }

            /*
                These three are the actions for after an exercise is clicked
                They are the different levels of a workout that the user can go for
            */
            @Override
            public void lightWorkout() {
                workout = new Workout(Workout.LIGHT_WORKOUT, 0);
                if (enoughEnergy(Energy.LIGHT_WORKOUT)) {
                    bottomPanel.updateEnergyBar(Energy.LIGHT_WORKOUT);
                    bottomPanel.updateStrengthLabels(exercise, Strength.LIGHT_WORKOUT);
                    
                    gymPanel.shouldShowOptions(false);
                    gymPanel.setExerciseDefault();
                }
            }

            @Override
            public void normalWorkout() {
                workout = new Workout(Workout.NORMAL_WORKOUT, 1);
                if (enoughEnergy(Energy.NORMAL_WORKOUT)) {
                    bottomPanel.updateEnergyBar(Energy.NORMAL_WORKOUT);
                    bottomPanel.updateStrengthLabels(exercise, Strength.NORMAL_WORKOUT);
                    
                    gymPanel.shouldShowOptions(false);
                    gymPanel.setExerciseDefault();
                }   
            }

            @Override
            public void maxWorkout() {
                workout = new Workout(Workout.MAX_WORKOUT, 2);
                 if (enoughEnergy(Energy.MAX_WORKOUT)) {
                    gymPanel.shouldShowOptions(false);
                    
                    gymPanel.setExerciseDefault();
                    gymPanel.shouldShowMaxOptions(true);
                    
                    gymPanel.setMaxButtons(calculateMaxes());
                }
            }

            /*
                These are the three options for after the max button is clicked
                They are the different maxes that the user can go for
                Each one has a different percentage chance of being successful and they give more strength accordingly
            */
            @Override
            public void lowMax() {
                if(attemptMax(MaxPercent.LOW_MAX_PERCENT)) {
                    gymPanel.updateMax(exercise, 0);
                    bottomPanel.updateStrengthLabels(exercise, Strength.LOW_MAX_WORKOUT);
                    gymPanel.maxSuccessful(true);
                } else {
                    bottomPanel.updateStrengthLabels(exercise, Strength.LOW_MAX_WORKOUT / 2);
                    gymPanel.maxSuccessful(false);
                }
            }

            @Override
            public void mediumMax() {
                if(attemptMax(MaxPercent.MEDIUM_MAX_PERCENT)) {
                    gymPanel.updateMax(exercise, 1);
                    bottomPanel.updateStrengthLabels(exercise, Strength.MEDIUM_MAX_WORKOUT);
                    gymPanel.maxSuccessful(true);
                } else {
                    bottomPanel.updateStrengthLabels(exercise, Strength.MEDIUM_MAX_WORKOUT / 2);
                    gymPanel.maxSuccessful(false);
                }
            }

            @Override
            public void highMax() {
                if(attemptMax(MaxPercent.HIGH_MAX_PERCENT)) {
                    gymPanel.updateMax(exercise, 2);
                    bottomPanel.updateStrengthLabels(exercise, Strength.HIGH_MAX_WORKOUT);
                    gymPanel.maxSuccessful(true);
                } else {
                    bottomPanel.updateStrengthLabels(exercise, Strength.HIGH_MAX_WORKOUT / 2);
                    gymPanel.maxSuccessful(false);
                }
            }

            /*
                These are common actions for the three max options buttons
            */
            @Override
            public void combinedMaxOptionsAction() {
                bottomPanel.updateEnergyBar(Energy.MAX_WORKOUT);
                gymPanel.shouldShowMaxOptions(false);
            }
        });
        
        // Adds in the panels to a location and sets their sizes
        gymPanel.setSize(Sizes.GYM_PANEL);
        gymPanel.setLocation(0,0);
        this.add(gymPanel);
        
        bottomPanel = new BottomPanel();
        bottomPanel.setSize(Sizes.BOTTOM_PANEL);
        bottomPanel.setLocation(0, 700);
        this.add(bottomPanel);
        
        updateExerciseOptionsButtonsColor();
    }
    
    // Checks to see if there is enough energy to do a workout
    private boolean enoughEnergy(int energy) {
        boolean enoughEnergy = false;
        
        if (bottomPanel.getEnergy() >= energy) {
            enoughEnergy = true;
        }
        
        return enoughEnergy;
    }
    
    // Calculates the values to put on the Max Buttons if max workout is selected
    private List calculateMaxes() {
        List maxes = new LinkedList();
        
        float lowMax = Math.round((bottomPanel.getUpperBodyStrengthLevel() * (1 - MaxPercent.LOW_MAX_PERCENT)));
        float mediumMax = Math.round((bottomPanel.getUpperBodyStrengthLevel() * (1 - MaxPercent.MEDIUM_MAX_PERCENT)));
        float highMax = Math.round((bottomPanel.getUpperBodyStrengthLevel() * (1 - MaxPercent.HIGH_MAX_PERCENT)));

        maxes.add((int)lowMax);
        maxes.add((int)mediumMax);
        maxes.add((int)highMax);
        
        return maxes;
    }
    
    // Returns true or value with a percent chance based on low, mid, or high max
    private boolean attemptMax(double maxPercent) {
        Random rand = new Random();
        int number = rand.nextInt(100);
        boolean attemptMax = false;
        
        if (number > (100 - ((int)(maxPercent * 100)))){
            attemptMax = true;
        }
        return attemptMax;
    }
    
    /*
        Returns the button colors back to normal if enough energy is available to perform the workout
        Checks everytime the energy is increased on the energy bar
    */
    private void updateExerciseOptionsButtonsColor() {
        Timer timer;
        timer = bottomPanel.getTimer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               for (int i = 0; i < 3; i++) {
                   if (enoughEnergy(Energy.WORKOUT_ENERGY.get(i))) {
                        gymPanel.setOneOptionDefault(i);
                    }
               }
            }
        },0, Energy.REGEN_RATE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Sizes.MAINFRAME);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1212, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
