package toys;

/**
 * Robot class.
 *
 * @author erf6575
 */
public class Robot extends BatteryPowered {

    // Defining constants
    public final static int FLY_SPEED = 25;
    public final static int RUN_SPEED = 10;
    public final static int INITIAL_SPEED = 0;

    // Defining variables in Robot
    private static int nextId = 400;
    private boolean isFlying;
    private int distance;

    /**
     * Calls BatteryPowered's constructor and adds Robot specific info
     * boolean -> isFlying
     * INITIAL_SPEED -> distance
     *
     * @param name
     * @param numBatteries
     * @param isFlying
     */
    protected Robot(String name, int numBatteries, boolean isFlying) {
        super(nextId++, name, numBatteries);
        this.isFlying = isFlying;
        this.distance = INITIAL_SPEED;
    }

    /**
     * Depening on if the robot is flying or not, it will have a different specialPlay
     * Flying:
     *     distance increased by (minutes * FLY_SPEED)
     *     play print
     *     call's Toy's increaseWear() by FLY_SPEED
     * Not Flying:
     *     distance increased by (minutes * RUN_SPEED)
     *     play print
     *     call's Toy's increaseWear() by RUN_SPEED
     * Calls BatteryPowered's useBatteries() by minutes
     *
     * @param minutes
     */
    @Override
    protected void specialPlay(int minutes) {
        if (isFlying) {
            distance += (minutes * FLY_SPEED);
            System.out.println("\t" + super.getName() + " is flying around with total distance: " + String.valueOf(getDistance()));
            super.increaseWear(FLY_SPEED);
        } else {
            distance += (minutes * RUN_SPEED);
            System.out.println("\t" + super.getName() + " is running around with total distance: " + String.valueOf(getDistance()));
            super.increaseWear(RUN_SPEED);
        }
        super.useBatteries(minutes);
    }

    public boolean isFlying() { return this.isFlying;}

    public int getDistance() { return this.distance; }

    /**
     * Robot has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * The BatteryPowered part of the string comes from BatteryPowered's toString()
     * F for flying or not (true or false)
     * D for distance
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", Robot{F:" + this.isFlying + ", D:" + String.valueOf(getDistance()) + "}"; }
}
