package rit.stu;

import rit.cs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This is the calculator interpreter using prefix expression notation
 *
 * @author erf6575
 */
public class DYI {

    /**
     * Recursively goes through your input (arraylist of Strings).
     *
     * @param token
     * @return the final value of your expression
     */
    private static Expression Parse(ArrayList<String> token) {

        // Pops the first string from the token arraylist.
        String t = token.remove(0);

        // Depending on what 't' is, it will call different functions in recursion.
        return switch (t) {
            case "+" -> new AddExpression(Parse(token), Parse(token));
            case "/" -> new DivExpression(Parse(token), Parse(token));
            case "%" -> new ModExpression(Parse(token), Parse(token));
            case "*" -> new MulExpression(Parse(token), Parse(token));
            case "-" -> new SubExpression(Parse(token), Parse(token));
            default -> new IntExpression(Integer.parseInt(t));
        };
    }

    public static void main(String[] args) {

        // Into information printed at the beginning
        System.out.println("Hello! I am your interpreter Tep :)");
        System.out.println("Enter a prefix expression using integers, '+', '-', '/', '*', and '%' or enter 'quit' to exit the program.");

        // The scanner is initiated, and it looks for your first input.
        Scanner Tep = new Scanner(System.in);
        String input = Tep.nextLine();

        // As long as your input does not equal 'quit' it will keep parsing your expression.
        while (!input.equals("quit")) {

            // How I implemented Parse, it will remove from the input arraylist until there is nothing left
            // so I have to copy the input arraylist for the second evaluate function.
            ArrayList<String> inputTokens = new ArrayList<>(Arrays.asList(input.split(" ")));
            ArrayList<String> inputTokensCopy = new ArrayList<>(inputTokens);

            // Calculates and prints the emit() and evaluate() functions.
            System.out.println("Emit(): " + Parse(inputTokens).emit());
            System.out.println("Evaluate(): " + Parse(inputTokensCopy).evaluate());

            // Looks for next input expression
            input = Tep.nextLine();
        }

        // When you enter 'quit' it will end the program and leave you with a message.
        System.out.println("Goodbye :(");
    }
}