package pkg;

import java.util.Random;

public class Dice {
    private int value;
    private boolean isHeld;
    private Random random;

    public Dice() {
        random = new Random();
        roll(); 
        isHeld = false;
    }

    public void roll() {
        if (!isHeld) {
            value = random.nextInt(6) + 1; 
        }
    }

    public int getValue() {
        return value;
    }

    public void hold() {
        isHeld = true;
    }

    public void release() {
        isHeld = false;
    }

    public void toggleHold() {
        isHeld = !isHeld;
    }

    public boolean isHeld() {
        return isHeld;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                ", isHeld=" + isHeld +
                '}';
    }
}
