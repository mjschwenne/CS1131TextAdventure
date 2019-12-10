package Framework;
import Framework.Items.Item;

import java.util.ArrayList;

public class PC {
    private Room currentRoom = null;
    private ArrayList<Item> inventory = null;

    public PC(Room currentRoom) {
        this.inventory = new ArrayList<>();
        this.currentRoom = currentRoom;
    }

    public String getItem(String item){
        inventory.add(new Item(item));
        return "You received the " + item;
    }

    public String dropItem(String item){
        //TODO Fix this thing.
        inventory.remove(new Item(item));
        return "You dropped the " + item;
    }

    public String look() {
        //TODO this might change
        return currentRoom.getDescription(this);
    }

    public String printInventory(){
        return inventory.toString();
    }

    public String go(String[] direction) {
        if(direction.length > 2) {
            //Portals
            switch(direction[2]) {
                case "NORTH":
                    currentRoom = currentRoom.getPortalNorth();
                    break;
                case "EAST":
                    currentRoom = currentRoom.getPortalEast();
                    break;
                case "WEST":
                    currentRoom = currentRoom.getPortalWest();
                    break;
                case "SOUTH":
                    currentRoom = currentRoom.getPortalSouth();
                    break;
            }
            return "You moved through the " + direction[2] + " " +  direction[1] + ".";
        } else {
            //Doors
            switch(direction[1]) {
                case"NORTH":
                    currentRoom = currentRoom.getNorth();
                    break;
                case "EAST":
                    currentRoom = currentRoom.getEast();
                    break;
                case "WEST":
                    currentRoom = currentRoom.getWest();
                    break;
                case "SOUTH":
                    currentRoom = currentRoom.getSouth();
                    break;
            }
            return "You moved through the " + direction[1] + " door.";
        }
    }

}
