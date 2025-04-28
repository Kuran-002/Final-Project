package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YahtzeeGui extends JFrame {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private YahtzeeGameLogic model;  // Your game logic model
    private JButton[] diceButtons;   // Buttons for each dice
    private JButton rollButton;      // Button to roll the dice
    private JTable scoreTable;       // Scoreboard table

    // Constructor
    public YahtzeeGui(YahtzeeGameLogic gameModel) {
       
    }

    public static void main(String[] args) {
        // Pass the game model instance to the GUI
        new YahtzeeGui(new YahtzeeGameLogic());
    }
}
