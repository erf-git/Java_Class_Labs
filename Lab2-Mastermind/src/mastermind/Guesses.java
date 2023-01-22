package mastermind;

import java.util.ArrayList;

/**
 * @author Ethan R Feldman
 * RIT CSCI 142
 */
public class Guesses {

    // Initializes the array list containing Guess objects
    private ArrayList<Guess> guessList;

    // Creates the Guesses object
    public Guesses() {
        this.guessList = new ArrayList<Guess>();
    }

    // Adds a guess object to Guesses object
    public void addGuess(Guess guess) {
        this.guessList.add(guess);
    }

    // Prints the guess objects in guesses array list
    public void displayGuesses() {

        for (Guess g : this.guessList) {
            System.out.println(g.toString());
        }

    }
}
