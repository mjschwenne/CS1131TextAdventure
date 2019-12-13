package Framework.Items;

/**
 * This abstract class contains all the shared attributes for items.
 *
 * @author Max Jorgensen
 * @author Matt Schwennesen
 * @author Grayson Wagner
 *
 * Date Last Modified: 12/12/2019
 */
public abstract class Item {
    public String name = ""; //The name of the object

    /**
     * Allows all inheriting items to be inspected by the player.
     * @return item description
     */
    public abstract String inspect();

    /**
     * Allows all inheriting items to return its name.
     * @return the name of the item.
     */
    public abstract String getName();

    /**
     * Creates each item in the game.
     * @param s
     * @return Item objects
     */
    public static Item makeItem(String s){
        String[] itemData = s.split("_");

        switch (itemData[0]){
            case "ORB":
                return new Orb();
            case "TORCH":
                return new Torch();
            case "SCROLL":
                return new Scroll(itemData[1]);
            case "KNIFE":
                return new Knife();
            case "BUBBLES":
                return new Bubbles();
        }
        return null;
    }
}
