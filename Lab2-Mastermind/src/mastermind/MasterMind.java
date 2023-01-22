package mastermind;

import java.util.ArrayList;

/**
 * The main class for the Mastermind game.  The secret code can be provided
 * as the command line argument, otherwise it will be randomly generated.
 *
 * @author RIT CS
 * @author Ethan R Feldman
 */
public class MasterMind {
    /** the number of digits in the secret code */
    public final static int SECRET_CODE_LENGTH = 4;
    /** the minimum digit in the code */
    public final static int MIN_CODE_DIGIT = 1;
    /** the maximum digit in the code */
    public final static int MAX_CODE_DIGIT = 8;
    /** the number of turns in the game */
    private final static int MAX_TURNS = 10;

    /**
     * Check that the digits in a code are all in the valid range.
     *
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @return whether the code is valid or not
     */
    public static boolean codeInRange(String code) {
        for (int i = 0; i < code.length(); ++i) {
            if (Character.getNumericValue(code.charAt(i)) < MIN_CODE_DIGIT ||
                    Character.getNumericValue(code.charAt(i)) > MAX_CODE_DIGIT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check that there are no duplicates in the code.
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @rit.pre code has all digits in valid range
     * @return whether the code has no duplicates (true) or does have duplicates (false)
     */
    public static boolean codeNoDuplicates(String code) {
        return code.length() == code.chars().distinct().count();
    }

    /**
     * The method that plays the game
     * It will initialize all the variables needed for the game and then loop while you make guesses
     *
     * @param secretCode takes the secret number from the command line argument
     */
    public static void playGame(String secretCode) {

        // Initializes codeMaker with the argument in the configuration
        CodeMaker codeMaker = new CodeMaker(secretCode);

        // Initializes guessHistory
        Guesses guessHistory = new Guesses();

        // Initializes c for the while loop for the game
        int c = 0;

        // Start up messages
        System.out.println("Welcome to Mastermind!");
        System.out.println("Enter your 4 digit guesses (q to quit)");

        /**
         * You have MAX_TURNS amount of guesses
         * After each guess you will press enter
         * It will then check your guess, increase score relative to your guess, then print out all your guesses
         * Upon getting 4 correct positions (meaning you guessed the code), it will say you won
         * Upon reaching the MAX_TURNS of guesses, it will say you lost
         */
        while (c < MAX_TURNS) {
            Guess guess = CodeBreaker.getGuess();

            // If Guess is null that means you want to quit
            if (guess == null) {
                return;
            }

            codeMaker.checkGuess(guess);
            CodeBreaker.increaseScore((guess.getCorrectPositions()*2) + guess.getWrongPositions());
            guessHistory.addGuess(guess);
            guessHistory.displayGuesses();

            // Checks if you guessed correctly
            if (guess.getCorrectPositions() == 4) {
                System.out.println("You won! You guessed the secret code.");
                System.out.println("Code breaker score: " + CodeBreaker.getScore()/c);
                return;
            }
            c++;
        }
        // You ran out of guesses
        System.out.println("You lost! The secret code was: " + codeMaker.getSecretCode());
        System.out.println("Code breaker score: " + CodeBreaker.getScore()/MAX_TURNS);
        return;
    }

    /**
     * The main method verifies the command line code (if present) and
     * then plays the game.
     *
     * @param args command line arguments (the secret number, optionally)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Mastermind secret-number");
            return;
        } else {
            if (args[0].length() != SECRET_CODE_LENGTH) {
                System.out.println("Secret number " + args[0] + " is not length " + SECRET_CODE_LENGTH);
                return;
            } else if (!codeInRange(args[0])) {
                System.out.println("Secret number " + args[0] +
                        " is invalid.  Expect all digits to be between " + MIN_CODE_DIGIT + " and " + MAX_CODE_DIGIT);
                return;
            } else if (!codeNoDuplicates(args[0])) {
                System.out.println("Secret number " + args[0] + " is invalid.  Has duplicate digits.");
                return;
            }
        }

        // Plays the game taking the secret code
        playGame(args[0]);
    }
}
