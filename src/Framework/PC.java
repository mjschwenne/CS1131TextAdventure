package Framework;
import Framework.Items.Item;

import java.util.ArrayList;

/**
 * This class handles all the interactions between the player and the game evnironment.
 *
 * @author Max Jorgensen
 * @author Grayson Wagner
 * @author Matt Schwennesen
 *
 * Date Last Modified: 12/12/2019
 */

public class PC {
    private Room startRoom = null; //The room that the player starts in.
    public Room currentRoom = null; //The room that the player is currently in.
    ArrayList<Item> inventory = new ArrayList<>(); //The players inventory.
    public boolean gameOver = false; //Checks if the game has ended.
    public boolean inCombat = false; //Checks if the player is in combat.
    public boolean beastSlain = false; //Checks if the monster has been defeated.

    /**
     * This constructor initalizes the player in the given room.
     * @param currentRoom
     */
    public PC(Room currentRoom) {
        this.inventory = new ArrayList<>();
        this.currentRoom = currentRoom;
        this.startRoom = currentRoom;

    }

    /**
     * This constructor intializes the player with the potential for keeping inventory items.
     * @param currentRoom
     * @param inventory
     */
    public PC(Room currentRoom, ArrayList<Item> inventory) {
        this.currentRoom = currentRoom;
        this.inventory = inventory;
        this.gameOver = false;
        this.inCombat = false;
    }

    /**
     * Allows the player to pick up items from the environment
     * @param item
     * @return String of success or not.
     */
    public String getItem(String[] item){
        //Checks if the command can be used in the situation.
        if(gameOver || inCombat) {
            return "Command not Available";
        }
        //Checks if the command was entered  properly.
        if(item.length < 2) {
            return "Invalid Command";
        }
        //Checks if the item given is in the room.
        if(currentRoom.getItem(item[1]) == null) {
            return item[1] + " is not a valid item.";
        } else {
            //Checks if the item given can be added to the player's inventory.
            if (currentRoom.getItem(item[1]).getName().equals(item[1])) {
                inventory.add(currentRoom.getItem(item[1]));
                currentRoom.getItems().remove(currentRoom.getItem(item[1]));
                return "You received the " + item[1] + ".";
            } else {
                return item[1] + " is not available";
            }
        }
    }

    /**
     * This method allows the player to remove an item from their inventory.
     * @param item
     * @return String for success or not.
     */
    public String dropItem(String[] item){
        //Checks if the command can be used.
        if(gameOver || inCombat) {
            return "Command not Available";
        }
        //Checks if the command was entered properly.
        if(item.length < 2) {
            return "Invalid Command";
        }
        //Checks if the item is in the player's inventory and drops it.
        Item temp = null;
        for(Item e : inventory) {
            if(e.getName().equals(item[1])) {
                temp = e;
            }
        }
        if(temp == null) {
            return "You do not have the " + item[1] + ".";
        } else {
            inventory.remove(temp);
            return "You dropped the " + item[1] + ".";
        }

    }

    /**
     * Returns the arraylist of items in the player's inventory.
     * @return inventory
     */
    public ArrayList<Item> getInventory(){
        ArrayList<Item> cleanInventory = new ArrayList<>();
        for (Item e: inventory){
            if (e != null) cleanInventory.add(e);
        }
        inventory = cleanInventory;
        return inventory;
    }

    /**
     * Helper method that checks if the given item is in the player's inventory.
     * @param item
     * @return item or null.
     */
    public Item getItem(String item){
        for(Item e : getInventory()){
            if (e.getName().equals(item)){
                return e;
            }
        }
        return null;
    }

    /**
     * Shows the description of either the item specified or the current room.
     * @param item
     * @return Room description or specified item description.
     */
    public String look(String[] item) {
        //Checks the requirements for initializing combat.
        for ( Item e : getInventory()) {
            if(e.getName().equals("ORB") && currentRoom.getID() == 2 && !beastSlain) {
                inCombat = true;
            }

        }
        //Checks if the command can be  used.
        if(gameOver) {
            return "Command not Available";
        }
        //Checks if the given item is in your inventory.
        if(item.length > 1) {
            ArrayList<Item> items = new ArrayList<>();
            for(Item e : inventory) {
                if(e.getName().equals(item[1])) {
                    items.add(e);
                }
            }
            //Creates a string builder of the item description.
            StringBuilder result = new StringBuilder();
            if (items.size() > 0) {
                for (Item e: items){
                    result.append(e.inspect()).append("\r\n\n");
                }
            }

            return result.toString();
        }
        //Checks if the player is in combat.
        if(inCombat) {
            return currentRoom.getDescription(this) + " You are in Combat. Use FIGHT to select a weapon.";
        } else {
            return currentRoom.getDescription(this) + "\r\nYou see: " + currentRoom.getItemString() + "\r\n" + currentRoom.getExits();
        }

    }

    /**
     * Prints the player's inventory in a clean looking fashion.
     * @return String of inventory
     */
    public String printInventory(){
        //Creates a new string builder and adds each element in the array.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) != null) {
                result.append(inventory.get(i));
                if (i + 1 < inventory.size()) {
                    result.append(", ");
                }
            }
        }

        //Return a given statement if the inventory is empty.
        if(result.length() == 0){
            result.append("NO ITEMS IN YOUR INVENTORY.");
        }
        return result.toString();
    }

    /**
     * This method handles initalizing combat for the player.
     * @return String of weapon options.
     */
    public String fight() {
        //Checks if a fight has started and what weapons are in your inventory.
        if(inCombat) {
            ArrayList<String> weapons = new ArrayList<>();
            String weaponsList = "";
            for(Item e : inventory) {
                switch (e.getName()) {
                    case "TORCH":
                        weapons.add("TORCH");
                        break;
                    case "KNIFE":
                        weapons.add("KNIFE");
                        break;
                    case "BUBBLES":
                        weapons.add("BUBBLES");
                        break;
                }
            }
            //Puts the weapons on a String to print.
            for(String i: weapons) {
                weaponsList = weaponsList + "\r\n" + i;
            }
            //Kills you if you have no weapons.
            if(weapons.isEmpty()) {
                gameOver = true;
                return "You had no weapons. You were brutally murdered. Use RESET to restart or QUIT to disconnect.";
            }
            return "Choose your weapon: \r\n" + weaponsList;
        }
        return "Command not available";

    }


    /**
     * This method handles all of the movement that the player can make.
     * @param direction
     * @return String of success or not.
     */
    public String go(String[] direction) {
        //Checks if the command can be used.
        if(gameOver || inCombat) {
            return "Command not available";
        }
        //Checks if the command was input properly.
        if (direction.length <= 1) {
            return "Invalid command";
        }

        //Checks if the command is the correct length for a portal or a door.
        if(direction.length > 2) {
            //Portals
            if(direction[2].equals("PORTAL")) {
                switch(direction[1]) {
                    case "NORTH":
                        if(currentRoom.getPortalNorth() == null) {
                            break;
                        } else {
                            currentRoom = currentRoom.getPortalNorth();
                            if (currentRoom.getID() == 12) {
                                gameOver = true;
                                return "You moved through the " + direction[2] + " " + direction[1] + ".\r\n" + currentRoom.getDescription(this);
                            }
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
                            if (currentRoom.getID() == 0 && getInventory().contains(getItem("ORB"))) {
                                currentRoom = currentRoom.getPortalWest();
                                //Special case, exit room.
                                if (currentRoom.getID() == 11) {
                                    gameOver = true;
                                    return "You moved through the " + direction[2] + " " + direction[1] + ".\r\n" + currentRoom.getDescription(this);
                                }
                                return "You moved through the " + direction[2] + " " + direction[1] + ".\r\n" + look(new String[0]);
                            } else if (currentRoom.getID() != 0){
                                currentRoom = currentRoom.getPortalWest();
                                return "You moved through the " + direction[2] + " " + direction[1] + ".\r\n" + look(new String[0]);
                            } else {
                                return "You attempt to move through the portal but cannot... Its almost like you need something else to be able to do that... or perhaps something is pulling you back.";
                            }
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
                        //Special case, exit room.
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

    /**
     * Handles the players state for saving the game.
     * @return The Stringbuilder of the player's state.
     */
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
