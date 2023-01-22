package toys;

/**
 * BatteryPowered abstract class.
 *
 * @author erf6575
 */
public abstract class BatteryPowered extends Toy {

    // Defining constants
    public final static int FULLY_CHARGED = 100;
    public final static int DEPLETED = 0;

    // Defining variables in BatterPowered toys
    private int batteryLevel;
    private int numBatteries;

    /**
     * Calls Toy's constructor and adds BatteryPowered specific info
     * BatteryPowered toys start at FULLY_CHARGED
     * Adds the number of batteries
     *
     * @param productCode
     * @param name
     * @param numBatteries
     */
    protected BatteryPowered(int productCode, String name, int numBatteries) {
        super(productCode, name);
        this.batteryLevel = FULLY_CHARGED;
        this.numBatteries = numBatteries;
    }

    public int getBatteryLevel() { return this.batteryLevel; }

    public int getNumBatteries() { return this.numBatteries; }

    /**
     * Batteries will be depleted by (minutes + numBatteries)
     * If batteryLevel is depleted set the batteryLevel to 0 and print out that its depleted
     * Recharge the batteries next and print out that it's recharged
     *
     * @param minutes
     */
    public void useBatteries(int minutes) {
        batteryLevel -= (minutes + numBatteries);
        if (batteryLevel <= DEPLETED) {
            batteryLevel = 0;
            System.out.println("\tDEPLETED:" + this.toString());
            batteryLevel = FULLY_CHARGED;
            System.out.println("\tRECHARGED:" + this.toString());
        }
    }

    /**
     * BatteryPowered has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * BL for the battery level
     * NB for the number of batteries
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", BatteryPowered{BL:" + String.valueOf(getBatteryLevel()) + ", NB:" + String.valueOf(getNumBatteries()) + "}"; }
}
