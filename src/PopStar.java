import java.util.ArrayList;

public class PopStar extends Creature {
    ArrayList<Item> items;

    public PopStar(String name, String description, Level level, Level.Room currentRoom){
        super(name, description, level, currentRoom);
        items = new ArrayList<>();
    }

    public void act(){
        if (this.currentRoom == player.getCurrentRoom()) {
            Item item = player.removeRandomItem();
            if (item != null) items.add(item);
        }
        super.act();
    }

    @Override
    protected Level.Room chooseNextRoom() {
        return lookForPlayer();
    }


}

