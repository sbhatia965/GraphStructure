public class NullCommand extends Command{

    public NullCommand(Player player){
        super(player);
    }

    @Override
    public void Init(String userString) {

    }

    @Override
    public void execute() {
        System.out.println("Not a valid command");
        System.out.println();
    }
}
