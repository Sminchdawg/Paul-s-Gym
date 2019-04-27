package Defaults;

public class Exercise {
    public static final String BENCH = "Bench";
    public static final String SQUAT = "Squat";
    public static final String DEADLIFT = "Deadlift";
    
    private String name;
    private int index;
    
    public Exercise (String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
    
}
