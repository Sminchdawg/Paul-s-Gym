package PaulsGym.Panels;

import Defaults.Colors;
import Defaults.Exercise;
import Defaults.Fonts;
import Defaults.MaxPercent;
import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import Defaults.Sizes;
import Defaults.Workout;
import PaulsGym.MyButton;
import PaulsGym.MyLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import PaulsGym.GymPanelActions;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Mitchell's Laptop
 */
public class GymPanel extends javax.swing.JPanel {
    
    private GymPanelActions actions;
    private List<MyButton> exerciseButtons;
    private List<MyButton> optionsButtons;
    private List<MyLabel> exerciseLabels;
    private List<MyLabel> maxWeightLabels;
    private List<MyButton> maxOptionsButtons;
    private MyLabel maxSuccessfulLabel;
    
    public GymPanel(GymPanelActions actions) {
        
        this.actions = actions;
        initComponents();
        myInitComponents();
        
    }
    
    private void myInitComponents() {
        
         exerciseButtons = new LinkedList();
         optionsButtons = new LinkedList();
         exerciseLabels = new LinkedList();
         maxWeightLabels = new LinkedList();
         maxOptionsButtons = new LinkedList();
        
         
        // Adds each exercise button to the list
        exerciseButtons.add(
                        new MyButton(
                            new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    actions.performBench();
                                    actions.combinedExerciseAction();
                                    actions.notEnoughEnergyAction();
                                }
                            },
                            "Bench Press", 
                            Sizes.NORMAL_SQUARE_BUTTON,
                            new javax.swing.ImageIcon(getClass().getResource("/PaulsGym/Pictures/benchPress.png")), 
                            new Point(200,100))
                        );
        
        exerciseButtons.add(
                        new MyButton(
                            new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    actions.performSquat();
                                    actions.combinedExerciseAction();
                                    actions.notEnoughEnergyAction();
                                }
                            },
                            "Squat", 
                            Sizes.NORMAL_SQUARE_BUTTON,
                            new javax.swing.ImageIcon(getClass().getResource("/PaulsGym/Pictures/squat.png")),
                            new Point(500,100))
                        );
        
        exerciseButtons.add(
                        new MyButton(
                            new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    actions.performDeadlift();
                                    actions.combinedExerciseAction();
                                    actions.notEnoughEnergyAction();
                                }
                            },
                            "DeadLift", 
                            Sizes.NORMAL_SQUARE_BUTTON,
                            new javax.swing.ImageIcon(getClass().getResource("/PaulsGym/Pictures/deadLift.png")),
                            new Point(800,100))
                        );
        
        // The tuna sandwich button gets initialized
        MyButton tunaSandwichButton = 
                new MyButton(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            actions.eatTunaSandwich();
                        }
                    },
                    "Tuna Sandwich",
                    Sizes.NORMAL_SQUARE_BUTTON,
                    new javax.swing.ImageIcon(getClass().getResource("/PaulsGym/Pictures/tunaSandwich.png")),
                    new Point(500, 400)
                );
        
        // Adds the workout option buttons to a list
        optionsButtons.add(new MyButton(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.lightWorkout();
                    }
                },
                "Light Workout",
                new Point(200, 400),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.NORMAL
        ));
        
        optionsButtons.add(new MyButton(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.normalWorkout();
                    }
                },
                "Normal Workout",
                new Point(200, 475),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.NORMAL
        ));
        
        optionsButtons.add(new MyButton(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.maxWorkout();
                    }
                },
                "Go for Max",
                new Point(200, 550),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.NORMAL
        ));
        
        // Adds the option buttons for the max workout to a list
        maxOptionsButtons.add(new MyButton (
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.lowMax();
                        actions.combinedMaxOptionsAction();
                    }
                },
                "Low Max",
                new Point(200, 400),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.SMALL
        ));
        
        maxOptionsButtons.add(new MyButton (
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.mediumMax();
                        actions.combinedMaxOptionsAction();
                    }
                },
                "Medium Max",
                new Point(200, 475),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.SMALL
        ));
        
        maxOptionsButtons.add(new MyButton (
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actions.highMax();
                        actions.combinedMaxOptionsAction();
                    }
                },
                "High Max",
                new Point(200, 550),
                Sizes.SMALL_RECTANGLE_BUTTON,
                Fonts.SMALL
        ));
        
        // Adds all the buttons to the panel
        for (MyButton exerciseButton: exerciseButtons) {
            this.add(exerciseButton);
        }
        
        for (MyButton optionButton: optionsButtons) {
            this.add(optionButton);
        }
        
        for (MyButton maxOptionButton: maxOptionsButtons) {
            this.add(maxOptionButton);
        }
        
        this.add(tunaSandwichButton);
        
        // Adds the labels for the exercises to a list
        exerciseLabels.add(new MyLabel("Bench", Sizes.NORMAL_LABEL, new Point(270, 25), Fonts.BOLD));
        exerciseLabels.add(new MyLabel("Squat", Sizes.NORMAL_LABEL, new Point(570, 25), Fonts.BOLD));
        exerciseLabels.add(new MyLabel("Deadlift", Sizes.NORMAL_LABEL, new Point(870, 25), Fonts.BOLD));
        
        // Adds the labels the max weight to a list
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(270, 275), Fonts.NORMAL, 0));
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(570, 275), Fonts.NORMAL, 0));
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(870, 275), Fonts.NORMAL, 0));
        
        // Creates a label for displaying if the max was successful or not
        maxSuccessfulLabel = new MyLabel("", Sizes.BIG_LABEL, new Point(750,350),Fonts.HEADER);
       
        // Adds all the labels to the panel
        for (MyLabel exerciseLabel: exerciseLabels) {
            this.add(exerciseLabel);
        }
        
        for (MyLabel maxWeightLabel: maxWeightLabels) {
            this.add(maxWeightLabel);
        }
        
        this.add(maxSuccessfulLabel);
        
        
        // Hides some stuff that need an action to be shown
        shouldShowOptions(false);
        shouldShowMaxOptions(false);
        maxSuccessfulLabel.setVisible(false);
    }
    
    // Hides or shows the workout options based on argument passed in
    public void shouldShowOptions(boolean shouldShow) {
        for (MyButton optionButton: optionsButtons) {
            optionButton.setVisible(shouldShow);
        }
    }
    
    // Hides or shows the max options based on argument passed in
    public void shouldShowMaxOptions(boolean shouldShow) {
        for (MyButton maxOptionButton: maxOptionsButtons) {
            maxOptionButton.setVisible(shouldShow);
        }
    }
    
    // Sets all the exercise buttons back to the default button color
    public void setExerciseDefault() {
        for (MyButton exerciseButton: exerciseButtons) {
            exerciseButton.setDefault();
        }
    }
    
    // Sets all the workout options buttons to the default button color
    public void setOptionsDefault() {
        for (MyButton optionsButton: optionsButtons) {
            optionsButton.setDefault();
        }
    }
    
    // Sets one of the workout options button to default
    public void setOneOptionDefault(int index) {
        optionsButtons.get(index).setDefault();
    }
    
    // Sets a workout button to green if it is clicked
    public void setGreen(Exercise exercise) {
        MyButton exerciseButton;
        exerciseButton = exerciseButtons.get(exercise.getIndex());
        
        setExerciseDefault();
        
        exerciseButton.setColor(Colors.GREEN);
    }

    // Sets the text on the max options buttons
    public void setMaxButtons(List<Integer> maxes) {
        for (int i = 0; i < maxOptionsButtons.size(); i ++) {
            maxOptionsButtons.get(i).setMax(maxes.get(i));
            maxOptionsButtons.get(i).addText(" " + maxes.get(i) + " (" + (int)(100 * MaxPercent.PERCENTS.get(i)) + "%)");
        }
    }
    
    // Sets a button red
    public void setRed(Workout workout) {
        MyButton optionButton;
        optionButton = optionsButtons.get(workout.getIndex());
        
        optionButton.setColor(Colors.RED);
    }
    
    // Updates the max on the label if the new max is higher than the last
    public void updateMax(Exercise exercise, int index) {
        
        int oldNumber = maxWeightLabels.get(index).getNumber();
        int newNumber = maxOptionsButtons.get(index).getMax();
        
        if (newNumber > oldNumber) {
            maxWeightLabels.get(exercise.getIndex()).setNumber(newNumber);
            maxWeightLabels.get(exercise.getIndex()).addText(newNumber + "");
        }
    }

    /*
        This sets the label if the max was successful or not
        This is for the user to see if they were successful
    */
    public void maxSuccessful(boolean successful) {
        Timer timer = new Timer();
        
        maxSuccessfulLabel.setVisible(true);
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                maxSuccessfulLabel.setVisible(false);
            }
        }, 3000);
        
        if (successful) {
            maxSuccessfulLabel.setText("Max Successful");
            maxSuccessfulLabel.setForeground(Colors.GREEN);
        } else {
            maxSuccessfulLabel.setText("Max Failed");
            maxSuccessfulLabel.setForeground(Colors.RED);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(Sizes.GYM_PANEL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
