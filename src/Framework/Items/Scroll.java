package Framework.Items;

public class Scroll extends Item {
    private String name;
    private String description;

    public Scroll(String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String interact() {
        return description;
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
