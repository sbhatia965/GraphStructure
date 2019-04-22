public class LookCommand extends Command {

    public LookCommand(Player player) {
        super(player);
    }

    @Override
    public void Init(String userString) {

    }

    @Override
    public void execute() {
        System.out.println("You can go to " + player.getCurrentRoom().getNeighborNames());
        System.out.println("The room contains: " + player.getCurrentRoom().displayItems());
        System.out.println("The room has: " + player.getCurrentRoom().displayCreatures());
        System.out.println("You are currently in: " + player.getCurrentRoom().getDescription());
        System.out.println();
    }
}
