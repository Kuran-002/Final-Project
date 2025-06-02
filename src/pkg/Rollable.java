package pkg;

/**
 * An interface that defines a rollable object.
 * Used for polymorphism across different dice types or rollable objects.
 */
public interface Rollable {
    /**
     * Rolls the object to produce a result.
     */
    void roll();
}
