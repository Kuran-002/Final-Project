package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoardGui extends JPanel {
    private YahtzeeGameLogic gameLogic;
    private JLabel totalScoreLabel;
    private JPanel categoriesPanel;

    private final Map<String, String> categoryMap = new LinkedHashMap<>();

    public ScoreBoardGui(YahtzeeGameLogic gameLogic) {
        this.gameLogic = gameLogic;
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel("Yahtzee Scoreboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        setupCategoryMap();

        categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(7, 2, 10, 10));
        categoriesPanel.setBackground(Color.DARK_GRAY);
        add(categoriesPanel, BorderLayout.CENTER);

        JPanel totalScorePanel = new JPanel(new FlowLayout());
        totalScorePanel.setBackground(Color.DARK_GRAY);
        totalScoreLabel = new JLabel("Total Score: " + gameLogic.getTotalScore());
        totalScoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        totalScoreLabel.setForeground(Color.WHITE);
        totalScorePanel.add(totalScoreLabel);
        add(totalScorePanel, BorderLayout.SOUTH);

        initializeCategories();
    }

    private void setupCategoryMap() {
        categoryMap.put("Ones", "ones");
        categoryMap.put("Twos", "twos");
        categoryMap.put("Threes", "threes");
        categoryMap.put("Fours", "fours");
        categoryMap.put("Fives", "fives");
        categoryMap.put("Sixes", "sixes");
        categoryMap.put("Two of a Kind", "twoofakind");
        categoryMap.put("Three of a Kind", "threeofakind");
        categoryMap.put("Four of a Kind", "fourofakind");
        categoryMap.put("Full House", "fullhouse");
        categoryMap.put("Small Straight", "smallstraight");
        categoryMap.put("Large Straight", "largestraight");
        categoryMap.put("Yahtzee", "yahtzee");
        categoryMap.put("Chance", "chance");
    }

    private void initializeCategories() {
        for (String displayName : categoryMap.keySet()) {
            JButton categoryButton = new JButton(displayName);
            categoryButton.setPreferredSize(new Dimension(250, 60));
            categoryButton.setFont(new Font("Arial", Font.PLAIN, 20));
            categoryButton.addActionListener(new CategoryButtonListener(displayName));
            categoriesPanel.add(categoryButton);
        }
    }

    private class CategoryButtonListener implements ActionListener {
        private final String displayName;

        public CategoryButtonListener(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String categoryKey = categoryMap.get(displayName);
            boolean success = gameLogic.scoreCategory(categoryKey);

            if (success) {
                totalScoreLabel.setText("Total Score: " + gameLogic.getTotalScore());

                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setEnabled(false);

                int score = gameLogic.getScoreBoard().getCategoryScore(categoryKey);

                sourceButton.setText(displayName + " (" + score + ")");
            } else {
                JOptionPane.showMessageDialog(ScoreBoardGui.this,
                        "This category has already been scored or an error occurred!",
                        "Scoring Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
