package toys;

/**
 * PlayDough class.
 *
 * @author erf6575
 */
public class PlayDough extends Toy {

    // Defining variables in PlayDough
    private static int nextId = 100;
    private Color color;

    // Defining constant
    public final static  double WEAR_MULTIPLIER = 0.05;

    /**
     * Calls Toy's constructor and adds PlayDough specific info
     *
     * @param name
     * @param color
     */
    protected PlayDough(String name, Color color) {
        super(nextId++, name);
        this.color = color;
    }

    /**
     * Special play text and special increase wear function
     *
     * @param minutes
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\tArts and crafting with " + getColor() + " " + super.getName());
        super.increaseWear(WEAR_MULTIPLIER * minutes);
    }

    public Color getColor() { return this.color; }

    /**
     * PlayDough has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * C for the color
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", PlayDough{C:" + getColor().toString() + "}"; }
}
