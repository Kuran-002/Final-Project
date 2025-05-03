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
        backgroundPanel.setLayout(null);

        // Roll remain label (top-left)
        rollremainLabel = new JLabel("Roll remain: " + model.getRollRemaining());
        rollremainLabel.setForeground(Color.WHITE);
        rollremainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        backgroundPanel.add(rollremainLabel);

        // Dice panel (bottom-center)
        List<DiceGui> diceButtons = new ArrayList<>();
        JPanel dicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        dicePanel.setOpaque(false);

        for (int i = 0; i < 5; i++) {
            Dice dice = new Dice();
            DiceGui diceGui = new DiceGui("", 64, 64, model, dice);
            diceGui.setOpaque(false);
            diceButtons.add(diceGui);
            dicePanel.add(diceGui);
        }
        backgroundPanel.add(dicePanel);

        // Roll button
        RollButton rollButton = new RollButton("Roll", 200, 50, model, rollremainLabel, diceButtons);
        backgroundPanel.add(rollButton);

        setContentPane(backgroundPanel);
        setVisible(true);

        // Dynamic layout based on actual screen size after frame is shown
        SwingUtilities.invokeLater(() -> {
            int width = getWidth();
            int height = getHeight();

            rollremainLabel.setBounds(20, 20, 300, 40);

            int dicePanelWidth = 600;
            int dicePanelHeight = 80;
            dicePanel.setBounds((width - dicePanelWidth) / 2, height - 200, dicePanelWidth, dicePanelHeight);

            int rollButtonWidth = 200;
            int rollButtonHeight = 50;
            rollButton.setBounds((width - rollButtonWidth) / 2, height - 100, rollButtonWidth, rollButtonHeight);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new YahtzeeGui(new YahtzeeGameLogic()));
    }
}