public class GoCommand extends Command{
    private String roomName;

    public GoCommand(Player player){
        super(player);
    }

    @Override
    public void Init(String userString) {
        this.roomName = getLastWord(userString);
    }

    @Override
    public void execute() {
        if(!player.moveToRoom(roomName)) System.out.println("You can't go to " + roomName + " try again");
        else player.setCurrentRoom(player.getCurrentRoom().getNeighbor(roomName));
        System.out.println();
    }
}
