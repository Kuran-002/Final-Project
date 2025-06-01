package pkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * References:
 * ChatGPT
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 *Provides the main GUI frame for the Yahtzee game.
 *Displays dice, score board, roll remaining label, and manages game-over overlay.
 *Handles user interactions with the game interface.
 */
public class YahtzeeGui extends JFrame {
    private YahtzeeGameLogic model;
    private JLabel rollremainLabel;
    private List<DiceGui> diceButtons = new ArrayList<>();

    /**
     * Constructs the Yahtzee GUI, initializes all components, and sets up event listeners.
     * 
     * @param gameModel the YahtzeeGameLogic instance backing the game state and logic
     */
    public YahtzeeGui(YahtzeeGameLogic gameModel) {
        this.model = gameModel;

        // Set up game event listeners
        model.setGameListener(new YahtzeeGameLogic.GameListener() {
            @Override
            public void onRollRemainingReset(int newRolls) {
                SwingUtilities.invokeLater(() -> {
                    rollremainLabel.setText("Roll remain: " + newRolls);
                });
            }

            @Override
            public void onGameOver(int finalScore) {
                SwingUtilities.invokeLater(() -> {
                    // Create translucent overlay panel
                    JPanel overlayPanel = new JPanel(new GridBagLayout());
                    overlayPanel.setOpaque(false);
                    overlayPanel.setBounds(0, 0, getWidth(), getHeight());

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);
                    gbc.gridx = 0;
                    gbc.gridy = 0;

                    // Game over message label
                    JLabel gameOverLabel = new JLabel("Game Over! Your total score is: " + finalScore);
                    gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
                    gameOverLabel.setForeground(Color.RED);
                    overlayPanel.add(gameOverLabel, gbc);

                    // Play again button to restart game
                    gbc.gridy = 1;
                    JButton playAgainButton = new JButton("Play Again");
                    playAgainButton.setFont(new Font("Arial", Font.BOLD, 24));
                    playAgainButton.addActionListener(e -> {
                        dispose();
                        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
                    });
                    overlayPanel.add(playAgainButton, gbc);

                    // Add overlay panel to layered pane above all other components
                    getLayeredPane().add(overlayPanel, JLayeredPane.POPUP_LAYER);
                    getContentPane().revalidate();
                    getContentPane().repaint();
                });
            }
        });

        setTitle("Yahtzee Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(true);

        // Background panel with table image painting
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Sprites/TableBackground.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        // ScoreBoard GUI panel
        ScoreBoardGui scoreBoardGui = new ScoreBoardGui(model, this);
        scoreBoardGui.setBackground(Color.BLACK);
        backgroundPanel.add(scoreBoardGui);

        // Label to show remaining rolls
        rollremainLabel = new JLabel("Roll remain: " + model.getRollRemaining());
        rollremainLabel.setForeground(Color.WHITE);
        rollremainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        backgroundPanel.add(rollremainLabel);

        // Dice buttons panel
        JPanel dicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        dicePanel.setOpaque(false);

        Dice[] realDice = model.getDiceSet().getDiceArray();
        for (int i = 0; i < 5; i++) {
            DiceGui diceGui = new DiceGui("", 64, 64, model, realDice[i]);
            diceGui.setOpaque(false);
            diceGui.setPreferredSize(new Dimension(64, 64));
            diceButtons.add(diceGui);
            dicePanel.add(diceGui);
        }

        // Update dice icons to match initial dice values
        for (DiceGui diceGui : diceButtons) {
            diceGui.updateIcon(diceGui.getDice().getValue());
        }
        backgroundPanel.add(dicePanel);

        // Roll button to roll dice
        RollButton rollButton = new RollButton("", 200, 50, model, rollremainLabel, diceButtons);
        backgroundPanel.add(rollButton);

        setContentPane(backgroundPanel);
        setVisible(true);

        // Layout components once frame size is known
        SwingUtilities.invokeLater(() -> {
            int width = getWidth();
            int height = getHeight();

            int sbWidth = (int) (width * 0.6);
            int sbHeight = (int) (height * 0.5);
            int sbX = (width - sbWidth) / 2;
            int sbY = height / 6;
            scoreBoardGui.setBounds(sbX, sbY, sbWidth, sbHeight);

            rollremainLabel.setBounds(30, height - 80, 300, 40);

            int diceCount = 5;
            int diceWidth = 64;
            int spacing = 20;
            int dicePanelWidth = (diceCount * diceWidth) + ((diceCount - 1) * spacing);
            int dicePanelHeight = 100;
            dicePanel.setBounds((width - dicePanelWidth) / 2, height - 160, dicePanelWidth, dicePanelHeight);

            int rollButtonWidth = 200;
            int rollButtonHeight = 50;
            rollButton.setBounds((width - rollButtonWidth) / 2, height - 80, rollButtonWidth, rollButtonHeight);

            backgroundPanel.revalidate();
            backgroundPanel.repaint();
        });
    }

    /**
     * Refreshes the hold style of all dice buttons.
     * Should be called when hold status of dice changes.
     */
    public void refreshDiceHoldStyles() {
        for (DiceGui diceGui : diceButtons) {
            diceGui.updateHoldStyle();
        }
    }

    /**
     * The main entry point to launch the Yahtzee GUI application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}
