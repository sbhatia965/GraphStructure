public class DropCommand extends Command{
    private Item i;
    private String itemName;

    public DropCommand(Player player){
        super(player);
    }

    @Override
    public void Init(String userString) {
        itemName = getLastWord(userString);
        i = player.removeItem(itemName);
    }

    @Override
    public void execute() {
        if(i == null) System.out.println("You do not have " + itemName);
        else{
            player.getCurrentRoom().addItem(i);
            System.out.println(i.getName() + " dropped");
        }
        System.out.println();
    }
}
