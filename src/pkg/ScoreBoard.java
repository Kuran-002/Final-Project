package pkg;

/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * References:
 * ChatGPT, oracle.com
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * The ScoreBoard class manages the scoring logic and state for a Yahtzee game.
 * It tracks whether each scoring category has already been used and stores the
 * corresponding scores. It includes methods to calculate scores for all standard
 * Yahtzee categories, validate combinations like full house and straights, and 
 * compute the total game score. It also provides query methods to check scoring status 
 * and retrieve category scores by name.
 */

public class ScoreBoard {
    // Boolean flags indicating if each category has been scored
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

    // Scores for each category
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

    /**
     * Calculates and sets the score for the Ones category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateOnes(Dice[] dice) {
        if (!onesScored) {
            onesScore = calculateCategoryScore(dice, 1);
            onesScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Twos category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateTwos(Dice[] dice) {
        if (!twosScored) {
            twosScore = calculateCategoryScore(dice, 2);
            twosScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Threes category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateThrees(Dice[] dice) {
        if (!threesScored) {
            threesScore = calculateCategoryScore(dice, 3);
            threesScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Fours category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateFours(Dice[] dice) {
        if (!foursScored) {
            foursScore = calculateCategoryScore(dice, 4);
            foursScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Fives category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateFives(Dice[] dice) {
        if (!fivesScored) {
            fivesScore = calculateCategoryScore(dice, 5);
            fivesScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Sixes category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateSixes(Dice[] dice) {
        if (!sixesScored) {
            sixesScore = calculateCategoryScore(dice, 6);
            sixesScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Two of a Kind category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateTwoOfaKind(Dice[] dice) {
        if (!twoOfaKindScored) {
            twoOfaKindScore = hasOfAKind(dice, 2) ? sumAllDice(dice) : 0;
            twoOfaKindScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Three of a Kind category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateThreeOfaKind(Dice[] dice) {
        if (!threeOfaKindScored) {
            threeOfaKindScore = hasOfAKind(dice, 3) ? sumAllDice(dice) : 0;
            threeOfaKindScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Four of a Kind category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateFourOfaKind(Dice[] dice) {
        if (!fourOfaKindScored) {
            fourOfaKindScore = hasOfAKind(dice, 4) ? sumAllDice(dice) : 0;
            fourOfaKindScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Full House category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateFullHouse(Dice[] dice) {
        if (!fullHouseScored) {
            fullHouseScore = isFullHouse(dice) ? 25 : 0;
            fullHouseScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Small Straight category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateSmallStraight(Dice[] dice) {
        if (!smallStraightScored) {
            smallStraightScore = hasStraight(dice, 4) ? 30 : 0;
            smallStraightScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Large Straight category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateLargeStraight(Dice[] dice) {
        if (!largeStraightScored) {
            largeStraightScore = hasStraight(dice, 5) ? 40 : 0;
            largeStraightScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Yahtzee category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateYahtzee(Dice[] dice) {
        if (!yahtzeeScored) {
            yahtzeeScore = isYahtzee(dice) ? 50 : 0;
            yahtzeeScored = true;
            return true;
        }
        return false;
    }

    /**
     * Calculates and sets the score for the Chance category if not already scored.
     *
     * @param dice the array of Dice objects representing the current roll
     * @return true if the score was successfully calculated and set; false if already scored
     */
    public boolean calculateChance(Dice[] dice) {
        if (!chanceScored) {
            chanceScore = sumAllDice(dice);
            chanceScored = true;
            return true;
        }
        return false;
    }

    /**
     * Helper method to calculate score for categories based on matching dice values.
     *
     * @param dice the array of Dice objects
     * @param number the dice value to count (1 to 6)
     * @return the sum of dice matching the given number
     */
    private int calculateCategoryScore(Dice[] dice, int number) {
        int score = 0;
        for (Dice die : dice) {
            if (die.getValue() == number) {
                score += number;
            }
        }
        return score;
    }

    /**
     * Checks if the dice contain at least the specified number of identical dice.
     *
     * @param dice the array of Dice objects
     * @param kind the number of identical dice required
     * @return true if the condition is met; false otherwise
     */
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

    /**
     * Checks if the dice form a full house (3 of a kind + 2 of a kind).
     *
     * @param dice the array of Dice objects
     * @return true if the dice form a full house; false otherwise
     */
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

    /**
     * Checks if the dice contain a straight of the specified length.
     *
     * @param dice the array of Dice objects
     * @param length the length of the straight (e.g., 4 for small straight, 5 for large straight)
     * @return true if the dice contain a straight of the given length; false otherwise
     */
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

    /**
     * Checks if the dice are all the same value (Yahtzee).
     *
     * @param dice the array of Dice objects
     * @return true if all dice have the same value; false otherwise
     */
    private boolean isYahtzee(Dice[] dice) {
        int value = dice[0].getValue();
        for (Dice die : dice) {
            if (die.getValue() != value) return false;
        }
        return true;
    }

    /**
     * Calculates the sum of all dice values.
     *
     * @param dice the array of Dice objects
     * @return the total sum of all dice values
     */
    private int sumAllDice(Dice[] dice) {
        int sum = 0;
        for (Dice die : dice) {
            sum += die.getValue();
        }
        return sum;
    }

    /**
     * Gets the total score across all scored categories.
     *
     * @return the total score
     */
    public int getTotalScore() {
        return onesScore + twosScore + threesScore + foursScore + fivesScore + sixesScore
            + twoOfaKindScore + threeOfaKindScore + fourOfaKindScore + fullHouseScore
            + smallStraightScore + largeStraightScore + yahtzeeScore + chanceScore;
    }

    /**
     * Checks if all categories have been scored.
     *
     * @return true if all categories have been scored; false otherwise
     */
    public boolean allCategoriesScored() {
        return onesScored && twosScored && threesScored && foursScored && fivesScored && sixesScored
            && twoOfaKindScored && threeOfaKindScored && fourOfaKindScored && fullHouseScored
            && smallStraightScored && largeStraightScored && yahtzeeScored && chanceScored;
    }

    /**
     * Returns the score for a given category by name.
     *
     * @param category the category name (case-insensitive)
     * @return the score for the category, or 0 if the category name is invalid
     */
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

    // Individual getters to check if a category has been scored

    /** @return true if the Full House category has been scored */
    public boolean isFullHouseScored() { 
    	return fullHouseScored; 
    	}
    /** @return true if the Ones category has been scored */
    public boolean isOnesScored() { 
    	return onesScored; 
    	}
    /** @return true if the Twos category has been scored */
    public boolean isTwosScored() { 
    	return twosScored; 
    	}
    /** @return true if the Threes category has been scored */
    public boolean isThreesScored() { 
    	return threesScored; 
    	}
    /** @return true if the Fours category has been scored */
    public boolean isFoursScored() { 
    	return foursScored; 
    	}
    /** @return true if the Fives category has been scored */
    public boolean isFivesScored() { 
    	return fivesScored; 
    	}
    /** @return true if the Sixes category has been scored */
    public boolean isSixesScored() { 
    	return sixesScored; 
    	}
    /** @return true if the Two of a Kind category has been scored */
    public boolean isTwoOfaKindScored() { 
    	return twoOfaKindScored; 
    	}
    /** @return true if the Three of a Kind category has been scored */
    public boolean isThreeOfaKindScored() { 
    	return threeOfaKindScored; 
    	}
    /** @return true if the Four of a Kind category has been scored */
    public boolean isFourOfaKindScored() { 
    	return fourOfaKindScored; 
    	}
    /** @return true if the Small Straight category has been scored */
    public boolean isSmallStraightScored() { 
    	return smallStraightScored; 
    	}
    /** @return true if the Large Straight category has been scored */
    public boolean isLargeStraightScored() { 
    	return largeStraightScored; 
    	}
    /** @return true if the Yahtzee category has been scored */
    public boolean isYahtzeeScored() { 
    	return yahtzeeScored; 
    	}
    /** @return true if the Chance category has been scored */
    public boolean isChanceScored() { 
    	return chanceScored; 
    	}
}
