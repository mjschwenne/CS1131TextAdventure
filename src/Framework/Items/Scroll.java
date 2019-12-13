package Framework.Items;

/**
 * The class for the scroll item.
 *
 * @author Grayson Wagner
 * @author Matt Schwennesen
 * @author Max Jorgensen
 *
 * Date Last Modified: 12/12/2019
 */
public class Scroll extends Item {
    private String name = "SCROLL"; //Name of the scroll
    private String description; //The description of the scroll

    /**
     * Constructor so that scrolls can have different text on them.
     * @param description
     */
    public Scroll(String description){
        this.description = description;
    }

    /**
     * Returns the text on the scroll.
     * @return description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Returns the name of the scroll
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the scroll.
     * @return getName()
     */
    @Override
    public String toString() {
        return getName();
    }
}
