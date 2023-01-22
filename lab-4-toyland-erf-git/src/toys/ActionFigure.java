package toys;

/**
 * ActionFigure class.
 *
 * @author erf6575
 */
public class ActionFigure extends Doll {

    // Defining constants
    public final static Color HAIR_COLOR = Color.ORANGE;
    public final static int MIN_ENERGY_LEVEL = 1;

    // Defining variables in ActionFigure
    private int energy;

    /**
     * Calls Doll's constructor and adds ActionFigure specific info
     * Sets hairColor at ORANGE
     *
     * @param name
     * @param age
     * @param speak
     */
    protected ActionFigure(String name, int age, String speak) {
        super(name, HAIR_COLOR, age, speak);
        this.energy = MIN_ENERGY_LEVEL;
    }

    /**
     * Special play text
     * Increases energy by 1
     * Calls Doll's specialPlay too
     *
     * @param minutes
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\t" + super.getName() + " kung foo chops with " + String.valueOf(energy * minutes) + " energy!");
        energy++;
        super.specialPlay(minutes);
    }

    public int getEnergyLevel() { return this.energy; }

    /**
     * ActionFigure has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * The Doll part of the string comes from Doll's toString()
     * E for the energy
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", ActionFigure{E:" + String.valueOf(this.energy) + "}"; }
}
