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

    public String getDescription( PC pc ) {
        //under construction, come back later
        if(descriptions.size() > 1) {
            if(pc.getInventory().isEmpty()) {
                return descriptions.get(0);
            }
            for ( Item e : pc.getInventory()) {
                if(e.getName().equals(descriptions.get(1).split("=")[0])) {
                    if(pc.beastSlain && pc.currentRoom.getID() == 2) {
                        return descriptions.get(0);
                    }
                    return descriptions.get(1).split("=")[1];
                }

            }
        }
        return descriptions.get(0);
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) != null) {
                result.append(items.get(i));
                if (i + 1 < items.size()) {
                    result.append(", ");
                }
            }
        }

        if(result.length() == 0){
            result.append("NO ITEMS IN THIS ROOM.");
        }
        return result.toString();
    }

    public Item getItem(String name) {
        for(Item i : items){
            if (i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getExits(){
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
