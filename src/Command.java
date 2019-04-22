public abstract class Command implements CommandFramework{
    Player player;

    public Command(Player player){
        this.player = player;
    }

    public String getLastWord(String userInput){
        String[] words = userInput.split(" ");
        return words[words.length - 1];
    }
}
