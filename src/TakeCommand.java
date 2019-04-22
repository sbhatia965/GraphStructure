public class TakeCommand extends Command{
    private Item toAdd;
    private String itemName;

    public TakeCommand(Player player){
        super(player);
    }

    @Override
    public void Init(String userString) {
        itemName = getLastWord(userString);
        toAdd = player.getCurrentRoom().removeItem(itemName);
    }

    @Override
    public void execute() {
        if(toAdd == null) System.out.println(player.getCurrentRoom().getName() + " does not contain " + itemName);
        else{
            player.addItem(toAdd);
            System.out.println(toAdd.getName() + " added to your inventory");
        }
        System.out.println();
    }
}
