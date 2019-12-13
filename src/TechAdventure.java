import Framework.Items.Item;
import Framework.Map;
import Framework.NPC;
import Framework.PC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS 1131 Week 13 Program
 * This is the main class for the program. It handles the sorting of commands into the correct function and responding to the client
 *
 * @author Matt Schwennesen
 * @author Max Jorgensen
 * @author Grayson Wagner
 */
public class TechAdventure implements ConnectionListener {
    //Help string detailing how to use all of the commands
    private String help = "Below is a list of all valid commands:\r\n" +
            "GET [item name] - adds the item to your inventory\r\n" +
            "DROP [item name] - drops the item in the current room\r\n" +
            "GO [direction] or GO [direction portal] - moves through the door or portal\r\n" +
            "LOOK - prints the description of the room\r\n" +
            "LOOK [item name] - prints the description of the item\r\n" +
            "INVENTORY - prints all the items in your inventory\r\n" +
            "FIGHT - Starts a fight\r\n" +
            "\tKNIFE - use the knife in a fight\r\n" +
            "\tTORCH - use the torch in a fight\r\n" +
            "\tBUBBLES - use the bubble in a fight\r\n" +
            "SAVE [save id] - creates a save with the given ID\r\n" +
            "RESTORE - loads the last save with your current ID\r\n" +
            "RESET - starts the game over\r\n" +
            "QUIT - disconnects from the server";

    private AdventureServer adventureServer = null; //holds the adventure server
    private PC player = null; //holds the player
    private Map map = null; //holds the map
    private NPC npc = null; //holds the npc
    private String monsterWeapon = ""; //holds the weapon that the monster selects

    /**
     * Constructor of the Tech Adventure, creates the default objects
     */
    public TechAdventure() {
        map = new Map();
        build("roomMap.txt");
        adventureServer = new AdventureServer();
        player = new PC(map.rooms[0]);
        npc = new NPC();
        adventureServer.setOnTransmission(this);
    }

    /**
     * Starts the adventure server
     *
     * @param args - Uses to accept alternate ports
     */
    public static void main(String[] args) {
        TechAdventure techAdventure = new TechAdventure();
        if (args.length > 0) {
            techAdventure.start(Integer.parseInt(args[0]));
        } else {
            techAdventure.start(2112);
        }

    }

    /**
     * Starts the adventure server
     *
     * @param port - the port to start the server on
     */
    public void start(int port) {
        adventureServer.startServer(port);
    }

    /**
     * Takes a connection event, processes the information and passes it to the correct method to complete the response
     *
     * @param e - the connection event from the adventure server
     */
    @Override
    public void handle(ConnectionEvent e) {
        try {
            switch (e.getCode()) {
                case CONNECTION_ESTABLISHED:
                    // When connection is established, send the start description of the current room
                    adventureServer.sendMessage(e.getConnectionID(), player.look(e.getData().toUpperCase().split(" ")));
                    break;
                case TRANSMISSION_RECEIVED:
                    //Switch statement about the type of command the player issued
                    switch (e.getData().toUpperCase().split(" ")[0]) {
                        //GET, DROP, GO, LOOK, INVENTORY, FIGHT, KNIFE, TORCH, BUBBLES, SAVE, RESTORE, QUIT, RESET, HELP
                        case "GET":
                            adventureServer.sendMessage(e.getConnectionID(), player.getItem(e.getData().toUpperCase().split(" ")));
                            break;
                        case "DROP":
                            adventureServer.sendMessage(e.getConnectionID(), player.dropItem(e.getData().toUpperCase().split(" ")));
                            break;
                        case "GO":
                            adventureServer.sendMessage(e.getConnectionID(), player.go(e.getData().toUpperCase().split(" ")));
                            break;
                        case "LOOK":
                            adventureServer.sendMessage(e.getConnectionID(), player.look(e.getData().toUpperCase().split(" ")));
                            break;
                        case "INVENTORY":
                            adventureServer.sendMessage(e.getConnectionID(), player.printInventory());
                            break;
                        case "FIGHT":
                            monsterWeapon = npc.getRandomWeapon();
                            adventureServer.sendMessage(e.getConnectionID(), npc.monsterWeaponDialog(monsterWeapon));
                            adventureServer.sendMessage(e.getConnectionID(), player.fight());
                            break;
                        case "KNIFE":
                            adventureServer.sendMessage(e.getConnectionID(), npc.combat(player, e.getData().toUpperCase(), monsterWeapon));
                            break;
                        case "TORCH":
                            adventureServer.sendMessage(e.getConnectionID(), npc.combat(player, e.getData().toUpperCase(), monsterWeapon));
                            break;
                        case "BUBBLES":
                            adventureServer.sendMessage(e.getConnectionID(), npc.combat(player, e.getData().toUpperCase(), monsterWeapon));
                            break;
                        case "SAVE":
                            long newID = Long.parseLong(e.getData().split(" ")[1]);
                            adventureServer.changeConnectionId(e.getConnectionID(), newID);
                            adventureServer.sendMessage(newID, save(newID));
                            break;
                        case "RESTORE":
                            adventureServer.sendMessage(e.getConnectionID(), build("save_" + e.getConnectionID() + ".txt"));
                            break;
                        case "QUIT":
                            adventureServer.disconnect(e.getConnectionID());
                            break;
                        case "RESET":
                            adventureServer.sendMessage(e.getConnectionID(), build("roomMap.txt"));
                            break;
                        case "HELP":
                            adventureServer.sendMessage(e.getConnectionID(), help);
                            break;
                        default:
                            adventureServer.sendMessage(e.getConnectionID(), "Invalid Command, try some of these\r\n");
                            adventureServer.sendMessage(e.getConnectionID(), help);
                            break;
                    }
                    break;
                case CONNECTION_TERMINATED:
                    // Cleanup when the connection is terminated.
                    break;
                default:
                    // What is a reasonable default?
            }
        } catch (UnknownConnectionException | IOException unknownConnectionException) {
            unknownConnectionException.printStackTrace();
        }
    }

    /**
     * Builds the map and player from a given text file
     *
     * @param saveFilepath - the filepath to save the file at
     * @return - Conformation that the save was created
     */
    private String build(String saveFilepath) {
        try (Scanner input = new Scanner(new File(saveFilepath))) {
            map.build(input.useDelimiter("PC\\|").next());
            input.useDelimiter("\\|");
            input.next();
            ArrayList<Item> items = new ArrayList<>();
            for (String s : input.next().trim().split(",")) {
                items.add(Item.makeItem(s));
            }
            player = new PC(map.rooms[Integer.parseInt(input.next().trim())], items);
            return "Save loaded.";
        } catch (FileNotFoundException e) {
            return e.toString();
        }
    }

    /**
     * Creates a text file save of the current game state
     *
     * @param connectionID - the connection ID to save the game with
     * @return - conformation that the save was taken
     */
    private String save(long connectionID) {
        try (PrintWriter output = new PrintWriter(new File("save_" + connectionID + ".txt"))) {
            output.print(map.saveMap());
            output.print(player.save());
            output.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Progress saved to save_" + connectionID + ".txt.";
    }

}