package pkg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lead Author(s):
 * @author Khanh Bao Luong
 * 
 * References:
 * oracle.com
 * 
 * Version/date: 06/01/2025
 * 
 * Responsibilities of class:
 * ScoreFileWriter handles writing the final score of a player to a text file.
 * It appends the player's name, score, and timestamp to a file named "FinalScore.txt".
 * This class provides static utility methods to facilitate persistent storage
 * of game results for record-keeping or later review.
 */
public class ScoreFileWriter {

    /**
     * Writes the player's final score along with a timestamp to the file "FinalScore.txt".
     * The entry is appended to the file without overwriting existing data.
     *
     * @param playerName the name of the player whose score is being recorded
     * @param score the final score achieved by the player
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void writeFinalScore(String playerName, int score) throws IOException {
        String filename = "FinalScore.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(playerName + " | Score: " + score + " | Time: " + timestamp);
            writer.newLine();
        }
    }
}
