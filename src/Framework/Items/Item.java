package Framework.Items;

public abstract class Item {
    public String name = "";

    abstract String interact();

    public abstract String inspect();

    public abstract String getName();

    public static Item makeItem(String s){
        String[] itemData = s.split("\\*");

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
