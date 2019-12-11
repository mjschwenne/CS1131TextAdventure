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
        System.out.println(currentRoom.getItems());
        if(currentRoom.getItem(item).getName().equals(item)) {
            inventory.add(Item.makeItem(item));
            return "You received the " + item;
        }
        return item + " is not available.";
    }

    public String dropItem(String item){
        Item temp = null;
        for(Item e : inventory) {
            if(e.getName().equals(item)) {
                temp = e;
            }
        }
        inventory.remove(temp);
        return "You dropped the " + item;
    }

    public String inspectItem(String item) {
        Item temp = null;
        for(Item e : inventory) {
            if(e.getName().equals(item)) {
                return e.inspect();
            }
        }
        return item + " is not in your inventory";
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
