package pkg;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text, int width, int height) {
        super(text);

        // Load and scale images
        ImageIcon normalIcon = new ImageIcon("Pressedbutton.png");
        ImageIcon pressedIcon = new ImageIcon("UnPressbutton.png");
        ImageIcon hoverIcon = new ImageIcon("UnPressbutton.png");

        normalIcon = new ImageIcon(normalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        pressedIcon = new ImageIcon(pressedIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        hoverIcon = new ImageIcon(hoverIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        this.setIcon(normalIcon);
        this.setPressedIcon(pressedIcon);
        this.setRolloverIcon(hoverIcon);

        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        

        this.setForeground(Color.WHITE);
    }
}
