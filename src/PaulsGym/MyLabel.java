package PaulsGym;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Font;

public class MyLabel extends JLabel{
    
    private String text;
    private int number;
    
    // A label that is used for standard text needs
    public MyLabel(String text, Dimension size, Point location, Font font) {
        this.text = text;
        this.setText(text);
        this.setLocation(location);
        this.setSize(size);
        this.setFont(font);
    }
    
    // A label that is used for labels with numbers
    public MyLabel(String text, Dimension size, Point location, Font font, int number) {
        this.text = text;
        this.setSize(size);
        this.setLocation(location);
        this.setFont(font);
        this.number = number;
        this.addText(number + "");
    }
    
    // Adds text into the label
    public void addText(String text) {
        this.setText(this.text + text);
    }
    
    // Specific functions for strength labels
    public void addToStrengthLevel(int strengthLevel) {
        this.number += strengthLevel;
        addText(this.number + "");
    }
    
    public void setStrengthLevel(int strengthLevel) {
        this.number = strengthLevel;
        addText(this.number + "");
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
}
