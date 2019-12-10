package Framework;

import Framework.Items.Item;

import java.util.ArrayList;

public class Room {
    private int ID;
    private String name;

    private ArrayList<String> descriptions;

    private ArrayList<Item> items;

    private Room[] rooms;

    public Room(){}

    public Room(int ID, String name, ArrayList<String> descriptions, ArrayList<Item> items) {
        this.ID = ID;
        this.name = name;
        this.descriptions = descriptions;
        this.items = items;
    }

    public void connect(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getID(){
        return ID;
    }

    public Room[] getRooms(){
        return rooms;
    }

    public Room getNorth() {
        return rooms[0];
    }

    public Room getPortalNorth() {
        return rooms[1];
    }

    public Room getEast() {
        return rooms[2];
    }

    public Room getPortalEast() {
        return rooms[3];
    }

    public Room getSouth() {
        return rooms[4];
    }

    public Room getPortalSouth() {
        return rooms[5];
    }

    public Room getWest() {
        return rooms[6];
    }

    public Room getPortalWest() {
        return rooms[7];
    }

    public String getName() {
        return name;
    }

    public String getDescription(PC pc){
        return descriptions.get(0);
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        for(Item i : items){
            if (i.name.equals(name)){
                return i;
            }
        }
        return null;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
