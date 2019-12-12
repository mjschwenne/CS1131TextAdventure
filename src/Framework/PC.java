package Framework;
import Framework.Items.Item;

import java.util.ArrayList;

public class PC {
    private Room startRoom = null;
    private Room currentRoom = null;
    ArrayList<Item> inventory = null;
    private boolean gameOver = false;
    private boolean inCombat = false;

    public PC(Room currentRoom) {
        this.inventory = new ArrayList<>();
        this.currentRoom = currentRoom;
        this.startRoom = currentRoom;

    }

    public PC(Room currentRoom, ArrayList<Item> inventory) {
        this.currentRoom = currentRoom;
        this.inventory = inventory;
        this.gameOver = false;
        this.inCombat = false;
    }

    public String getItem(String item){
        if(gameOver || inCombat) {
            return "Command not Available";
        }
        if(currentRoom.getItem(item) == null) {
            return item + " is not a valid item.";
        } else {
            if (currentRoom.getItem(item).getName().equals(item)) {
                inventory.add(currentRoom.getItem(item));
                currentRoom.getItems().remove(currentRoom.getItem(item));
                return "You received the " + item + ".";
            } else {
                return item + " is not available";
            }
        }
    }

    public String dropItem(String item){
        if(gameOver || inCombat) {
            return "Command not Available";
        }
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

    public String look(String[] item) {
        if(gameOver) {
            return "Command not Available";
        }
        if(item.length > 1) {
            ArrayList<Item> items = new ArrayList<>();
            for(Item e : inventory) {
                if(e.getName().equals(item[1])) {
                    items.add(e);
                }
            }
            StringBuilder result = new StringBuilder();
            if (items.size() > 0) {
                for (Item e: items){
                    result.append(e.inspect()).append("\r\n\n");
                }
            }

            return result.toString();
        }
        return currentRoom.getDescription(this) + "\r\nYou see: " + currentRoom.getItemString() + "\r\n" + currentRoom.getExits();
    }

    public String printInventory(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) != null) {
                result.append(inventory.get(i));
                if (i + 1 < inventory.size()) {
                    result.append(", ");
                }
            }
        }

        if(result.length() == 0){
            result.append("NO ITEMS IN YOUR INVENTORY.");
        }
        return result.toString();
    }

    public String fight() {
        inCombat = true;
        ArrayList<String> weapons = new ArrayList<>();
        String weaponsList = "";
        for(Item e : inventory) {
            if(e.getName().equals("TORCH")) {
                weapons.add("TORCH");
            } else if(e.getName().equals("KNIFE")) {
                weapons.add("KNIFE");
            } else if(e.getName().equals("BUBBLES")) {
                weapons.add("BUBBLES");
            }
        }
        for(String i: weapons) {
            weaponsList = weaponsList + "\r\n" + i;
        }
        return "Choose your weapon: \r\n" + weaponsList;
    }

    public String go(String[] direction) {
        if(gameOver || inCombat) {
            return "Command not Available";
        }
        if(direction.length > 2) {
            //Portals
            if(direction[1].equals("PORTAL")) {
                switch(direction[2]) {
                    case "NORTH":
                        if(currentRoom.getPortalNorth() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalNorth();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\r\n " + look(new String[0]);
                        }
                    case "EAST":
                        if(currentRoom.getPortalEast() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalEast();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\r\n " + look(new String[0]);
                        }
                    case "WEST":
                        if(currentRoom.getPortalWest() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalWest();
                            if(currentRoom.getID() == 12) {
                                gameOver = true;
                                return "You moved through the " + direction[2] + " " + direction[1] + ".\r\n" + currentRoom.getDescription(this);
                            }
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\r\n" + look(new String[0]);
                        }
                    case "SOUTH":
                        if(currentRoom.getPortalSouth() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalSouth();
                            return "You moved through the " + direction[2] + " " +  direction[1] + ".\r\n" + look(new String[0]);
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
                        if(currentRoom.getID() == 11) {
                            gameOver = true;
                            return "You moved through the " + direction[1] + " door.\r\n" + currentRoom.getDescription(this);
                        }
                        return "You moved through the " + direction[1] + " door.\r\n" + look(new String[0]);
                    }
                case "EAST":
                    if(currentRoom.getEast() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getEast();
                        return "You moved through the " + direction[1] + " door.\r\n" + look(new String[0]);
                    }
                case "WEST":
                    if(currentRoom.getWest() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getWest();
                        return "You moved through the " + direction[1] + " door.\r\n" + look(new String[0]);
                    }
                case "SOUTH":
                    if(currentRoom.getSouth() == null) {
                        break;
                    } else {
                        currentRoom = currentRoom.getSouth();
                        return "You moved through the " + direction[1] + " door.\r\n" + look(new String[0]);
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
            if (inventory.get(i) != null) {
                result.append(inventory.get(i).getName()).append("_").append(inventory.get(i).inspect());
                if (i + 1 < inventory.size()) {
                    result.append("*");
                }
            }
        }
        result.append("|\n").append(currentRoom.getID()).append("|");
        return result;
    }
}
