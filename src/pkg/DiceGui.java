package pkg;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * This class defines a graphical button representing a dice, including dynamic icon updates 
 * based on the dice's value, and visual feedback for held/unheld state. It interacts with the 
 * YahtzeeGameLogic model to determine whether the hold functionality is allowed.
 */
public class DiceGui extends JButton {
    private ImageIcon[] icons;
    private Dice dice;

    /**
     * Constructs a DiceGui button representing a dice with graphical icons.
     * It sets up the icons, initial dice value, and hold style.
     * Adds an ActionListener to toggle hold state if holding is not locked in the game model.
     * 
     * @param text the text to display on the button (often unused due to icon)
     * @param width desired icon width in pixels
     * @param height desired icon height in pixels
     * @param model the YahtzeeGameLogic model, used to check if hold is locked
     * @param dice the Dice object this button represents and controls
     */
    public DiceGui(String text, int width, int height, YahtzeeGameLogic model, Dice dice) {
        super(text);
        this.dice = dice;

        // Load images
        icons = new ImageIcon[6];
        icons[0] = new ImageIcon("Sprites/Dice1.png");
        icons[1] = new ImageIcon("Sprites/Dice2.png");
        icons[2] = new ImageIcon("Sprites/Dice3.png");
        icons[3] = new ImageIcon("Sprites/Dice4.png");
        icons[4] = new ImageIcon("Sprites/Dice5.png");
        icons[5] = new ImageIcon("Sprites/Dice6.png");

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

        // Toggle hold on click only if hold is not locked in the model
        this.addActionListener(e -> {
            if (model.isHoldLocked()) {
                return;
            }
            dice.toggleHold();
            updateHoldStyle();
        });
    }

    /**
     * Updates the button icon to reflect the dice's current value.
     * 
     * @param value the dice value (1-6) to update the icon for
     */
    public void updateIcon(int value) {
        if (value >= 1 && value <= 6) {
            this.setIcon(icons[value - 1]);
        }
    }

    /**
     * Updates the button border style to indicate whether the dice is held or not.
     * Held dice get a red border; otherwise, no border is painted.
     */
    public void updateHoldStyle() {
        if (dice.isHeld()) {
            this.setBorderPainted(true);
            this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 3));
        } else {
            this.setBorderPainted(false);
        }
    }

    /**
     * Returns the Dice object associated with this DiceGui button.
     * 
     * @return the Dice instance this button controls
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Rolls the dice and updates the icon only if the dice is not currently held.
     */
    public void rollIfNotHeld() {
        if (!dice.isHeld()) {
            dice.roll();
            updateIcon(dice.getValue());
        }
    }
}
