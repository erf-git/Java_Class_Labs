package mastermind;

/**
 * @author Ethan R Feldman
 * RIT CSCI 142
 */
public class CodeMaker {

    // Initializes the variables used
    private String secretCode;

    public CodeMaker(String secret) {
        this.secretCode = secret;
    }

    /**
     * Calculates the number of correct and wrong positions of your guess digits with the secret code
     *
     * @param guess
     */
    public void checkGuess(Guess guess) {

        // Initializes guess string and secret code string
        String g = guess.getGuess();
        String s = this.secretCode;

        // Counters for correct digit positions and wrong digit positions
        int cPos = 0;
        int wPos = 0;

        // Goes through each digit of guess and secret code
        for (int i = 0; i < g.length(); i++) {

            // Finds if the char in g is within s
            if (s.indexOf(g.charAt(i)) > -1) {

                // Checks if the chars are at the same position in the string
                if (g.charAt(i) == s.charAt(i)) {
                    cPos++;
                    //System.out.println("Correct Position: " + String.valueOf(cPos) + ", g:" + g.charAt(i) + " s:" + s.charAt(i));
                } else {
                    wPos++;
                    //System.out.println("Wrong Position: " + String.valueOf(wPos) + ", g:" + g.charAt(i) + " s:" + s.charAt(i));
                }
            }
        }

        // Sets the correct and wrong position values
        guess.setCorrectPositions(cPos);
        guess.setWrongPositions(wPos);

        /**
         * WARNING: this will break if the guess is made with duplicate numbers
         *
         * Ex:
         * s: 1234
         * g: 1224
         *
         * The two 2's will mess up the count.
         */
    }

    /**
     * Returns the secret code
     *
     * @return
     */
    public String getSecretCode() {
        return this.secretCode;
    }
}
