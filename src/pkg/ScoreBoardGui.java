package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.IOException;

/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * References:
 * ChatGPT, oracle.com
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * ScoreBoardGui is a Swing-based JPanel that visually represents the score categories and 
 * total score for a Yahtzee game. It initializes category buttons with custom graphics, 
 * handles category scoring through button interactions, and integrates with the 
 * YahtzeeGameLogic and YahtzeeGui to update game state and UI. It also renders a 
 * background image and formats the interface using layout managers for a visually 
 * appealing scoreboard.
 */
public class ScoreBoardGui extends JPanel {
    private YahtzeeGameLogic gameLogic;
    private YahtzeeGui yahtzeeGui;
    private JLabel totalScoreLabel;
    private JPanel categoriesPanel;
    private Image backgroundImage;

    private final Map<String, String> categoryMap = new LinkedHashMap<>();

    /**
     * Scales an image icon from the given file path to the specified width and height.
     * @param path the image file path
     * @param width desired width
     * @param height desired height
     * @return scaled ImageIcon
     */
    private ImageIcon scaleIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Constructs the ScoreBoardGui with game logic and main GUI references.
     * Sets layout, background, category buttons, and total score panel.
     * @param gameLogic the Yahtzee game logic instance
     * @param yahtzeeGui the main Yahtzee GUI instance
     */
    public ScoreBoardGui(YahtzeeGameLogic gameLogic, YahtzeeGui yahtzeeGui) {
        this.gameLogic = gameLogic;
        this.yahtzeeGui = yahtzeeGui;
        this.backgroundImage = new ImageIcon("Sprites/ScoreboardBackGround.png").getImage();

        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel titleLabel = new JLabel(" ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(false);
        add(titleLabel, BorderLayout.NORTH);

        setupCategoryMap();

        categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(7, 2, 10, 10));
        categoriesPanel.setOpaque(false);
        add(categoriesPanel, BorderLayout.CENTER);

        JPanel totalScorePanel = createTotalScorePanel();
        add(totalScorePanel, BorderLayout.SOUTH);

        initializeCategories();
    }

    /**
     * Sets up the mapping between category display names and internal keys.
     */
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

    /**
     * Initializes the category buttons with custom icons and adds them to the panel.
     */
    private void initializeCategories() {
        ImageIcon normalIcon = scaleIcon("Sprites/NormalScore.png", 300, 40);
        ImageIcon hoverIcon = scaleIcon("Sprites/HoverScore.png", 300, 40);
        ImageIcon pressedIcon = scaleIcon("Sprites/ClickedScore.png", 300, 40);

        for (String displayName : categoryMap.keySet()) {
            JButton categoryButton = new JButton(displayName);
            categoryButton.setPreferredSize(new Dimension(20, 20));
            categoryButton.setFont(new Font("Arial", Font.BOLD, 20));
            categoryButton.setForeground(Color.WHITE);
            categoryButton.setContentAreaFilled(false);
            categoryButton.setBorderPainted(false);
            categoryButton.setFocusPainted(false);
            categoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            categoryButton.setVerticalTextPosition(SwingConstants.CENTER);
            categoryButton.setIcon(normalIcon);
            categoryButton.setRolloverIcon(hoverIcon);
            categoryButton.setPressedIcon(pressedIcon);

            categoryButton.addActionListener(new CategoryButtonListener(displayName));
            categoriesPanel.add(categoryButton);
        }
    }

    /**
     * Creates and returns a JPanel containing the total score label.
     * @return JPanel with total score label
     */
    private JPanel createTotalScorePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);
        totalScoreLabel = new JLabel(" ");
        totalScoreLabel.setForeground(Color.WHITE);
        totalScoreLabel.setOpaque(false);

        panel.add(totalScoreLabel);
        return panel;
    }

    /**
     * Inner class that handles button click events for scoring categories.
     */
    private class CategoryButtonListener implements ActionListener {
        private final String displayName;

        /**
         * Constructs the listener for the specified category display name.
         * @param displayName category display name
         */
        public CategoryButtonListener(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Invoked when a category button is clicked.
         * Attempts to score the category, disables button on success,
         * updates UI, saves final score if game over.
         * @param e the event triggered by button press
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String categoryKey = categoryMap.get(displayName);
            boolean success = gameLogic.scoreCategory(categoryKey);

            if (success) {
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setEnabled(false);

                int score = gameLogic.getScoreBoard().getCategoryScore(categoryKey);
                sourceButton.setText(displayName + " (" + score + ")");
                yahtzeeGui.refreshDiceHoldStyles();
                if (gameLogic.getScoreBoard().allCategoriesScored()) {
                    int totalScore = gameLogic.getScoreBoard().getTotalScore();

                    try {
                        ScoreFileWriter.writeFinalScore("Player1", totalScore);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(ScoreBoardGui.this,
                                "Failed to save score: " + ex.getMessage(),
                                "File Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(ScoreBoardGui.this,
                        "Please ROLL to score",
                        "Scoring Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Paints the background image scaled to the size of this panel.
     * @param g the Graphics context to paint on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * A JPanel subclass that paints a background image scaled to its size.
     */
    private static class BackgroundPanel extends JPanel {
        private final Image image;

        /**
         * Constructs a BackgroundPanel with the specified background image.
         * @param image the background image to paint
         */
        public BackgroundPanel(Image image) {
            this.image = image;
            setOpaque(false);
        }

        /**
         * Paints the background image scaled to the panel size.
         * @param g the Graphics context to paint on
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
