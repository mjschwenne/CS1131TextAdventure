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

public class TechAdventure implements ConnectionListener {
    AdventureServer adventureServer = null;
    PC player = null;
    Map map = null;

    public TechAdventure() {
        map = new Map("roomMap.txt");
        adventureServer = new AdventureServer();
        player = new PC(map.rooms[0]);
        adventureServer.setOnTransmission(this);
    }

    public static void main(String[] args) {
        TechAdventure techAdventure = new TechAdventure();
        if (args.length > 0) {
            techAdventure.start(Integer.parseInt(args[0]));
        } else {
            techAdventure.start(2112);
        }

    }

    public void start(int port) {
        adventureServer.startServer(port);
    }

    @Override
    public void handle(ConnectionEvent e) {
        //System.out.println( "EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
        //System.out.println( String.format ( "connectionId=%d, data=%s", e.getConnectionID (), e.getData() ));
        try {
            switch (e.getCode()) {
                case CONNECTION_ESTABLISHED:
                    // What do you do when the connection is established?
                    adventureServer.sendMessage(e.getConnectionID(), player.look(e.getData().toUpperCase().split(" ")));
                    break;
                case TRANSMISSION_RECEIVED:
                    //adventureServer.sendMessage ( e.getConnectionID ( ), String.format ("MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID ( ), e.getData ( ) ) );
                    switch (e.getData().toUpperCase().split(" ")[0]) {
                        //GET, DROP, GO, LOOK, INVENTORY, FIGHT, SAVE, RESTORE, QUIT
                        case "GET":
                            adventureServer.sendMessage(e.getConnectionID(), player.getItem(e.getData().toUpperCase().split(" ")[1]));
                            break;
                        case "DROP":
                            adventureServer.sendMessage(e.getConnectionID(), player.dropItem(e.getData().toUpperCase().split(" ")[1]));
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
                            adventureServer.sendMessage(e.getConnectionID(), player.fight());
                            break;
                        case "KNIFE":
                            break;
                        case "TORCH":
                            break;
                        case "BUBBLES:":
                            break;
                        case "SAVE":
                            long newID = Long.parseLong(e.getData().split(" ")[1]);
                            adventureServer.changeConnectionId(e.getConnectionID(), newID);
                            adventureServer.sendMessage(newID, save(newID));
                            break;
                        case "RESTORE":
                            adventureServer.sendMessage(e.getConnectionID(), restore("save_" + e.getConnectionID() + ".txt"));
                            break;
                        case "QUIT":
                            adventureServer.disconnect(e.getConnectionID());
                            break;
                        case "RESET":
                            adventureServer.sendMessage(e.getConnectionID(), restore("roomMap.txt"));
                            break;
                        default:
                            adventureServer.sendMessage(e.getConnectionID(), "Invalid Command");
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

    private String restore(String saveFilepath) {
        try (Scanner input = new Scanner(new File(saveFilepath))) {
            map.rebuild(input.useDelimiter("PC\\|").next());
            input.useDelimiter("\\|");
            input.next();
            ArrayList<Item> items = new ArrayList<>();
            for (String s : input.next().trim().split(",")) {
                items.add(Item.makeItem(s));
            }
            player = new PC(map.rooms[Integer.parseInt(input.next().trim())], items);
            return "Game restored to this connection IDs last save.";
        } catch (FileNotFoundException e) {
            return e.toString();
        }
    }

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