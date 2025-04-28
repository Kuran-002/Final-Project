package pkg;

import javax.swing.*;
import java.awt.*;

public class YahtzeeGui extends JFrame {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private YahtzeeGameLogic model;  // Your game logic model
    private JLabel rollremainLabel;  // The label to show roll remaining

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
        backgroundPanel.setLayout(null);

        // Create rollremainLabel to show remaining rolls
        rollremainLabel = new JLabel("Roll remain: " + model.getRollRemaining());
        rollremainLabel.setForeground(Color.WHITE);
        rollremainLabel.setBounds(30, 10, 200, 40); // x, y, width, height
        backgroundPanel.add(rollremainLabel);

        // Create roll button and add it to background panel
        RollButton rollButton = new RollButton("Roll", 250, 30, model, rollremainLabel);
        rollButton.setBounds(350, 500, 100, 40); // x, y, width, height
        backgroundPanel.add(rollButton);

        // Set the content pane and make it visible
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Pass the game model instance to the GUI
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}
