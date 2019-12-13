package Framework;

import Framework.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CS 1131 Week 13 Program
 * This class creates the map and holds the rooms
 *
 * @author Matt Schwennesen
 * @author Max Jorgensen
 * @author Greyson Wanger
 */

public class Map {

    public Room[] rooms; //An array of all the rooms in the map

    /**
     * Builds all the rooms in the map based off of data from a text file
     *
     * @param data - map data from a text file
     */
    public void build(String data) {
        Integer[][] connectionData; //Needed to store data about room connection data in the form of array indices
        Room[][] roomConnections; //Needed to store data about room connection data in the form of room object to create a proper linked structure
        Scanner input = new Scanner(data);
        input.useDelimiter("\\|");
        rooms = new Room[input.nextInt()];
        connectionData = new Integer[rooms.length][8];
        roomConnections = new Room[rooms.length][8];
        //Iterate through the number of rooms in the map, based off of the first integer in the string
        for (int i = 0; i < rooms.length; i++) {
            int roomID = Integer.parseInt(input.next().trim());
            String roomName = input.next().trim();

            //Create descriptions in the room
            //Descriptions are delimited by '*'
            ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(input.next().trim().split("\\*")));

            //Create items in the room
            ArrayList<Item> items = new ArrayList<>();
            for (String s : input.next().trim().split("\\*")) {
                //Items are delimited by '*'
                items.add(Item.makeItem(s));
            }

            //Create rooms
            rooms[roomID] = new Room(roomID, roomName, descriptions, items);

            //Create link data based off of integers in the text file
            String[] Links = input.next().trim().split(",");

            for (int j = 0; j < Links.length; j++) {
                connectionData[i][j] = Integer.parseInt(Links[j]);
            }
        }

        //Convert ints to rooms
        for (int i = 0; i < connectionData.length; i++) {
            for (int j = 0; j < connectionData[i].length; j++) {
                if (connectionData[i][j] >= 0) {
                    roomConnections[i][j] = rooms[connectionData[i][j]];
                } else {
                    roomConnections[i][j] = null;
                }
            }
        }

        //Submit connection data to rooms
        for (int i = 0; i < rooms.length; i++) {
            rooms[i].connect(roomConnections[i]);
        }

        //close the input scanner
        input.close();
    }

    /**
     * Create a string reparation of the map that can be saved in a save file
     *
     * @return - String with all map data in it
     */
    public StringBuilder saveMap() {
        StringBuilder result = new StringBuilder(); //result
        result.append(rooms.length).append("|\n");
        //For each room...
        for (Room r : rooms) {
            //Add the room ID and name
            result.append(r.getID()).append("|\n");
            result.append(r.getName()).append("|\n");

            //Add the room descriptions to the result
            ArrayList<String> descriptions = r.getDescriptions();
            for (int i = 0; i < descriptions.size(); i++) {
                result.append(descriptions.get(i));
                if (i + 1 < descriptions.size()) {
                    result.append("*");
                }
            }
            result.append("|\n");

            //Add the room items to the result
            ArrayList<Item> rItems = r.getItems();
            for (int i = 0; i < rItems.size(); i++) {
                if (rItems.get(i) != null) {
                    result.append(rItems.get(i).getName()).append("_").append(rItems.get(i).inspect());
                    if (i + 1 < rItems.size()) {
                        result.append("*");
                    }
                }
            }
            result.append("|\n");

            //Add connection data to the result
            Room[] rRooms = r.getRooms();
            for (int i = 0; i < rRooms.length; i++) {
                if (rRooms[i] == null) {
                    result.append("-1");
                } else {
                    result.append(rRooms[i].getID());
                }
                if (i + 1 < rRooms.length) {
                    result.append(",");
                }
            }
            result.append("|\n");
        }
        return result;
    }
}
