package Framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {

    private Room start;

    public Map(String filePath) {
        build(new File(filePath));
    }

    private void build(File file) {
        Room[] rooms;
        Integer[][] connectionData;
        try {
            Scanner input = new Scanner(file);
            input = input.useDelimiter("\\|");
            rooms = new Room[input.nextInt()];
            connectionData = new Integer[rooms.length][];
            for (int i = 0; i < rooms.length; i++) {
                int roomID = Integer.parseInt(input.next().trim());
                String roomName = input.next().trim();
                ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(input.next().trim().split(",")));
                ArrayList<Item> items = new ArrayList<>();
                for (String s : input.next().trim().split(",")) {
                    items.add(new Item(s));
                }

                rooms[roomID] = new Room(roomName, descriptions, items);
                String[] Links = input.next().trim().split(",");
                System.out.println(Arrays.toString(Links));

                for (int j = 0; j < Links.length; j++){
                    Integer value = Integer.parseInt(Links[j]);
                    System.out.println(value);
                    connectionData[i][j] = value > 0 ? value : null;
                }

                System.out.println(roomID + " " + roomName + " " + descriptions.toString() + " " + items.toString());
            }

            for (int i = 0; i < rooms.length; i++) {
                System.out.println(Arrays.toString(connectionData[i]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map map = new Map("roomTest.txt");
    }
}
