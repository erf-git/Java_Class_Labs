package mastermind;

import java.util.Scanner;
import static mastermind.MasterMind.*;

/**
 * @author Ethan R Feldman
 * RIT CSCI 142
 */
public class CodeBreaker {

    // Initializes the variables used
    private final static String QUIT = "q";
    private static Scanner input = new Scanner(System.in);
    private static double score = 0.0;

    // Creator method
    public CodeBreaker() {
        this.input = input;
        this.score = score;
    }

    /**
     * Takes your command line input and checks for 4 things
     * 1) if you want to quit
     * 2) if you did not input the right amount of digits
     * 3) if you inputted a number out of range
     * 4) if you inputted duplicate digits
     * It will loop until you made a valid guess
     *
     * @return a new guess with your command line input
     */
    public static Guess getGuess() {
        String currentGuess = input.nextLine();

        if (currentGuess.equals(QUIT)) {
            System.out.println("You have decided to quit, go bye!");
            return null;
        } else if (currentGuess.length() != SECRET_CODE_LENGTH) {
            System.out.println("Guess must have 4 digits!");
        } else if (!codeInRange(currentGuess)) {
            System.out.println("Guess must have all digits between 1 and 8!");
        } else if (!codeNoDuplicates(currentGuess)) {
            System.out.println("Guess cannot have duplicate digits!");
        } else {
            return new Guess(currentGuess);
        }

        // Recursion
        return getGuess();
    }

    /**
     * Increases score by amount
     *
     * @param amount
     */
    public static void increaseScore(int amount) {
        score += amount;
    }

    /**
     * Returns score from CodeBreaker class
     *
     * @return score
     */
    public static double getScore() {
        return score;
    }
}
