package Framework;

public class Item {
    String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
