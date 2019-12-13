package Framework.Items;

/**
 * The class for the knife item.
 *
 * @author Grayson Wagner
 * @author Matt Schwennesen
 * @author Max Jorgensen
 */
public class Knife extends Item {
    private String name = "KNIFE"; //Name of the knife.
    private String description = "A simple iron gladiatorial knife. It seems to have seen many battles in the arena. A letter 'S' is engraved on the pommel"; //The description of the knife.

    /**
     * Returns the description of the element.
     * @return description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Returns the name.
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the name
     * @return getName()
     */
    @Override
    public String toString() {
        return getName();
    }
}
