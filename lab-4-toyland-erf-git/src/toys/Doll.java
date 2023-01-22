package toys;

/**
 * Doll class.
 *
 * @author erf6575
 */
public class Doll extends Toy {

    // Defining variables in Doll
    private static int nextId = 200;
    private Color hairColor;
    private int age;
    private String speak;

    /**
     * Calls Toy's constructor and adds Doll specific info
     *
     * @param name
     * @param hairColor
     * @param age
     * @param speak
     */
    protected Doll(String name, Color hairColor, int age, String speak) {
        super(nextId++, name);
        this.hairColor = hairColor;
        this.age = age;
        this.speak = speak;
    }

    /**
     * Special play text and increase wear is determined by doll age
     *
     * @param minutes
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\t" + super.getName() + " brushes their " + getHairColor() + " hair and says, \"" + getSpeak() + "\"");
        super.increaseWear(this.age);
    }

    public Color getHairColor() { return this.hairColor; }

    public int getAge() { return this.age; }

    public String getSpeak() { return this.speak; }

    /**
     * Doll has to display certain information:
     * The Toy part of the string comes from Toy's toString()
     * HC for the hair color
     * A for the age
     * S for the speak
     *
     * @return String with supporting info
     */
    @Override
    public String toString() { return super.toString() + ", Doll{HC:" + getHairColor().toString() + ", A:" + String.valueOf(getAge()) + ", S:" + getSpeak() + "}"; }
}
