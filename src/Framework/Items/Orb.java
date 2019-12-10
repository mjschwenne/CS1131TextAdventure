package Framework.Items;

public class Orb extends Item {
    private String name = "Orb of Space-warp";
    private String description = "A curious device of some kind. You can feel space fold and twist around you. Could this be what you need to escape?";

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
}
