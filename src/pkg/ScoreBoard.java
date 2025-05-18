package pkg;

public class ScoreBoard {
    // Boolean flags
    private boolean onesScored = false;
    private boolean twosScored = false;
    private boolean threesScored = false;
    private boolean foursScored = false;
    private boolean fivesScored = false;
    private boolean sixesScored = false;
    private boolean twoOfaKindScored = false;
    private boolean threeOfaKindScored = false;
    private boolean fourOfaKindScored = false;
    private boolean fullHouseScored = false;
    private boolean smallStraightScored = false;
    private boolean largeStraightScored = false;
    private boolean yahtzeeScored = false;
    private boolean chanceScored = false;

    // Scores
    private int onesScore = 0;
    private int twosScore = 0;
    private int threesScore = 0;
    private int foursScore = 0;
    private int fivesScore = 0;
    private int sixesScore = 0;
    private int twoOfaKindScore = 0;
    private int threeOfaKindScore = 0;
    private int fourOfaKindScore = 0;
    private int fullHouseScore = 0;
    private int smallStraightScore = 0;
    private int largeStraightScore = 0;
    private int yahtzeeScore = 0;
    private int chanceScore = 0;

    public boolean calculateOnes(Dice[] dice) {
        if (!onesScored) {
            onesScore = calculateCategoryScore(dice, 1);
            onesScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateTwos(Dice[] dice) {
        if (!twosScored) {
            twosScore = calculateCategoryScore(dice, 2);
            twosScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateThrees(Dice[] dice) {
        if (!threesScored) {
            threesScore = calculateCategoryScore(dice, 3);
            threesScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateFours(Dice[] dice) {
        if (!foursScored) {
            foursScore = calculateCategoryScore(dice, 4);
            foursScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateFives(Dice[] dice) {
        if (!fivesScored) {
            fivesScore = calculateCategoryScore(dice, 5);
            fivesScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateSixes(Dice[] dice) {
        if (!sixesScored) {
            sixesScore = calculateCategoryScore(dice, 6);
            sixesScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateTwoOfaKind(Dice[] dice) {
        if (!twoOfaKindScored) {
            twoOfaKindScore = hasOfAKind(dice, 2) ? sumAllDice(dice) : 0;
            twoOfaKindScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateThreeOfaKind(Dice[] dice) {
        if (!threeOfaKindScored) {
            threeOfaKindScore = hasOfAKind(dice, 3) ? sumAllDice(dice) : 0;
            threeOfaKindScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateFourOfaKind(Dice[] dice) {
        if (!fourOfaKindScored) {
            fourOfaKindScore = hasOfAKind(dice, 4) ? sumAllDice(dice) : 0;
            fourOfaKindScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateFullHouse(Dice[] dice) {
        if (!fullHouseScored) {
            fullHouseScore = isFullHouse(dice) ? 25 : 0;
            fullHouseScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateSmallStraight(Dice[] dice) {
        if (!smallStraightScored) {
            smallStraightScore = hasStraight(dice, 4) ? 30 : 0;
            smallStraightScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateLargeStraight(Dice[] dice) {
        if (!largeStraightScored) {
            largeStraightScore = hasStraight(dice, 5) ? 40 : 0;
            largeStraightScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateYahtzee(Dice[] dice) {
        if (!yahtzeeScored) {
            yahtzeeScore = isYahtzee(dice) ? 50 : 0;
            yahtzeeScored = true;
            return true;
        }
        return false;
    }

    public boolean calculateChance(Dice[] dice) {
        if (!chanceScored) {
            chanceScore = sumAllDice(dice);
            chanceScored = true;
            return true;
        }
        return false;
    }

    private int calculateCategoryScore(Dice[] dice, int number) {
        int score = 0;
        for (Dice die : dice) {
            if (die.getValue() == number) {
                score += number;
            }
        }
        return score;
    }

    private boolean hasOfAKind(Dice[] dice, int kind) {
        int[] counts = new int[6];
        for (Dice die : dice) {
            counts[die.getValue() - 1]++;
        }
        for (int count : counts) {
            if (count >= kind) return true;
        }
        return false;
    }

    private boolean isFullHouse(Dice[] dice) {
        int[] counts = new int[6];
        for (Dice die : dice) {
            counts[die.getValue() - 1]++;
        }
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int count : counts) {
            if (count == 3) hasThree = true;
            if (count == 2) hasTwo = true;
        }
        return hasThree && hasTwo;
    }

    private boolean hasStraight(Dice[] dice, int length) {
        boolean[] seen = new boolean[6];
        for (Dice die : dice) {
            seen[die.getValue() - 1] = true;
        }
        int currentStreak = 0;
        for (boolean present : seen) {
            if (present) {
                currentStreak++;
                if (currentStreak >= length) return true;
            } else {
                currentStreak = 0;
            }
        }
        return false;
    }

    private boolean isYahtzee(Dice[] dice) {
        int value = dice[0].getValue();
        for (Dice die : dice) {
            if (die.getValue() != value) return false;
        }
        return true;
    }

    private int sumAllDice(Dice[] dice) {
        int sum = 0;
        for (Dice die : dice) {
            sum += die.getValue();
        }
        return sum;
    }

    public int getTotalScore() {
        return onesScore + twosScore + threesScore + foursScore + fivesScore + sixesScore
            + twoOfaKindScore + threeOfaKindScore + fourOfaKindScore + fullHouseScore
            + smallStraightScore + largeStraightScore + yahtzeeScore + chanceScore;
    }

    public boolean allCategoriesScored() {
        return onesScored && twosScored && threesScored && foursScored && fivesScored && sixesScored
            && twoOfaKindScored && threeOfaKindScored && fourOfaKindScored && fullHouseScored
            && smallStraightScored && largeStraightScored && yahtzeeScored && chanceScored;
    }

    public int getCategoryScore(String category) {
        switch (category.toLowerCase()) {
            case "ones": return onesScore;
            case "twos": return twosScore;
            case "threes": return threesScore;
            case "fours": return foursScore;
            case "fives": return fivesScore;
            case "sixes": return sixesScore;
            case "twoofakind": return twoOfaKindScore;
            case "threeofakind": return threeOfaKindScore;
            case "fourofakind": return fourOfaKindScore;
            case "fullhouse": return fullHouseScore;
            case "smallstraight": return smallStraightScore;
            case "largestraight": return largeStraightScore;
            case "yahtzee": return yahtzeeScore;
            case "chance": return chanceScore;
            default: return 0;
        }
    }

    public boolean isFullHouseScored() { return fullHouseScored; }
    public boolean isOnesScored() { return onesScored; }
    public boolean isTwosScored() { return twosScored; }
    public boolean isThreesScored() { return threesScored; }
    public boolean isFoursScored() { return foursScored; }
    public boolean isFivesScored() { return fivesScored; }
    public boolean isSixesScored() { return sixesScored; }
    public boolean isTwoOfaKindScored() { return twoOfaKindScored; }
    public boolean isThreeOfaKindScored() { return threeOfaKindScored; }
    public boolean isFourOfaKindScored() { return fourOfaKindScored; }
    public boolean isSmallStraightScored() { return smallStraightScored; }
    public boolean isLargeStraightScored() { return largeStraightScored; }
    public boolean isYahtzeeScored() { return yahtzeeScored; }
    public boolean isChanceScored() { return chanceScored; }
}
