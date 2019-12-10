import java.util.ArrayList;

public class TechAdventure {
    ArrayList<String> invArrayList = new ArrayList<>(); // temporary array list that represents your inventory
    AdventureServer as = new AdventureServer;
    public static void main(String[] args) {
        AdventureServer server = new AdventureServer();
        server.setOnTransmission(e -> System.out.println(e.getData()));
        if(args.length >= 1) {
            server.startServer(Integer.parseInt(args[0]));
        } else {
            server.startServer(2112);
        }

    }

    public void getItem(String item) { // picks up items by checking to see if you have the item, checking if the item exists
        boolean itemInInventory = false;
        for ( int i = 0; i < invArrayList.size(); i++ ) { // checks to make sure that the item isn't already in your inventory
            if ( item == invArrayList.get(i)) {
                itemInInventory = true;
            }
        }

        boolean itemExists = false;
        for ( int i = 0; i < itemArrayList.size(); i++ ) { // checks to make sure that the item is actually an item in the game
            if ( item == itemArrayList.get(i)) { // itemArrayList represents the list of real items in the game
                itemExists = true;
            }
        }

        if (itemInInventory == false && itemExists == true) { // if the item isn't in your inventory and the item exists, add it to your inventory
            invArrayList.add(item);
        } else if (itemExists == false) {
            as.sendMessage(connectionId, "The item you entered doesn't exist (You entered " + item + ")");
        } else if (itemInInventory == true) {
            as.sendMessage(connectionId, "The item you entered is already in your inventory, so you can't pick it up");
        }
    }

    public void dropItem(String item) { // picks up items by checking to see if you have the item, checking if the item exists
        boolean itemInInventory = false;
        for ( int i = 0; i < invArrayList.size(); i++ ) { // checks to make sure that the item is in your inventory
            if ( item == invArrayList.get(i)) {
                itemInInventory = true;
            }
        }

        boolean itemExists = false;
        for ( int i = 0; i < itemArrayList.size(); i++ ) { // checks to make sure that the item is actually an item in the game
            if ( item == itemArrayList.get(i)) { // itemArrayList represents the list of real items in the game
                itemExists = true;
            }
        }

        if (itemInInventory == true && itemExists == true) { // if the item is in your inventory and the item exists, remove it to your inventory
            invArrayList.remove(item);
        } else if (itemExists == false) {
            as.sendMessage(connectionId, "The item you entered doesn't exist (You entered " + item + ")");
        } else if (itemInInventory == false) {
            as.sendMessage(connectionId, "The item you entered isn't in your inventory, so you can't drop it");
        }
    }

    public void goDirection(String direction) {
        // if you can go in direction, go in direction
    }

    public String look() { // returns the current room description
        return currentRoomDescription; // replace currentRoomDescription with the actual room description
    }

    public String look(String item) {
        String lookString = "The specified item is not in your inventory"; // I set this up so if the item isnt in your inventory, this string is returned
        for ( int i = 0; i < invArrayList.size(); i++ ) { // invArrayList is a temp name for the inventory array list
            if ( item == invArrayList.get(i)) {
                lookString = itemDescription; // replace itemDescription with the actual item description
            }
        }
        return lookString;
    }

    public void inventory() {
        as.sendMessage(connectionId, "The following items are in your inventory:");
        for ( int i = 0; i < invArrayList.size(); i++ ) { // checks to make sure that the item is in your inventory
            as.sendMessage(connectionId, invArrayList.get(i));
        }
    }

    public void save() {

    }

    public void restore() {

    }

    public void quit() {
        as.disconnect(connectionId);
    }
}