package PaulsGym;

import Defaults.Energy;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JProgressBar;
import Defaults.Fonts;

public class MyEnergyBar extends JProgressBar {
    private int energy;
    
    public MyEnergyBar(Point location, Dimension size) {
        energy = Energy.STARTING_ENERGY;
        this.setValue(energy);
        this.setLocation(location);
        this.setSize(size);
        this.setString(energy + "%");
        this.setFont(Fonts.BOLD);
        
        // Include a string on the bar
        this.setStringPainted(true);
    }
    
    public void updateEnergy(int energy) {
        this.energy -= energy;
        this.setValue(this.energy);
        this.setString(this.energy + "%");
    }
    
    public void resetEnergy() {
        this.energy = Energy.STARTING_ENERGY;
        this.setValue(this.energy);
        this.setString(this.energy + "%");
    }
    
    public int getEnergy() {
        return energy;
    }
}
