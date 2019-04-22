import java.util.*;

public class Player {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private Level.Room currentRoom;

    public Player(String name, String description, Level.Room currentRoom){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item removeItem(String name){
        for(Item i:items){
            if(i.getName().equals(name)){
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public boolean destroyItem(String name){
        for(Item i:items){
            if(i.getName().equals(name)){
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public void displayInventory(){
        String output = "";
        for(Item i: items){
            output += i.getName() + ", ";
        }
        System.out.println(output);
    }

    public Level.Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom){
        this.currentRoom = newRoom;
    }

    public boolean moveToRoom(String name){
        for(Level.Room n: currentRoom.getNeighbors()){
            if(n.getName().equals(name)){
                currentRoom = n;
            }
        }
        return currentRoom.getName().equals(name);
    }

    public Item removeRandomItem() {
        Item item = items.get((int)(Math.random() * items.size()));
        items.remove(item);
        System.out.println(item + " was removed");
        return item;
    }
}
