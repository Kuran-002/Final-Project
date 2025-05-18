package pkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class YahtzeeGui extends JFrame {
    private YahtzeeGameLogic model;
    private JLabel rollremainLabel;

    public YahtzeeGui(YahtzeeGameLogic gameModel) {
        this.model = gameModel;

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
        backgroundPanel.setLayout(null);  // Absolute layout for precise control

        // Scoreboard GUI (centered near top)
        ScoreBoardGui scoreBoardGui = new ScoreBoardGui(model);
        scoreBoardGui.setBackground(Color.BLACK);
        scoreBoardGui.setPreferredSize(new Dimension(800, 600));  // Width x Height
        backgroundPanel.add(scoreBoardGui);

        // Roll remain label (bottom-left corner)
        rollremainLabel = new JLabel("Roll remain: " + model.getRollRemaining());
        rollremainLabel.setForeground(Color.WHITE);
        rollremainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        backgroundPanel.add(rollremainLabel);

        // Dice panel (bottom-center)
        List<DiceGui> diceButtons = new ArrayList<>();
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

        // Roll button (bottom-center)
        RollButton rollButton = new RollButton("Roll", 200, 50, model, rollremainLabel, diceButtons);
        backgroundPanel.add(rollButton);

        setContentPane(backgroundPanel);
        setVisible(true);

        // Layout positioning
        SwingUtilities.invokeLater(() -> {
            int width = getWidth();
            int height = getHeight();

            // Center scoreboard horizontally and position it 1/4 down from top
            Dimension sbSize = scoreBoardGui.getPreferredSize();
            int sbX = (width - sbSize.width) / 2;
            int sbY = height / 6;
            scoreBoardGui.setBounds(sbX, sbY, sbSize.width, sbSize.height);

            // Roll remain label in bottom-left
            rollremainLabel.setBounds(30, height - 80, 300, 40);

            // Dice panel in bottom-center
            int diceCount = 5;
            int diceWidth = 64;
            int spacing = 20;
            int dicePanelWidth = (diceCount * diceWidth) + ((diceCount - 1) * spacing);
            int dicePanelHeight = 100;
            dicePanel.setBounds((width - dicePanelWidth) / 2, height - 160, dicePanelWidth, dicePanelHeight);

            // Roll button just above bottom-center
            int rollButtonWidth = 200;
            int rollButtonHeight = 50;
            rollButton.setBounds((width - rollButtonWidth) / 2, height - 80, rollButtonWidth, rollButtonHeight);

            backgroundPanel.revalidate();
            backgroundPanel.repaint();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}
