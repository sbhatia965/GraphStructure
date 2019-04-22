public class Chicken extends Creature {

    public Chicken(String name, String description, Level level, Level.Room currentRoom){
        super(name, description, level, currentRoom);
    }

    @Override
    protected Level.Room chooseNextRoom() {
        return getCurrentRoom().getRandomNeighbor();
    }
}