package Framework.Items;

/**
 * The class for the bubbles item.
 *
 * @author Matt Schwennesen
 * @author Max Jorgensen
 * @author Grayson Wagner
 *
 * Date Last Modified: 12/12/2019
 */
public class Bubbles extends Item {
    private String name = "BUBBLES"; //Name of the bubble.
    private String description = "It is a bubble made of a very fragrant soap. You can’t possibly fathom why one would be in a crypt."; //The description of the bubble.

    /**
     * Returns the description of the bubbles
     * @return description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Returns the name of the bubble.
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Return the name of the bubble.
     * @return getName()
     */
    @Override
    public String toString(){
        return getName();
    }
}
