package pkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class YahtzeeGui extends JFrame {
    private YahtzeeGameLogic model;
    private JLabel rollremainLabel;
    private List<DiceGui> diceButtons = new ArrayList<>();

    public YahtzeeGui(YahtzeeGameLogic gameModel) {
        this.model = gameModel;
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
                   
                    JPanel overlayPanel = new JPanel(new GridBagLayout());
                    overlayPanel.setOpaque(false);
                    overlayPanel.setBounds(0, 0, getWidth(), getHeight());

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);
                    gbc.gridx = 0;
                    gbc.gridy = 0;

                    JLabel gameOverLabel = new JLabel("Game Over! Your total score is: " + finalScore);
                    gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
                    gameOverLabel.setForeground(Color.RED);
                    overlayPanel.add(gameOverLabel, gbc);

                    gbc.gridy = 1;
                    JButton playAgainButton = new JButton("Play Again");
                    playAgainButton.setFont(new Font("Arial", Font.BOLD, 24));
                    playAgainButton.addActionListener(e -> {
                        dispose();
                        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
                    });
                    overlayPanel.add(playAgainButton, gbc);

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

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Backgound.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        ScoreBoardGui scoreBoardGui = new ScoreBoardGui(model, this);
        scoreBoardGui.setBackground(Color.BLACK);
        scoreBoardGui.setPreferredSize(new Dimension(800, 600));
        backgroundPanel.add(scoreBoardGui);

        rollremainLabel = new JLabel("Roll remain: " + model.getRollRemaining());
        rollremainLabel.setForeground(Color.WHITE);
        rollremainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        backgroundPanel.add(rollremainLabel);

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

        for (DiceGui diceGui : diceButtons) {
            diceGui.updateIcon(diceGui.getDice().getValue());
        }
        backgroundPanel.add(dicePanel);

        RollButton rollButton = new RollButton("Roll", 200, 50, model, rollremainLabel, diceButtons);
        backgroundPanel.add(rollButton);

        setContentPane(backgroundPanel);
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            int width = getWidth();
            int height = getHeight();

            Dimension sbSize = scoreBoardGui.getPreferredSize();
            int sbX = (width - sbSize.width) / 2;
            int sbY = height / 6;
            scoreBoardGui.setBounds(sbX, sbY, sbSize.width, sbSize.height);

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

    public void refreshDiceHoldStyles() {
        for (DiceGui diceGui : diceButtons) {
            diceGui.updateHoldStyle();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}
