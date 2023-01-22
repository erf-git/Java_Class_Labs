package toys;

/**
 * RCCar class.
 *
 * @author erf6575
 */
public class RCCar extends BatteryPowered{

    // Defining constants
    public final static int STARTING_SPEED = 10;
    public final static int SPEED_INCREASE = 5;

    // Defining variables in RCCar
    private static int nextId = 300;
    private int speed;

    /**
     * Calls BatteryPowered's constructor and adds RCCar specific info
     * Speed starts at STARTING_SPEED
     *
     * @param name
     * @param numBatteries
     */
    protected RCCar(String name, int numBatteries) {
        super(nextId++, name, numBatteries);
        this.speed = STARTING_SPEED;
    }

    /**
     * Special play text
     * Calls BatteryPowered's useBatteries() taking minutes
     * Calls Toy's increaseWear() taking speed
     * Increases speed by SPEED_INCREASE
     *
     * @param minutes
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\t" + super.getName() + " races around at " + String.valueOf(getSpeed()) + "mph!");
        super.useBatteries(minutes);
        super.increaseWear(speed);
        this.speed += SPEED_INCREASE;
    }

    public int getSpeed() { return this.speed; }

    /**
     * RRCar has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * The BatteryPowered part of the string comes from BatteryPowered's toString()
     * S for the speed
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", RCCar{S:" + String.valueOf(getSpeed()) + "}"; }
}
