package Defaults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Energy {
    public static final int STARTING_ENERGY = 100;
    
    public static final int REGEN_RATE = 20;
    public static final int REGEN_PERIOD = 5000; // in milliseconds
    
    // Energy loss for workouts
    public static final int LIGHT_WORKOUT = 10;
    public static final int NORMAL_WORKOUT = 25;
    public static final int MAX_WORKOUT = 90;
    
    public static final List<Integer> WORKOUT_ENERGY= 
    Collections.unmodifiableList(Arrays.asList(LIGHT_WORKOUT, NORMAL_WORKOUT, MAX_WORKOUT));
}
