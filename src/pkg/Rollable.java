package pkg;
/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * 
 * References:
 * Chatgpt, oracle.com
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * An interface that defines a rollable object.
 * Used for polymorphism across different dice types or rollable objects.the remaining roll count display and rolls only the unheld dice in the UI.
 */
public interface Rollable {
    /**
     * Rolls the object to produce a result.
     */
    void roll();
}
