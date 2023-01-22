package rit.cs;

/**
 * Handles integer expressions.
 *
 * @author erf6575
 */
public class IntExpression implements Expression{

    private int value;

    public IntExpression(int i) {
        this.value = i;
    }

    @Override
    public int evaluate() {
        return this.value;
    }

    @Override
    public String emit() {
        return String.valueOf(this.value);
    }
}
