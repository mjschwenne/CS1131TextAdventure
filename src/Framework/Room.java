package Framework;

import Framework.Items.Item;

import java.util.ArrayList;

/**
 * CS 1131 Week 13 program
 * This class represents a room on our map
 *
 * @author Matt Schwennesen
 * @author Max Jorgensen
 * @author Grayson Wagner
 */

public class Room {
    private int ID; //The index of the room in the map array of rooms
    private String name; //The name of the room

    private ArrayList<String> descriptions; //All of the descriptions, including alternate ones for rooms with or without items

    private ArrayList<Item> items; //A list of all items in the room

    private Room[] rooms; //All of the other rooms that this room is connected with

    /**
     * Constructor for a room
     *
     * @param ID           - the ID of the room
     * @param name         - the name of the room
     * @param descriptions - the ArrayList with room descriptions
     * @param items        - the ArrayList with the items in the room
     */
    public Room(int ID, String name, ArrayList<String> descriptions, ArrayList<Item> items) {
        this.ID = ID;
        this.name = name;
        this.descriptions = descriptions;
        this.items = items;
    }

    /**
     * Connects this room with other rooms in the map
     *
     * @param rooms - the other rooms that this room is connected to.
     */
    public void connect(Room[] rooms) {
        this.rooms = rooms;
    }

    /**
     * @return - the ID of the room
     */
    public int getID() {
        return ID;
    }

    /**
     * @return - the rooms that this room is connected to
     */
    public Room[] getRooms() {
        return rooms;
    }

    /**
     * @return - the room connect to the north
     */
    public Room getNorth() {
        return rooms[0];
    }

    /**
     * @return - the room connected to the north via portal
     */
    public Room getPortalNorth() {
        return rooms[1];
    }

    /**
     * @return - the room connected to the east
     */
    public Room getEast() {
        return rooms[2];
    }

    /**
     * @return - the room connected to the east via portal
     */
    public Room getPortalEast() {
        return rooms[3];
    }

    /**
     * @return - the room connected to the south
     */
    public Room getSouth() {
        return rooms[4];
    }

    /**
     * @return - the room connected to the south via portal
     */
    public Room getPortalSouth() {
        return rooms[5];
    }

    /**
     * @return - the room connected to the west
     */
    public Room getWest() {
        return rooms[6];
    }

    /**
     * @return - the room connected to the west via portal
     */
    public Room getPortalWest() {
        return rooms[7];
    }

    /**
     * @return - the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the correct description based on which items the player is carrying
     *
     * @param pc - the player getting the description
     * @return - the description of the room
     */
    public String getDescription(PC pc) {
        //under construction, come back later
        if (descriptions.size() > 1) {
            if (pc.getInventory().isEmpty()) {
                return descriptions.get(0);
            }
            for (Item e : pc.getInventory()) {
                if (e.getName().equals(descriptions.get(1).split("=")[0])) {
                    if (pc.beastSlain && pc.currentRoom.getID() == 2) {
                        return descriptions.get(0);
                    }
                    return descriptions.get(1).split("=")[1];
                }

            }
        }
        return descriptions.get(0);
    }

    /**
     * @return - the descriptions ArrayList
     */
    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    /**
     * @return - the items ArrayList
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param items - the items ArrayList that is the new list of items in room
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * A string representation of the items in the room
     *
     * @return - the string containing the name of the items in this room
     */
    public String getItemString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                result.append(items.get(i));
                if (i + 1 < items.size()) {
                    result.append(", ");
                }
            }
        }

        if (result.length() == 0) {
            result.append("NO ITEMS IN THIS ROOM.");
        }
        return result.toString();
    }

    /**
     * Searches the items in the room for an item with a given name
     *
     * @param name - the name of the item that is being searched for
     * @return - the item, if found, null otherwise
     */
    public Item getItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Adds a single item to the room
     *
     * @param e - the item to be added
     */
    public void addItem(Item e) {
        items.add(e);
    }

    /**
     * Gets a string representation of the exits in this room
     *
     * @return - A string representation of the exits in this room
     */
    public String getExits() {
        StringBuilder result = new StringBuilder();
        result.append("Visible exits include:\r\n");
        if (rooms[0] != null) result.append("NORTH\r\n");
        if (rooms[1] != null) result.append("NORTH PORTAL\r\n");
        if (rooms[2] != null) result.append("EAST\r\n");
        if (rooms[3] != null) result.append("EAST PORTAL\r\n");
        if (rooms[4] != null) result.append("SOUTH\r\n");
        if (rooms[5] != null) result.append("SOUTH PORTAL\r\n");
        if (rooms[6] != null) result.append("WEST\r\n");
        if (rooms[7] != null) result.append("WEST PORTAL\r\n");

        return result.toString();
    }
}
