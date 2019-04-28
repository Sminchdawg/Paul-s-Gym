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
        
        
        for (MyButton exerciseButton: exerciseButtons) {
            this.add(exerciseButton);
        }
        
        for (MyButton optionButton: optionsButtons) {
            this.add(optionButton);
        }
        
        for (MyButton maxOptionButton: maxOptionsButtons) {
            this.add(maxOptionButton);
        }
        
        exerciseLabels.add(new MyLabel("Bench", Sizes.NORMAL_LABEL, new Point(270, 25), Fonts.BOLD));
        exerciseLabels.add(new MyLabel("Squat", Sizes.NORMAL_LABEL, new Point(570, 25), Fonts.BOLD));
        exerciseLabels.add(new MyLabel("Deadlift", Sizes.NORMAL_LABEL, new Point(870, 25), Fonts.BOLD));
        
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(270, 275), Fonts.NORMAL, 0));
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(570, 275), Fonts.NORMAL, 0));
        maxWeightLabels.add(new MyLabel("Max: ", Sizes.NORMAL_LABEL, new Point(870, 275), Fonts.NORMAL, 0));
       
        for (MyLabel exerciseLabel: exerciseLabels) {
            this.add(exerciseLabel);
        }
        
        for (MyLabel maxWeightLabel: maxWeightLabels) {
            this.add(maxWeightLabel);
        }
        
        maxSuccessfulLabel = new MyLabel("", Sizes.BIG_LABEL, new Point(750,350),Fonts.HEADER);
        this.add(maxSuccessfulLabel);
        
        this.add(tunaSandwichButton);
        shouldShowOptions(false);
        shouldShowMaxOptions(false);
        maxSuccessfulLabel.setVisible(false);
    }
    
    public void shouldShowOptions(boolean shouldShow) {
        for (MyButton optionButton: optionsButtons) {
            optionButton.setVisible(shouldShow);
        }
    }
    
    public void shouldShowMaxOptions(boolean shouldShow) {
        for (MyButton maxOptionButton: maxOptionsButtons) {
            maxOptionButton.setVisible(shouldShow);
        }
    }
    
    public void setExerciseDefault() {
        for (MyButton exerciseButton: exerciseButtons) {
            exerciseButton.setDefault();
        }
    }
    
    public void setOptionsDefault() {
        for (MyButton optionsButton: optionsButtons) {
            optionsButton.setDefault();
        }
    }
    
    public void setOneOptionDefault(int index) {
        optionsButtons.get(index).setDefault();
    }
    
    public void setGreen(Exercise exercise) {
        MyButton exerciseButton;
        exerciseButton = exerciseButtons.get(exercise.getIndex());
        
        setExerciseDefault();
        
        exerciseButton.setColor(Colors.GREEN);
    }

    public void setMaxButtons(List<Integer> maxes) {
        for (int i = 0; i < maxOptionsButtons.size(); i ++) {
            maxOptionsButtons.get(i).setMax(maxes.get(i));
            maxOptionsButtons.get(i).addText(" " + maxes.get(i) + " (" + (int)(100 * MaxPercent.PERCENTS.get(i)) + "%)");
        }
    }
    
    public void setRed(Workout workout) {
        MyButton optionButton;
        optionButton = optionsButtons.get(workout.getIndex());
        
        optionButton.setColor(Colors.RED);
    }
    
    public void updateMax(Exercise exercise, int index) {
        int oldNumber = maxWeightLabels.get(index).getNumber();
        int newNumber = maxOptionsButtons.get(index).getMax();
        System.out.println("New Number: "  + newNumber);
        System.out.println("Old Number: " + oldNumber);
        if (newNumber > oldNumber) {
            maxWeightLabels.get(exercise.getIndex()).setNumber(newNumber);
            maxWeightLabels.get(exercise.getIndex()).addText(newNumber + "");
        }
        System.out.println("Updated old number: " + maxWeightLabels.get(index).getNumber());
    }
    
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
