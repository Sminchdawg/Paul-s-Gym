package PaulsGym;


public interface GymPanelActions{
    
    // Exercise Button GymPanelActions
    void performBench();
    void performSquat();
    void performDeadlift();
    void combinedExerciseAction();
    
    void notEnoughEnergyAction();
    
    // Other Main Gym GymPanelActions
    void eatTunaSandwich();
    
    // Options for Workout Button GymPanelActions
    void lightWorkout();
    void normalWorkout();
    void maxWorkout();
    
    // Options for Max Workout Button GymPanelActions
    void lowMax();
    void mediumMax();
    void highMax();
}
