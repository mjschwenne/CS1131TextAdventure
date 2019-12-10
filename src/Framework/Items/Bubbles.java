package Framework.Items;

public class Bubbles extends Item {
    private String name = "BUBBLES";
    private String description = "It is a bubble made of a very fragrant soap. You can’t possibly fathom why one would be in a crypt.";


    @Override
    public String interact() {
        return "The bubble pops";
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
