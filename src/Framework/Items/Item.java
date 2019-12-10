package Framework.Items;

public abstract class Item {
    public String name = "";

    abstract String interact();

    abstract String inspect();

    abstract String getName();

    public static Item makeItem(String s){
        String[] itemData = s.split("\\*");

        switch (itemData[0]){
            case "ORB":
                return new Orb();
            case "Torch":
                return new Torch();
            case "Scroll":
                return new Scroll(itemData[1]);
            case "Knife":
                return new Knife();
            case "Bubbles":
                return new Bubbles();
        }
        return null;
    }
}
