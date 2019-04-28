package PaulsGym;

import Defaults.Colors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    private String name;
    private int max;
    
    // Used mainly for exercise buttons or buttons with pictures on them
    public MyButton(ActionListener action, String name, Dimension size, ImageIcon image, Point position) {
        this.addActionListener(action);
        this.name = name;
        this.setIcon(image);
        this.setLocation(position);
        this.setSize(size);
        this.setBackground(Colors.DEFAULT_BUTTON);
    }
 
    // Used for non exercise buttons
    public MyButton(ActionListener action, String text, Point position, Dimension size, Font font) {
        this.addActionListener(action);
        this.name = text;
        this.setText(text);
        this.setLocation(position);
        this.setSize(size);
        this.setFont(font);
        this.setBackground(Colors.DEFAULT_BUTTON);
    }
    
    
    // Sets to default color
    public void setDefault() {
        this.setBackground(Colors.DEFAULT_BUTTON);
    }
    
    public void setColor(Color color) {
        this.setBackground(color);
    }
    
    public void addText(String text) {
        String addedText = text;
        this.setText(this.name + addedText);
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMax() {
        return max;
    }
}
