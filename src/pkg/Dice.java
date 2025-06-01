package pkg;

import java.util.Random;
/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * 
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * This class represents a single six-sided die. It can be rolled to produce
 * a random value between 1 and 6. It also supports a "hold" feature to 
 * prevent rolling when necessary, useful for games like Yahtzee.
 */
public class Dice {
    private int value;
    private boolean isHeld;
    private Random random;

    /**
     * Constructs a Dice object with an initial random value and not held.
     */
    public Dice() {
        random = new Random();
        roll();
        isHeld = false;
    }

    /**
     * Rolls the dice to get a new value between 1 and 6, 
     * but only if the dice is not held.
     */
    public void roll() {
        if (!isHeld) {
            value = random.nextInt(6) + 1;
        }
    }

    /**
     * Returns the current value of the dice.
     * @return the dice value (1-6)
     */
    public int getValue() {
        return value;
    }

    /**
     * Holds the dice, preventing it from rolling.
     */
    public void hold() {
        isHeld = true;
    }

    /**
     * Releases the dice, allowing it to roll.
     */
    public void release() {
        isHeld = false;
    }

    /**
     * Toggles the held state of the dice.
     * If it was held, it becomes released; if released, becomes held.
     */
    public void toggleHold() {
        isHeld = !isHeld;
    }

    /**
     * Checks if the dice is currently held.
     * @return true if held, false otherwise
     */
    public boolean isHeld() {
        return isHeld;
    }

    /**
     * Returns a string representation of the dice, including its value and held status.
     * @return string describing the dice state
     */
    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                ", isHeld=" + isHeld +
                '}';
    }
}
