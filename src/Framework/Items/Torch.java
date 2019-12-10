package Framework.Items;

public class Torch extends Item {
    private String name = "TORCH";
    private String description = "A torch that burns bright. It doesn’t seem to decay or burn out. It gives you the creeps, but at least you can see.";


    @Override
    public String interact() {
        return "";
    }

    @Override
    public String inspect() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
