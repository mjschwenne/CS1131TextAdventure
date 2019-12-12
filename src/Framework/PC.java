package Framework;
import Framework.Items.Item;

import java.util.ArrayList;

public class PC {
    private Room startRoom = null;
    private Room currentRoom = null;
    private ArrayList<Item> inventory = null;

    public PC(Room currentRoom) {
        this.inventory = new ArrayList<>();
        this.currentRoom = currentRoom;
        this.startRoom = currentRoom;

    }

    public String resetPlayer() {
        currentRoom = startRoom;
        inventory.clear();
        return look(new String[0]);
    }

    public PC(Room currentRoom, ArrayList<Item> inventory) {
        this.currentRoom = currentRoom;
        this.inventory = inventory;
    }

    public String getItem(String item){
        if(currentRoom.getItem(item) == null) {
        } else {
            if(currentRoom.getItem(item).getName().equals(item)) {
                currentRoom.getItems().remove(currentRoom.getItem(item));
                inventory.add(Item.makeItem(item));
                return "You received the " + item + ".";
            } else {
                return item + " is not available";
            }
        }
        return item + " is not valid.";
    }

    public String dropItem(String item){
        Item temp = null;
        for(Item e : inventory) {
            if(e.getName().equals(item)) {
                temp = e;
            }
        }
        if(temp == null) {
            return "You do not have the " + item + ".";
        } else {
            inventory.remove(temp);
            return "You dropped the " + item + ".";
        }

    }

    public String inspectItem(String item) {
        for(Item e : inventory) {
            if(e.getName().equals(item)) {
                return e.inspect();
            }
        }
        return item + " is not in your inventory.";
    }

    public String look(String[] item) {
        if(item.length > 1) {
            return inspectItem(item[1]);
        }
        return currentRoom.getDescription(this) + "\nYou see: " + currentRoom.getItems();
    }

    public String printInventory(){
        return inventory.toString();
    }

    public String go(String[] direction) {
        if(direction.length > 2) {
            //Portals
            if(direction[1].equals("PORTAL")) {
                switch(direction[2]) {
                    case "NORTH":
                        if(currentRoom.getPortalNorth() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalNorth();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\n " + look(new String[0]);
                        }
                    case "EAST":
                        if(currentRoom.getPortalEast() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalEast();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\n " + look(new String[0]);
                        }
                    case "WEST":
                        if(currentRoom.getPortalWest() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalWest();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\n" + look(new String[0]);
                        }
                    case "SOUTH":
                        if(currentRoom.getPortalSouth() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalSouth();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\n" + look(new String[0]);
                        }
                    default:
                        return "Invalid Direction Command";
                }
            } else {
                return "Invalid Direction Command";
            }
            return "You cannot go in that direction.";
        } else {
            //Doors
            switch(direction[1]) {
                case"NORTH":
                    if(currentRoom.getNorth() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getNorth();
                        return "You moved through the " + direction[1] + " door.\n" + look(new String[0]);
                    }
                case "EAST":
                    if(currentRoom.getEast() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getEast();
                        return "You moved through the " + direction[1] + " door.\n" + look(new String[0]);
                    }
                case "WEST":
                    if(currentRoom.getWest() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getWest();
                        return "You moved through the " + direction[1] + " door.\n" + look(new String[0]);
                    }
                case "SOUTH":
                    if(currentRoom.getSouth() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getSouth();
                        return "You moved through the " + direction[1] + " door.\n" + look(new String[0]);
                    }
                default:
                    return "Invalid Direction Command";
            }
            return "You cannot go in that direction.";
        }
    }

    public StringBuilder save(){
        StringBuilder result = new StringBuilder();
        result.append("PC|\n");
        for (int i = 0; i < inventory.size(); i++) {
            result.append(inventory.get(i));
            if (i + 1 < inventory.size()) {
                result.append(",");
            }
        }
        result.append("|\n").append(currentRoom.getID()).append("|");
        return result;
    }
}
