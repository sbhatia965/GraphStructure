public abstract class Creature {
    protected Level l;
    protected Player player;
    protected Level.Room currentRoom;
    protected String name, description;

    public Creature(String name, String description, Level level, Level.Room currentRoom){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
        this.l = level;
        this.player = l.getPlayer();
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
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

    public void act(){
        this.move();
    }

    private void move() {
        Level.Room next = this.chooseNextRoom();
        if(next != null) this.moveToRoom(next);
        else this.moveRandom();
    }

    protected abstract Level.Room chooseNextRoom();

    public void moveToRoom(Level.Room nextRoom){
        currentRoom.removeCreatureFromRoom(this);
        this.setCurrentRoom(nextRoom);
        currentRoom.addCreatureToRoom(this);
    }

    public void moveRandom(){
        Level.Room nextRoom = currentRoom.getRandomNeighbor();
        this.moveToRoom(nextRoom);
    }

    public Level.Room lookForPlayer() {
        Level.Room playerRoom = player.getCurrentRoom();

        for(Level.Room r : currentRoom.getNeighbors()){
            if(r == playerRoom) return r;
            else{
                for(Level.Room n : r.getNeighbors()){
                    if(n == playerRoom) return r;
                }
            }
        }

        return null;
    }
}