package Framework.Items;

/**
 * The Torch class for the torch item.
 *
 * @author Max Jorgensen
 * @author Grayson Wagner
 * @author Matt Schwennesen
 */
public class Torch extends Item {
    private String name = "TORCH"; //Name of the torch
    private String description = "A torch that burns bright. It doesn’t seem to decay or burn out. It gives you the creeps, but at least you can see."; //The description of the torch.

    /**
     * The inspect class returns the description.
     * @return description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Returns the name of the torch.
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the torch.
     * @return getName()
     */
    @Override
    public String toString() {
        return getName();
    }
}
