import Framework.Map;
import Framework.PC;

import java.io.IOException;

public class TechAdventure implements ConnectionListener{
    AdventureServer adventureServer = null;
    PC player = null;
    Map map = null;

    public TechAdventure() {
        map = new Map("roomTest.txt");
        adventureServer = new AdventureServer();
        player = new PC(map.rooms[0]);
        adventureServer.setOnTransmission ( this );
    }

    public static void main(String[] args) {
        TechAdventure techAdventure = new TechAdventure ();
        if(args.length > 0) {
            techAdventure.start(Integer.parseInt(args[0]));
        } else {
            techAdventure.start ( 2112 );
        }

    }

    public void start( int port ) {
        adventureServer.startServer ( port );
    }

    @Override
    public void handle(ConnectionEvent e) {
        //System.out.println( "EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
        //System.out.println( String.format ( "connectionId=%d, data=%s", e.getConnectionID (), e.getData() ));
        try {
            switch ( e.getCode ( ) ) {
                case CONNECTION_ESTABLISHED:
                    // What do you do when the connection is established?
                    break;
                case TRANSMISSION_RECEIVED:
                    //adventureServer.sendMessage ( e.getConnectionID ( ), String.format ("MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID ( ), e.getData ( ) ) );
                    switch(e.getData().toUpperCase().split(" ")[0]) {
                        //GET, DROP, GO, LOOK, INVENTORY, SAVE, RESTORE, QUIT
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
                            adventureServer.sendMessage(e.getConnectionID(), player.look());
                            break;
                        case "INVENTORY":
                            adventureServer.sendMessage(e.getConnectionID(), player.printInventory());
                            break;
                        case "INSPECT":
                            adventureServer.sendMessage(e.getConnectionID(), player.inspectItem(e.getData().toUpperCase().split(" ")[1]));
                        case "SAVE":
                            break;
                        case "RESTORE":
                            //Load save here
                            break;
                        case "QUIT":
                            adventureServer.disconnect(e.getConnectionID());
                            break;
                        default:
                            adventureServer.sendMessage(e.getConnectionID(), player.resetPlayer());
                            break;
                    }
                    break;
                case CONNECTION_TERMINATED:
                    // Cleanup when the connection is terminated.
                    break;
                default:
                    // What is a reasonable default?
            }
        } catch (UnknownConnectionException | IOException unknownConnectionException ) {
            unknownConnectionException.printStackTrace ( );
        }
    }
}