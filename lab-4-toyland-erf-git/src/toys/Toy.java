package toys;

/**
 * Toy abstract class.
 *
 * @author erf6575
 */
public abstract class Toy implements IToy{

    // Defining variables in Toy
    private int productCode;
    private String name;
    private int happiness;
    private double wear;

    // Defining constants
    public final static int INITIAL_HAPPINESS = 0;
    public final static int MAX_HAPPINESS = 100;
    public final static double INITIAL_WEAR = 0.0;

    /**
     * Toy constructor using its variables
     *
     * @param productCode
     * @param name
     */
    protected Toy(int productCode, String name) {
        this.productCode = productCode;
        this.name = name;
        this.happiness = INITIAL_HAPPINESS;
        this.wear = INITIAL_WEAR;
    }

    @Override
    public int getProductCode() { return this.productCode; }

    @Override
    public String getName() { return this.name; }

    @Override
    public int getHappiness() { return this.happiness; }

    /**
     * If the toy's happiness is >= the max happiness -> the toy is retired
     *
     * @return boolean
     */
    @Override
    public boolean isRetired() {
        if (this.happiness >= MAX_HAPPINESS) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double getWear() { return this.wear; }

    /**
     * Increases toy wear by a defined amount
     *
     * @param amount
     */
    @Override
    public void increaseWear(double amount) {
        this.wear += amount;
    }

    /**
     * Takes time playing in minutes
     * Prints that the toy is being played with
     * Calls the toy's unique play method (specialPlay)
     * Increases the toy's happiness by minutes
     * Checks if the toy becomes retired after play, and prints a retired message if so
     *
     * @param minutes
     */
    @Override
    public void play(int minutes) {
        System.out.println("PLAYING(" + String.valueOf(minutes) + "): " + this.toString());
        specialPlay(minutes);
        this.happiness += minutes;
        if (this.isRetired()) {
            System.out.println("RETIRED: " + this.toString());
        }
    }
    protected abstract void specialPlay(int minutes);

    /**
     * Toy has to display certain information:
     * PC for product code
     * N for name
     * H for current happiness
     * R for retired (true or false)
     * W for current wear
     *
     * @return String with supporting info
     */
    @Override
    public String toString() {
        return "Toy{PC:" + this.productCode + ", N:" + this.name + ", H:" + this.happiness + ", R:" + this.isRetired() + ", W:" + this.wear + "}";
    }
}
