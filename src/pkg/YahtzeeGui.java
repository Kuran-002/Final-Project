package pkg;

import javax.swing.*;
import java.awt.*;

public class YahtzeeGui extends JFrame {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private YahtzeeGameLogic model;  // Your game logic model
    private JButton[] diceButtons;   // Buttons for each dice
    private RollButton rollButton;      // Button to roll the dice

    // Constructor
    public YahtzeeGui(YahtzeeGameLogic gameModel) {
        this.model = gameModel;

        setTitle("Yahtzee Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Load the background image
        Image backgroundImage = new ImageIcon("Backgound.png").getImage();

        // Create a custom panel that draws the image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // You can choose other layouts too

        // Example: create and add a Roll button
        rollButton = new RollButton("Roll", 250, 30);
        rollButton.setBounds(350, 500, 100, 40); // x, y, width, height
        backgroundPanel.add(rollButton);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Pass the game model instance to the GUI
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}
