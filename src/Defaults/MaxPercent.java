package Defaults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxPercent {
    public static final double LOW_MAX_PERCENT = 0.80;
    public static final double MEDIUM_MAX_PERCENT = 0.60;
    public static final double HIGH_MAX_PERCENT = 0.30;
    
    public static final List<Double> PERCENTS = 
    Collections.unmodifiableList(Arrays.asList(LOW_MAX_PERCENT, MEDIUM_MAX_PERCENT, HIGH_MAX_PERCENT));
}
