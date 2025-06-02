package pkg;

/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * Dice represents a 6-sided die that can be rolled and held.
 * It inherits behavior from AbstractDice and implements Rollable.
 */
public class Dice extends AbstractDice {
    
    /**
     * Constructs a Dice object with an initial roll.
     */
    public Dice() {
        super(); // calls AbstractDice constructor
        roll();  // initialize value
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                ", isHeld=" + isHeld +
                '}';
    }
}
