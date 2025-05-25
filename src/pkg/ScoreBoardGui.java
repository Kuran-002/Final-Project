package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoardGui extends JPanel {
    private YahtzeeGameLogic gameLogic;
    private YahtzeeGui yahtzeeGui;
    private JLabel totalScoreLabel;
    private JPanel categoriesPanel;
    private Image backgroundImage;

    private final Map<String, String> categoryMap = new LinkedHashMap<>();

    /**
     * Scales an image icon from the given path to the specified width and height.
     * @param path the path to the image file
     * @param width the target width
     * @param height the target height
     * @return a scaled ImageIcon
     */
    private ImageIcon scaleIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Constructs the ScoreBoardGui panel with the given game logic and main GUI references.
     * Sets up the layout, background, category buttons, and total score panel.
     * @param gameLogic the Yahtzee game logic instance
     * @param yahtzeeGui the main Yahtzee GUI instance for refreshing UI elements
     */
    public ScoreBoardGui(YahtzeeGameLogic gameLogic, YahtzeeGui yahtzeeGui) {
        this.gameLogic = gameLogic;
        this.yahtzeeGui = yahtzeeGui;
        this.backgroundImage = new ImageIcon("ScoreboardBackGround.png").getImage();

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
     * Sets up the mapping from category display names to internal category keys.
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
     * Initializes the category buttons with icons and adds them to the categories panel.
     */
    private void initializeCategories() {
        ImageIcon normalIcon = scaleIcon("NormalScore.png", 400, 50);
        ImageIcon hoverIcon = scaleIcon("HoverScore.png", 400, 50);
        ImageIcon pressedIcon = scaleIcon("ClickedScore.png", 400, 50);

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
     * Creates the total score panel with spacing but without visible total score text.
     * @return the JPanel containing an invisible placeholder label for spacing
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
     * ActionListener for category buttons. Handles scoring and updates UI accordingly.
     */
    private class CategoryButtonListener implements ActionListener {
        private final String displayName;

        /**
         * Constructs the listener for a specific category button.
         * @param displayName the display name of the category
         */
        public CategoryButtonListener(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Called when the category button is pressed.
         * disables the button, updates its text with the scored value,
         * and refreshes dice hold styles in the main GUI.
         * Shows a warning if category was already scored.
         * @param e the ActionEvent triggered by button press
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
            } else {
                JOptionPane.showMessageDialog(ScoreBoardGui.this,
                        "PLease ROLL to score",
                        "Scoring Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Paints the background image scaled to the panel size.
     * @param g the Graphics object for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * A JPanel subclass that paints a given image as its background.
     */
    private static class BackgroundPanel extends JPanel {
        private final Image image;

        /**
         * Constructs a BackgroundPanel with the specified background image.
         * @param image the Image to paint as background
         */
        public BackgroundPanel(Image image) {
            this.image = image;
            setOpaque(false);
        }

        /**
         * Paints the background image scaled to the panel size.
         * @param g the Graphics object for painting
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
