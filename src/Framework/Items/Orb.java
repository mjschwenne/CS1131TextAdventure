package Framework.Items;

/**
 * The item class for the orb item.
 *
 * @author Max Jorgensen
 * @author Matt Schwennesen
 * @author Grayson Wagner
 *
 * Date Last Modified: 12/12/2019
 */
public class Orb extends Item {
    private String name = "ORB"; //Name of the orb
    private String description = "A curious device of some kind. You can feel space fold and twist around you. Could this be what you need to escape?"; //description of the orb.

    /**
     * Returns the description of the orb.
     * @return description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Returns the name of the orb.
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the orb.
     * @return getName()
     */
    @Override
    public String toString() {
        return getName();
    }
}
