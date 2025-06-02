package pkg;

import java.util.Random;

/**
 * AbstractDice provides base implementation for dice behavior.
 * It implements Rollable and includes common fields like value and isHeld.
 */
public abstract class AbstractDice implements Rollable {
    protected int value;
    protected boolean isHeld;
    protected Random random;

    /**
     * Constructs an AbstractDice with a Random generator and default not held.
     */
    public AbstractDice() {
        random = new Random();
        isHeld = false;
    }

    /**
     * Rolls the die if not held.
     */
    @Override
    public void roll() {
        if (!isHeld) {
            value = random.nextInt(6) + 1;
        }
    }

    /**
     * Returns the value of the dice.
     */
    public int getValue() {
        return value;
    }

    /**
     * Holds the dice.
     */
    public void hold() {
        isHeld = true;
    }

    /**
     * Releases the dice.
     */
    public void release() {
        isHeld = false;
    }

    /**
     * Toggles hold state.
     */
    public void toggleHold() {
        isHeld = !isHeld;
    }

    /**
     * Checks if the dice is held.
     */
    public boolean isHeld() {
        return isHeld;
    }

    @Override
    public String toString() {
        return "AbstractDice{" +
                "value=" + value +
                ", isHeld=" + isHeld +
                '}';
    }
}
