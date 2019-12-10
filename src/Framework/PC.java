package Framework;
import java.util.ArrayList;

public class PC {
    private Room currentRoom;
    private ArrayList<Item> inventory = null;

    public PC() {
        this.inventory = new ArrayList<>();
    }

    public void getItem(String item){
        inventory.add(new Item(item));
    }

    public void dropItem(String item){
        //TODO Fix this thing.
        inventory.remove(new Item(item));
    }

    public void look() {
        //TODO this might change
        System.out.println(currentRoom.getDescription(this));
    }

    public void printInventory(){
        System.out.println(inventory);
    }

    public void go(String[] direction) {
        if(direction.length > 2) {
            //Portals
            switch(direction[2]) {
                case "NORTH":
                    currentRoom = currentRoom.getPortalNorth();
                    break;
                case "EAST":
                    currentRoom = currentRoom.getPortalEast();
                    break;
                case "WEST":
                    currentRoom = currentRoom.getPortalWest();
                    break;
                case "SOUTH":
                    currentRoom = currentRoom.getPortalSouth();
                    break;
            }
        } else {
            //Doors
            switch(direction[1]) {
                case"NORTH":
                    currentRoom = currentRoom.getNorth();
                    break;
                case "EAST":
                    currentRoom = currentRoom.getEast();
                    break;
                case "WEST":
                    currentRoom = currentRoom.getWest();
                    break;
                case "SOUTH":
                    currentRoom = currentRoom.getSouth();
                    break;
            }
        }
    }

}
