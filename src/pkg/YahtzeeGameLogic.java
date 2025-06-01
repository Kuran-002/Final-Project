package pkg;

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
 * Handles the core logic for a game of Yahtzee. This includes rolling dice, holding/unholding dice,
 * scoring in various categories, and determining when the game is over. Manages the state of dice 
 * using a DiceSet, and scoring via a ScoreBoard. Also supports game reset and event notification 
 * through a GameListener interface.
 */

public class YahtzeeGameLogic {
    private int rollRemaining = 3;
    private DiceSet diceSet;
    private ScoreBoard scoreBoard;

    private boolean hasRolledSinceScore = false;

    private boolean holdLocked = true;

    /**
     * Constructs a new Yahtzee game logic instance with default settings.
     */
    public YahtzeeGameLogic() {
        diceSet = new DiceSet();
        scoreBoard = new ScoreBoard();
        hasRolledSinceScore = false;
        holdLocked = true;
    }

    /**
     * Gets the current dice state as a string.
     *
     * @return the string representation of the current dice values.
     */
    public String getDiceState() {
        return diceSet.toString();
    }

    /**
     * Gets the number of rolls remaining in the current turn.
     *
     * @return the number of rolls left (0 to 3).
     */
    public int getRollRemaining() {
        return rollRemaining;
    }

    /**
     * Rolls all dice that are not currently held, if rolls remain.
     *
     * @return true if the dice were rolled successfully; false if no rolls remain.
     */
    public boolean rollDice() {
        if (rollRemaining > 0) {
            diceSet.rollUnheld();
            rollRemaining--;
            hasRolledSinceScore = true;
            holdLocked = false;
            return true;
        }
        return false;
    }

    /**
     * Holds or unholds a single die specified by its index.
     * Holding is locked before the first roll of a turn.
     *
     * @param diceIndex the zero-based index of the die to hold or unhold.
     * @return true if the hold status was successfully toggled; false if invalid index or hold locked.
     */
    public boolean holdDice(int diceIndex) {
        if (holdLocked) {
            return false;
        }
        if (diceIndex >= 0 && diceIndex < diceSet.getDiceArray().length) {
            diceSet.holdOneDice(diceIndex);
            return true;
        }
        return false;
    }

    /**
     * Unholds all dice (sets all dice as not held).
     */
    public void unholdAllDice() {
        diceSet.unholdAll();
    }

    /**
     * Scores the dice for the specified category if scoring is allowed and category not already scored.
     * Resets roll count and unlocks holding for the next turn if scoring succeeds.
     *
     * @param category the name of the scoring category (case insensitive).
     * @return true if scoring was successful; false if category invalid, already scored, or scoring not allowed.
     */
    public boolean scoreCategory(String category) {
        if (category == null) return false;

        if (!hasRolledSinceScore) return false;

        category = category.trim().toLowerCase();
        boolean success = false;
        Dice[] dice = diceSet.getDiceArray();

        if (isCategoryScored(category)) {
            return false;
        }

        switch (category) {
            case "ones":          success = scoreBoard.calculateOnes(dice); break;
            case "twos":          success = scoreBoard.calculateTwos(dice); break;
            case "threes":        success = scoreBoard.calculateThrees(dice); break;
            case "fours":         success = scoreBoard.calculateFours(dice); break;
            case "fives":         success = scoreBoard.calculateFives(dice); break;
            case "sixes":         success = scoreBoard.calculateSixes(dice); break;
            case "twoofakind":    success = scoreBoard.calculateTwoOfaKind(dice); break;
            case "threeofakind":  success = scoreBoard.calculateThreeOfaKind(dice); break;
            case "fourofakind":   success = scoreBoard.calculateFourOfaKind(dice); break;
            case "fullhouse":     success = scoreBoard.calculateFullHouse(dice); break;
            case "smallstraight": success = scoreBoard.calculateSmallStraight(dice); break;
            case "largestraight": success = scoreBoard.calculateLargeStraight(dice); break;
            case "yahtzee":       success = scoreBoard.calculateYahtzee(dice); break;
            case "chance":        success = scoreBoard.calculateChance(dice); break;
            default: return false;
        }

        if (success) {
            rollRemaining = 3;
            diceSet.unholdAll();
            hasRolledSinceScore = false;
            holdLocked = true;

            if (gameListener != null) {
                gameListener.onRollRemainingReset(rollRemaining);
                if (isGameOver()) {
                    gameListener.onGameOver(getTotalScore());
                }
            }
        }

        return success;
    }

    /**
     * Checks if the specified category has already been scored.
     *
     * @param category the category name (case insensitive).
     * @return true if the category is already scored; false otherwise.
     */
    private boolean isCategoryScored(String category) {
        switch (category) {
            case "ones":          return scoreBoard.isOnesScored();
            case "twos":          return scoreBoard.isTwosScored();
            case "threes":        return scoreBoard.isThreesScored();
            case "fours":         return scoreBoard.isFoursScored();
            case "fives":         return scoreBoard.isFivesScored();
            case "sixes":         return scoreBoard.isSixesScored();
            case "twoofakind":    return scoreBoard.isTwoOfaKindScored();
            case "threeofakind":  return scoreBoard.isThreeOfaKindScored();
            case "fourofakind":   return scoreBoard.isFourOfaKindScored();
            case "fullhouse":     return scoreBoard.isFullHouseScored();
            case "smallstraight": return scoreBoard.isSmallStraightScored();
            case "largestraight": return scoreBoard.isLargeStraightScored();
            case "yahtzee":       return scoreBoard.isYahtzeeScored();
            case "chance":        return scoreBoard.isChanceScored();
            default: return false;
        }
    }

    /**
     * Gets the total score across all categories.
     *
     * @return the sum of all scored category points.
     */
    public int getTotalScore() {
        return scoreBoard.getTotalScore();
    }

    /**
     * Checks if the game is over, meaning all categories have been scored.
     *
     * @return true if the game is complete; false otherwise.
     */
    public boolean isGameOver() {
        return scoreBoard.allCategoriesScored();
    }

    /**
     * Gets the current DiceSet object.
     *
     * @return the DiceSet representing the dice in play.
     */
    public DiceSet getDiceSet() {
        return diceSet;
    }

    /**
     * Gets the ScoreBoard object that manages scoring.
     *
     * @return the ScoreBoard instance.
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Resets the game state for a new game, including dice, scores, rolls, and locks.
     */
    public void resetGame() {
        rollRemaining = 3;
        diceSet = new DiceSet();
        scoreBoard = new ScoreBoard();
        hasRolledSinceScore = false;
        holdLocked = true;

        if (gameListener != null) {
            gameListener.onRollRemainingReset(rollRemaining);
        }
    }

    /**
     * Checks if scoring is currently allowed, which requires having rolled and rolls left >= 0.
     *
     * @return true if scoring can be done now; false otherwise.
     */
    public boolean canScoreNow() {
        return hasRolledSinceScore && rollRemaining >= 0;
    }

    /**
     * Indicates whether the hold feature is currently locked (disallowed).
     *
     * @return true if holds are locked; false if holds can be toggled.
     */
    public boolean isHoldLocked() {
        return holdLocked;
    }

    private GameListener gameListener;

    /**
     * Sets a listener to receive game state events such as roll reset and game over.
     *
     * @param listener the GameListener to notify.
     */
    public void setGameListener(GameListener listener) {
        this.gameListener = listener;
    }

    /**
     * Interface to listen for game events.
     */
    public interface GameListener {
        /**
         * Called when the number of rolls remaining is reset.
         *
         * @param newRolls the new number of rolls available.
         */
        void onRollRemainingReset(int newRolls);

        /**
         * Called when the game is over.
         *
         * @param finalScore the final total score.
         */
        void onGameOver(int finalScore);
    }
}
