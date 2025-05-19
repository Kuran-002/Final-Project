package pkg;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DiceGui extends JButton {
    private ImageIcon[] icons;
    private Dice dice;

    public DiceGui(String text, int width, int height, YahtzeeGameLogic model, Dice dice) {
        super(text);
        this.dice = dice;

        // Load images
        icons = new ImageIcon[6];
        icons[0] = new ImageIcon("Dice1.png");
        icons[1] = new ImageIcon("Dice2.png");
        icons[2] = new ImageIcon("Dice3.png");
        icons[3] = new ImageIcon("Dice4.png");
        icons[4] = new ImageIcon("Dice5.png");
        icons[5] = new ImageIcon("Dice6.png");

        for (int i = 0; i < icons.length; i++) {
            Image img = icons[i].getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(img);
        }

        updateIcon(dice.getValue());
        updateHoldStyle();

        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);

        // IMPORTANT: Only toggle hold if holding is NOT locked
        this.addActionListener(e -> {
            if (model.isHoldLocked()) {
                // Holding is locked, do nothing
                return;
            }
            dice.toggleHold();
            updateHoldStyle();
        });
    }

    public void updateIcon(int value) {
        if (value >= 1 && value <= 6) {
            this.setIcon(icons[value - 1]);
        }
    }

    public void updateHoldStyle() {
        if (dice.isHeld()) {
            this.setBorderPainted(true);
            this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 3));
        } else {
            this.setBorderPainted(false);
        }
    }

    public Dice getDice() {
        return dice;
    }

    public void rollIfNotHeld() {
        if (!dice.isHeld()) {
            dice.roll();
            updateIcon(dice.getValue());
        }
    }
}
