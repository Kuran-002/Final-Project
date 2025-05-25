package pkg;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A custom JButton used for rolling dice in the Yahtzee game.
 * Displays images for normal, hover, and pressed states and updates the game state on click.
 */
public class RollButton extends JButton {

    /**
     * Constructs a RollButton with custom icons, size, and associated game logic.
     *
     * @param text            the button label text
     * @param width           the width to scale the button icons
     * @param height          the height to scale the button icons
     * @param model           the YahtzeeGameLogic instance controlling game state
     * @param rollremainLabel the JLabel that displays the remaining roll count
     * @param diceButtons     the list of DiceGui buttons representing dice in the UI
     */
    public RollButton(String text, int width, int height, YahtzeeGameLogic model, JLabel rollremainLabel, List<DiceGui> diceButtons) {
        super(text);

        ImageIcon normalIcon = new ImageIcon("Normal.png");
        ImageIcon pressedIcon = new ImageIcon("Clicked.png");
        ImageIcon hoverIcon = new ImageIcon("Hover.png");

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

        this.addActionListener(e -> {
            if (model.rollDice()) {
                rollremainLabel.setText("Roll remain: " + model.getRollRemaining());

                // Roll dice that are not held
                for (DiceGui diceGui : diceButtons) {
                    diceGui.rollIfNotHeld();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No rolls remaining!");
            }
        });
    }
}
