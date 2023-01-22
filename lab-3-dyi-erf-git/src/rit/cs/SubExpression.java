package rit.cs;

/**
 * Handles the "-" operator in the expression.
 *
 * @author erf6575
 */
public class SubExpression implements Expression {

    private Expression left;
    private Expression right;

    public SubExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return this.left.evaluate() - this.right.evaluate();
    }

    @Override
    public String emit() {
        return "(" + this.left.emit() + " - " + this.right.emit() + ")";
    }
}
