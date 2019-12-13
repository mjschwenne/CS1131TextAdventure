package Framework;

import Framework.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {

    public Room[] rooms;

    public Map() {
    }

    public void build(String data) {
        Integer[][] connectionData;
        Room[][] roomConnections;
        Scanner input = new Scanner(data);
        input.useDelimiter("\\|");
        rooms = new Room[input.nextInt()];
        connectionData = new Integer[rooms.length][8];
        roomConnections = new Room[rooms.length][8];
        for (int i = 0; i < rooms.length; i++) {
            int roomID = Integer.parseInt(input.next().trim());
            String roomName = input.next().trim();
            ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(input.next().trim().split("\\*")));
            ArrayList<Item> items = new ArrayList<>();
            for (String s : input.next().trim().split("\\*")) {
                items.add(Item.makeItem(s));
            }

            rooms[roomID] = new Room(roomID, roomName, descriptions, items);
            String[] Links = input.next().trim().split(",");

            for (int j = 0; j < Links.length; j++) {
                connectionData[i][j] = Integer.parseInt(Links[j]);
            }
        }

        for (int i = 0; i < connectionData.length; i++) {
            for (int j = 0; j < connectionData[i].length; j++) {
                if (connectionData[i][j] >= 0) {
                    roomConnections[i][j] = rooms[connectionData[i][j]];
                } else {
                    roomConnections[i][j] = null;
                }
            }
        }

        for (int i = 0; i < rooms.length; i++) {
            rooms[i].connect(roomConnections[i]);
        }

        input.close();
    }

    public StringBuilder saveMap() {
        StringBuilder result = new StringBuilder();
        result.append(rooms.length).append("|\n");
        for (Room r : rooms) {
            result.append(r.getID()).append("|\n");
            result.append(r.getName()).append("|\n");

            ArrayList<String> descriptions = r.getDescriptions();
            for (int i = 0; i < descriptions.size(); i++) {
                result.append(descriptions.get(i));
                if (i + 1 < descriptions.size()) {
                    result.append("*");
                }
            }
            result.append("|\n");

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
