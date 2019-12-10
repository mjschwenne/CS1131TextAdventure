package Framework;

import java.util.ArrayList;

public class Room {
    private String name;

    private ArrayList<String> descriptions;

    private ArrayList<Item> items;

    private Room north;
    private Room portalNorth;

    private Room east;
    private Room portalEast;

    private Room south;
    private Room portalSouth;

    private Room west;
    private Room portalWest;

    public Room(){}

    public Room(String name, ArrayList<String> descriptions, ArrayList<Item> items) {
        this.name = name;
        this.descriptions = descriptions;
        this.items = items;
    }

    public void connect(Room north, Room portalNorth, Room east, Room portalEast, Room south, Room portalSouth, Room west, Room portalWest) {
        this.north = north;
        this.portalNorth = portalNorth;
        this.east = east;
        this.portalEast = portalEast;
        this.south = south;
        this.portalSouth = portalSouth;
        this.west = west;
        this.portalWest = portalWest;
    }

    public Room getNorth() {
        return north;
    }

    public Room getPortalNorth() {
        return portalNorth;
    }

    public Room getEast() {
        return east;
    }

    public Room getPortalEast() {
        return portalEast;
    }

    public Room getSouth() {
        return south;
    }

    public Room getPortalSouth() {
        return portalSouth;
    }

    public Room getWest() {
        return west;
    }

    public Room getPortalWest() {
        return portalWest;
    }

    public String getDescription(PC pc){
        return descriptions.get(0);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setPortalNorth(Room portalNorth) {
        this.portalNorth = portalNorth;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setPortalEast(Room portalEast) {
        this.portalEast = portalEast;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setPortalSouth(Room portalSouth) {
        this.portalSouth = portalSouth;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public void setPortalWest(Room portalWest) {
        this.portalWest = portalWest;
    }
}
