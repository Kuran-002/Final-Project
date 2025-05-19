package pkg;

public class YahtzeeGameLogic {
    private int rollRemaining = 3;
    private DiceSet diceSet;
    private ScoreBoard scoreBoard;

    private boolean hasRolledSinceScore = false;

    // NEW: Lock holding dice toggle after scoring until first roll
    private boolean holdLocked = true;

    public YahtzeeGameLogic() {
        diceSet = new DiceSet();
        scoreBoard = new ScoreBoard();
        hasRolledSinceScore = false;
        holdLocked = true;  // locked at game start (no dice held)
    }

    public String getDiceState() {
        return diceSet.toString();
    }

    public int getRollRemaining() {
        return rollRemaining;
    }

    public boolean rollDice() {
        if (rollRemaining > 0) {
            diceSet.rollUnheld();
            rollRemaining--;
            hasRolledSinceScore = true;

            // Unlock holding dice after first roll
            holdLocked = false;

            return true;
        }
        return false;
    }

    public boolean holdDice(int diceIndex) {
        if (holdLocked) {
            // Holding is locked, do nothing
            return false;
        }
        if (diceIndex >= 0 && diceIndex < diceSet.getDiceArray().length) {
            diceSet.holdOneDice(diceIndex);
            return true;
        }
        return false;
    }

    public void unholdAllDice() {
        diceSet.unholdAll();
    }

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

            // Lock holding dice again after scoring
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

    public int getTotalScore() {
        return scoreBoard.getTotalScore();
    }

    public boolean isGameOver() {
        return scoreBoard.allCategoriesScored();
    }

    public DiceSet getDiceSet() {
        return diceSet;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void resetGame() {
        rollRemaining = 3;
        diceSet = new DiceSet();
        scoreBoard = new ScoreBoard();
        hasRolledSinceScore = false;
        holdLocked = true;

        // Notify listener about reset to update GUI
        if (gameListener != null) {
            gameListener.onRollRemainingReset(rollRemaining);
        }
    }

    public boolean canScoreNow() {
        return hasRolledSinceScore && rollRemaining >= 0;
    }

    // NEW: expose hold lock status for GUI to check
    public boolean isHoldLocked() {
        return holdLocked;
    }

    private GameListener gameListener;

    public void setGameListener(GameListener listener) {
        this.gameListener = listener;
    }

    public interface GameListener {
        void onRollRemainingReset(int newRolls);
        void onGameOver(int finalScore);
    }
}
