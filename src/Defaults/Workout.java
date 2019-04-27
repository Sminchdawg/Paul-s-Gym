package Defaults;

public class Workout {
    public static final String LIGHT_WORKOUT = "Light Workout";
    public static final String NORMAL_WORKOUT = "Normal Workout";
    public static final String MAX_WORKOUT = "Max Workout";
    
    private String workout;
    private int index;
    
    public Workout (String workout, int index) {
        this.workout = workout;
        this.index = index;
    }

    public String getWorkout() {
        return workout;
    }

    public int getIndex() {
        return index;
    }
}
