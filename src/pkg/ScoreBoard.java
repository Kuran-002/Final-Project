package pkg;

public class ScoreBoard {
    int ones = 0;
    int twos = 0;
    int threes = 0;
    int fours = 0;
    int fives = 0;
    int sixes = 0;
    int twoOfaKind = 0;
    int threeOfaKind = 0;
    int fourOfaKind = 0;
    int fullHouse = 0;
    int smallStraight = 0;
    int largeStraight = 0;
    int yahtzee = 0;
    int chance = 0;

    // Added boolean flags to track if the section has been scored
    boolean onesScored = false;
    boolean twosScored = false;
    boolean threesScored = false;
    boolean foursScored = false;
    boolean fivesScored = false;
    boolean sixesScored = false;
    boolean twoOfaKindScored = false;
    boolean threeOfaKindScored = false;
    boolean fourOfaKindScored = false;
    boolean fullHouseScored = false;
    boolean smallStraightScored = false;
    boolean largeStraightScored = false;
    boolean yahtzeeScored = false;
    boolean chanceScored = false;

    public ScoreBoard() {
    }

    public void resetBoard() {
        ones = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        twoOfaKind = 0;
        threeOfaKind = 0;
        fourOfaKind = 0;
        fullHouse = 0;
        smallStraight = 0;
        largeStraight = 0;
        yahtzee = 0;
        chance = 0;

        // Reset the score flags
        onesScored = false;
        twosScored = false;
        threesScored = false;
        foursScored = false;
        fivesScored = false;
        sixesScored = false;
        twoOfaKindScored = false;
        threeOfaKindScored = false;
        fourOfaKindScored = false;
        fullHouseScored = false;
        smallStraightScored = false;
        largeStraightScored = false;
        yahtzeeScored = false;
        chanceScored = false;
    }
    public boolean isOnesScored() {
        return onesScored;
    }

    public boolean isTwosScored() {
        return twosScored;
    }

    public boolean isThreesScored() {
        return threesScored;
    }

    public boolean isFoursScored() {
        return foursScored;
    }

    public boolean isFivesScored() {
        return fivesScored;
    }

    public boolean isSixesScored() {
        return sixesScored;
    }

    public boolean isTwoOfaKindScored() {
        return twoOfaKindScored;
    }

    public boolean isThreeOfaKindScored() {
        return threeOfaKindScored;
    }

    public boolean isFourOfaKindScored() {
        return fourOfaKindScored;
    }

    public boolean isFullHouseScored() {
        return fullHouseScored;
    }

    public boolean isSmallStraightScored() {
        return smallStraightScored;
    }

    public boolean isLargeStraightScored() {
        return largeStraightScored;
    }

    public boolean isYahtzeeScored() {
        return yahtzeeScored;
    }

    public boolean isChanceScored() {
        return chanceScored;
    }

    // Scoring methods
    public void calculateOnes(Dice[] dice) {
        if (!onesScored) {
            ones = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 1) {
                    ones += 1;
                }
            }
            onesScored = true;
        }
    }

    public void calculateTwos(Dice[] dice) {
        if (!twosScored) {
            twos = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 2) {
                    twos += 2;
                }
            }
            twosScored = true;
        }
    }

    public void calculateThrees(Dice[] dice) {
        if (!threesScored) {
            threes = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 3) {
                    threes += 3;
                }
            }
            threesScored = true;
        }
    }

    public void calculateFours(Dice[] dice) {
        if (!foursScored) {
            fours = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 4) {
                    fours += 4;
                }
            }
            foursScored = true;
        }
    }

    public void calculateFives(Dice[] dice) {
        if (!fivesScored) {
            fives = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 5) {
                    fives += 5;
                }
            }
            fivesScored = true;
        }
    }

    public void calculateSixes(Dice[] dice) {
        if (!sixesScored) {
            sixes = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == 6) {
                    sixes += 6;
                }
            }
            sixesScored = true;
        }
    }

    public void calculateTwoOfaKind(Dice[] dice) {
        if (!twoOfaKindScored) {
            int[] counts = new int[6];
            for (int i = 0; i < dice.length; i++) {
                counts[dice[i].getValue() - 1]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] >= 2) {
                    int total = 0;
                    for (int j = 0; j < dice.length; j++) {
                        total += dice[j].getValue();
                    }
                    twoOfaKind = total;
                    twoOfaKindScored = true;
                    return;
                }
            }
        }
    }

    public void calculateThreeOfaKind(Dice[] dice) {
        if (!threeOfaKindScored) {
            int[] counts = new int[6];
            for (int i = 0; i < dice.length; i++) {
                counts[dice[i].getValue() - 1]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] >= 3) {
                    int total = 0;
                    for (int j = 0; j < dice.length; j++) {
                        total += dice[j].getValue();
                    }
                    threeOfaKind = total;
                    threeOfaKindScored = true;
                    return;
                }
            }
        }
    }

    public void calculateFourOfaKind(Dice[] dice) {
        if (!fourOfaKindScored) {
            int[] counts = new int[6];
            for (int i = 0; i < dice.length; i++) {
                counts[dice[i].getValue() - 1]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] >= 4) {
                    int total = 0;
                    for (int j = 0; j < dice.length; j++) {
                        total += dice[j].getValue();
                    }
                    fourOfaKind = total;
                    fourOfaKindScored = true;
                    return;
                }
            }
        }
    }

    public void calculateFullHouse(Dice[] dice) {
        if (!fullHouseScored) {
            int[] counts = new int[6];
            for (int i = 0; i < dice.length; i++) {
                counts[dice[i].getValue() - 1]++;
            }
            boolean hasThree = false;
            boolean hasTwo = false;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == 3) {
                    hasThree = true;
                }
                if (counts[i] == 2) {
                    hasTwo = true;
                }
            }
            if (hasThree && hasTwo) {
                fullHouse = 25;
                fullHouseScored = true;
            }
        }
    }

    public void calculateSmallStraight(Dice[] dice) {
        if (!smallStraightScored) {
            boolean[] seen = new boolean[6];
            for (int i = 0; i < dice.length; i++) {
                seen[dice[i].getValue() - 1] = true;
            }
            if ((seen[0] && seen[1] && seen[2] && seen[3]) ||
                (seen[1] && seen[2] && seen[3] && seen[4]) ||
                (seen[2] && seen[3] && seen[4] && seen[5])) {
                smallStraight = 30;
                smallStraightScored = true;
            }
        }
    }

    public void calculateLargeStraight(Dice[] dice) {
        if (!largeStraightScored) {
            boolean[] seen = new boolean[6];
            for (int i = 0; i < dice.length; i++) {
                seen[dice[i].getValue() - 1] = true;
            }
            if ((seen[0] && seen[1] && seen[2] && seen[3] && seen[4]) ||
                (seen[1] && seen[2] && seen[3] && seen[4] && seen[5])) {
                largeStraight = 40;
                largeStraightScored = true;
            }
        }
    }

    public void calculateYahtzee(Dice[] dice) {
        if (!yahtzeeScored) {
            int value = dice[0].getValue();
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() != value) {
                    return;
                }
            }
            yahtzee = 50;
            yahtzeeScored = true;
        }
    }

    public void calculateChance(Dice[] dice) {
        if (!chanceScored) {
            chance = 0;
            for (int i = 0; i < dice.length; i++) {
                chance += dice[i].getValue();
            }
            chanceScored = true;
        }
    }

    // Getters for scores
    public int getOnes() { return ones; }
    public int getTwos() { return twos; }
    public int getThrees() { return threes; }
    public int getFours() { return fours; }
    public int getFives() { return fives; }
    public int getSixes() { return sixes; }
    public int getTwoOfaKind() { return twoOfaKind; }
    public int getThreeOfaKind() { return threeOfaKind; }
    public int getFourOfaKind() { return fourOfaKind; }
    public int getFullHouse() { return fullHouse; }
    public int getSmallStraight() { return smallStraight; }
    public int getLargeStraight() { return largeStraight; }
    public int getYahtzee() { return yahtzee; }
    public int getChance() { return chance; }

    public int getTotalScore() {
        return ones + twos + threes + fours + fives + sixes + twoOfaKind + threeOfaKind + fourOfaKind + fullHouse + smallStraight + largeStraight + yahtzee + chance;
    }
}
