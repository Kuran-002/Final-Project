package pkg;

public class YahtzeeGameLogic {
    private int rollRemaining = 3;
    private DiceSet diceSet;
    private ScoreBoard scoreBoard;
    private RollButton rollButton;
    

    public YahtzeeGameLogic() 
    {
        diceSet = new DiceSet();
        scoreBoard = new ScoreBoard();
        
    }

    public String getDiceState() 
    {
        return diceSet.toString();  // Return the current dice state as a string
    }

    public int getRollRemaining() 
    {
        return rollRemaining;  // Get the number of rolls remaining
    }

    public boolean rollDice() 
    {
        if (rollRemaining > 0) 
        {
            diceSet.rollUnheld();  // Roll only unheld dice
            rollRemaining--;       // Decrease remaining rolls
            return true;           
        }
        return false;  // No rolls remaining
    }

    public boolean holdDice(int diceIndex) 
    {
        if (diceIndex >= 0 && diceIndex < 5) 
        {
            diceSet.holdOneDice(diceIndex);  // Hold the specified dice
            return true;
        }
        return false;  // Invalid dice index
    }

    public boolean unholdAllDice() {
        diceSet.unholdAll();  // Unhold all dice before the next roll
        return true;
    }

    public boolean scoreCategory(String category) 
    {
        switch (category.toLowerCase()) 
        {
            case "ones": return scoreCategoryOnes();
            case "twos": return scoreCategoryTwos();
            case "threes": return scoreCategoryThrees();
            case "fours": return scoreCategoryFours();
            case "fives": return scoreCategoryFives();
            case "sixes": return scoreCategorySixes();
            case "twoofakind": return scoreCategoryTwoOfAKind();
            case "threeofakind": return scoreCategoryThreeOfAKind();
            case "fourofakind": return scoreCategoryFourOfAKind();
            case "smallstraight": return scoreCategorySmallStraight();
            case "largestraight": return scoreCategoryLargeStraight();
            case "yahtzee": return scoreCategoryYahtzee();
            case "chance": return scoreCategoryChance();
            default: return false;  // Invalid category
        }
    }

    private boolean scoreCategoryOnes() {
        scoreBoard.calculateOnes(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryTwos() {
        scoreBoard.calculateTwos(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryThrees() {
        scoreBoard.calculateThrees(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryFours() {
        scoreBoard.calculateFours(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryFives() {
        scoreBoard.calculateFives(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategorySixes() {
        scoreBoard.calculateSixes(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryTwoOfAKind() {
        scoreBoard.calculateTwoOfaKind(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryThreeOfAKind() {
        scoreBoard.calculateThreeOfaKind(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryFourOfAKind() {
        scoreBoard.calculateFourOfaKind(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategorySmallStraight() {
        scoreBoard.calculateSmallStraight(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryLargeStraight() {
        scoreBoard.calculateLargeStraight(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryYahtzee() {
        scoreBoard.calculateYahtzee(diceSet.getDiceArray());
        return true;
    }

    private boolean scoreCategoryChance() {
        scoreBoard.calculateChance(diceSet.getDiceArray());
        return true;
    }

    public int getTotalScore() {
        return scoreBoard.getTotalScore();  // Return total score from score board
    }

    public boolean isGameOver() {
        return rollRemaining == 0;  // Game is over if no rolls are remaining
    }
}
