package Framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {

    private Room[] rooms;

    public Map(String filePath) {
        rooms = build(new File(filePath));
    }

    public static void main(String[] args) {
        Map map = new Map("testOut.txt");
        map.saveMap(new File("testOut.txt"));
    }

    private Room[] build(File file) {
        Room[] rooms = null;
        Integer[][] connectionData;
        Room[][] roomConnections;
        try {
            Scanner input = new Scanner(file);
            input.useDelimiter("\\|");
            rooms = new Room[input.nextInt()];
            connectionData = new Integer[rooms.length][8];
            roomConnections = new Room[rooms.length][8];
            for (int i = 0; i < rooms.length; i++) {
                int roomID = Integer.parseInt(input.next().trim());
                String roomName = input.next().trim();
                ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(input.next().trim().split(",")));
                ArrayList<Item> items = new ArrayList<>();
                for (String s : input.next().trim().split(",")) {
                    items.add(new Item(s));
                }

                rooms[roomID] = new Room(roomID, roomName, descriptions, items);
                String[] Links = input.next().trim().split(",");
                System.out.println(Arrays.toString(Links));

                for (int j = 0; j < Links.length; j++) {
                    connectionData[i][j] = Integer.parseInt(Links[j]);
                }

                System.out.println(roomID + " " + roomName + " " + descriptions.toString() + " " + items.toString());
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void saveMap(File file) {
        try (PrintWriter output = new PrintWriter(file)) {
            output.println(rooms.length + "|");
            for (Room r : rooms) {
                output.println(r.getID() + "|");
                output.println(r.getName() + "|");

                ArrayList<String> descriptions = r.getDescriptions();
                for (int i = 0; i < descriptions.size(); i++) {
                    output.print(descriptions.get(i));
                    if (i + 1 < descriptions.size()) {
                        output.print(",");
                    }
                }
                output.println("|");

                ArrayList<Item> rItems = r.getItems();
                for (int i = 0; i < rItems.size(); i++) {
                    output.print(rItems.get(i));
                    if (i + 1 < rItems.size()) {
                        output.print(",");
                    }
                }
                output.println("|");

                Room[] rRooms = r.getRooms();
                for (int i = 0; i < rRooms.length; i++) {
                    if (rRooms[i] == null) {
                        output.print("-1");
                    } else {
                        output.print(rRooms[i].getID());
                    }
                    if (i + 1 < rRooms.length) {
                        output.print(",");
                    }
                }
                output.println("|");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
