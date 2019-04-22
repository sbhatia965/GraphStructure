public class AddRoomCommand extends Command{
    private Level l;
    private String roomName;

    public AddRoomCommand(Player player, Level level){
        super(player);
        this.l = level;
    }

    @Override
    public void Init(String userString) {
        roomName = getLastWord(userString);
    }

    @Override
    public void execute() {
        l.addNode(roomName, "");
        l.addDirectedEdge(player.getCurrentRoom().getName(), roomName);
        System.out.println();
    }
}
