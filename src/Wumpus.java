public class Wumpus extends Creature {

    public Wumpus(String name, String description, Level level, Level.Room currentRoom) {
        super(name, description, level, currentRoom);
    }

    @Override
    protected Level.Room chooseNextRoom() {
        Level.Room playerRoomOrNeighbor = lookForPlayer();
        Level.Room potential = currentRoom.getRandomNeighbor();
        if(potential == playerRoomOrNeighbor) potential = currentRoom.getRandomNeighbor(); //testing to ensure Wumpus doesn't move towards player
        return potential;
    }

}