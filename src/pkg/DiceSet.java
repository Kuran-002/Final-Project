package pkg;

/**
 * Represents a set of five dice used in the Yahtzee game.
 * Provides methods to roll dice, hold/unhold individual dice, and access dice states.
 */
public class DiceSet {
    private static final int NUM_DICE = 5;
    private Dice[] diceArray;

    /**
     * Constructs a DiceSet with five newly instantiated Dice objects.
     */
    public DiceSet() {
        diceArray = new Dice[NUM_DICE];
        for (int i = 0; i < NUM_DICE; i++) {
            diceArray[i] = new Dice();
        }
    }

    /**
     * Rolls all dice in the set regardless of their held state.
     */
    public void rollAll() {
        for (int i = 0; i < NUM_DICE; i++) {
            diceArray[i].roll();
        }
    }

    /**
     * Rolls only the dice that are not currently held.
     */
    public void rollUnheld() {
        for (int i = 0; i < NUM_DICE; i++) {
            if (!diceArray[i].isHeld()) {
                diceArray[i].roll();
            }
        }
    }

    /**
     * Releases (unholds) all dice in the set.
     */
    public void unholdAll() {
        for (int i = 0; i < NUM_DICE; i++) {
            diceArray[i].release();
        }
    }

    /**
     * Holds the dice at the specified index.
     * 
     * @param i index of the dice to hold (0-based)
     */
    public void holdOneDice(int i) {
        diceArray[i].hold();
    }

    /**
     * Releases (unholds) the dice at the specified index.
     * 
     * @param i index of the dice to release (0-based)
     */
    public void unHoldOneDice(int i) {
        diceArray[i].release();
    }

    /**
     * Checks if the dice at the specified index is currently held.
     * 
     * @param i index of the dice to check (0-based)
     * @return true if the dice is held; false otherwise
     */
    public boolean oneDiceIsHeld(int i) {
        return diceArray[i].isHeld();
    }

    /**
     * Returns the Dice object at the specified index.
     * 
     * @param i index of the dice to retrieve (0-based)
     * @return the Dice at the specified index
     */
    public Dice getOneDice(int i) {
        return diceArray[i];
    }

    /**
     * Returns the array of all dice in this set.
     * 
     * @return array of Dice objects
     */
    public Dice[] getDiceArray() {
        return diceArray;
    }

    /**
     * Sets the array of dice to the specified array.
     * 
     * @param diceArray the new array of Dice objects
     */
    public void setDiceArray(Dice[] diceArray) {
        this.diceArray = diceArray;
    }

    /**
     * Returns a string representation of the DiceSet, including the value of each dice
     * and whether it is held.
     * 
     * @return string describing the dice values and held states
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Dice Set: ");
        for (int i = 0; i < diceArray.length; i++) {
            result.append(diceArray[i].getValue());
            if (diceArray[i].isHeld()) {
                result.append(" (held)");
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
