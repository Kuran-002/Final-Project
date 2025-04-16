package pkg;

import javax.swing.*;
import java.awt.*;

public class YahtzeeGui {
    static int rollremain = 5;

    public static void main(String[] args) {

        // Get screen size dynamically
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        JFrame frame1 = new JFrame();
        frame1.setTitle("Yahtzee");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setUndecorated(true); // remove window borders
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximize full screen
        frame1.setResizable(false);

        // Load and scale background
        ImageIcon background = new ImageIcon("Backgound.png");
        Image backgroundImage = background.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundImage);

        JLabel label1 = new JLabel();
        label1.setIcon(background);
        label1.setLayout(null);
        label1.setBounds(0, 0, screenWidth, screenHeight);

        // Set proportional sizes
        int buttonWidth = screenWidth / 10;  
        int buttonHeight = screenHeight / 14;
        int yPosition = (int)(screenHeight * 0.85);

        // Create Roll Button
        Button rollButton = new Button("Roll",buttonWidth , buttonHeight);
        rollButton.setBounds(screenWidth / 2 - buttonWidth - 10, yPosition, buttonWidth, buttonHeight);
        rollButton.setFont(new Font("Arial", Font.BOLD, buttonHeight / 2));
        rollButton.setForeground(Color.WHITE);
        rollButton.setFocusPainted(false);

        // Roll Left Label
        JLabel RollLeft = new JLabel("Roll Left: " + rollremain);
        RollLeft.setBounds(screenWidth / 2 + 10, yPosition, buttonWidth + 50, buttonHeight);
        RollLeft.setForeground(Color.WHITE);
        RollLeft.setFont(new Font("Pixel Emulator", Font.BOLD, buttonHeight / 2));
        RollLeft.setHorizontalAlignment(SwingConstants.LEFT);

        // Add action listener
        rollButton.addActionListener(e -> {
            if (rollremain > 0) {
                rollremain--;
                
                RollLeft.setText("Roll Left: " + rollremain);
            }
        });

        label1.add(RollLeft);
        label1.add(rollButton);

        frame1.add(label1);
        frame1.setVisible(true);
    }
}
