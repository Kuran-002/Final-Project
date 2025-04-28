package pkg;

import javax.swing.*;
import java.awt.*;

public class RollButton extends JButton {

    // Constructor for RollButton
    public RollButton(String text, int width, int height, YahtzeeGameLogic model, JLabel rollremainLabel) {
        super(text);

        // Load and scale images for button states
        ImageIcon normalIcon = new ImageIcon("Pressedbutton.png");
        ImageIcon pressedIcon = new ImageIcon("UnPressbutton.png");
        ImageIcon hoverIcon = new ImageIcon("UnPressbutton.png");

        // Resize icons to match button size
        normalIcon = new ImageIcon(normalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        pressedIcon = new ImageIcon(pressedIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        hoverIcon = new ImageIcon(hoverIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        // Set button icons
        this.setIcon(normalIcon);
        this.setPressedIcon(pressedIcon);
        this.setRolloverIcon(hoverIcon);

        // Set text position and button appearance
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setForeground(Color.WHITE);

        // Action listener for roll button
        this.addActionListener(e -> {
            // Try rolling the dice
            if (model.rollDice()) {
                // If the roll is successful, update the remaining rolls label
                rollremainLabel.setText("Roll remain: " + model.getRollRemaining());
            } else {
                // If no rolls remain, show a message
                JOptionPane.showMessageDialog(null, "No rolls remaining!");
            }
        });
    }
}
