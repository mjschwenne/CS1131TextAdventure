package Framework.Items;

public class Knife extends Item {
    private String name = "Knife";
    private String description = "A simple iron gladiatorial knife. It seems to have seen many battles in the arena. A letter 'S' is engraved on the pommel";

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
