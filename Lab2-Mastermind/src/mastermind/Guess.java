package mastermind;

/**
 * @author Ethan R Feldman
 * RIT CSCI 142
 */
public class Guess {

    // Initializing variables
    private static int currentGuessNumber = 1;
    private int guessNumber;
    private String guess;
    private int correctPositions;
    private int wrongPositions;

    // Creating the Guess object with its variables
    public Guess(String guess) {
        this.guess = guess;
        this.guessNumber = currentGuessNumber;
        currentGuessNumber++;
        this.correctPositions = 0;
        this.wrongPositions = 0;
    }

    // Returns correct positions
    public int getCorrectPositions() {
        return this.correctPositions;
    }

    // Returns wrong positions
    public int getWrongPositions() {
        return this.wrongPositions;
    }

    // Sets the correct positions
    public void setCorrectPositions(int correctPositions) {
        this.correctPositions = correctPositions;
    }

    // Sets the wrong positions
    public void setWrongPositions(int wrongPositions) {
        this.wrongPositions = wrongPositions;
    }

    // Returns the guess
    public String getGuess() {
        return this.guess;
    }

    // Returns the guess in this string format
    @Override
    public String toString() {
        return "Guess #" + this.guessNumber + ": " + this.guess + " (B:" + this.correctPositions + " W:" + this.wrongPositions + ")";
    }
}
