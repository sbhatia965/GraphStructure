import java.util.*;

public class Main {
    private static Level g = new Level();
    private static HashMap<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        initCommands();

        g.addNode("hall", "a long dank hallway");
        g.addNode("closet", "a huge closet, not a lot of clothes present");
        g.addNode("bedroom", "a bedroom with a three tiny beds");
        g.addNode("kitchen","place with food");
        g.addNode("yard","a yard with fruits and also chickens");
        g.addNode("dungeon","a place to sleep with the fishes");
        g.addNode("bathroom","a place to bathe");
        g.addNode("balcony","be careful it's really far from the ground");
        g.addNode("void","don't come here");

        g.addDirectedEdge("hall", "bedroom");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("hall", "kitchen");
        g.addUndirectedEdge("hall", "yard");
        g.addUndirectedEdge("hall", "bedroom");
        g.addUndirectedEdge("hall", "bathroom");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("bedroom", "balcony");
        g.addUndirectedEdge("bedroom", "bathroom");
        g.addDirectedEdge("balcony", "void");
        g.addUndirectedEdge("kitchen", "yard");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("bedroom", "closet");

        g.getRoom("hall").addItem("lobster", "a shiny red lobster");
        g.getRoom("hall").addItem("bat", "a wooden baseball bat, seems a bit used up");
        g.getRoom("closet").addItem("Wumpus sleepy-time gun", "an object that puts Wumpuses to sleep. Is old, but has never been used");
        g.getRoom("bedroom").addItem("cat","really fat");
        g.getRoom("hall").addItem("hat","really handsome");
        g.getRoom("bathroom").addItem("mat","really flat");
        g.getRoom("dungeon").addItem("rat","really fat");

        Player player = g.getPlayer();
        player.setCurrentRoom(g.getRoom("hall"));
        Level.Room current = player.getCurrentRoom();

        for(int i = 0; i < 10; i++){
            Chicken chicken = new Chicken("bob", "just a chicken", g, g.getRoom("kitchen"));
            g.addCreature(chicken);
            chicken.getCurrentRoom().addCreatureToRoom(chicken);
        }
        Wumpus wumpus = new Wumpus("UwU", "a scared Wumpus", g, g.getRoom("yard"));
        g.addCreature(wumpus);
        wumpus.getCurrentRoom().addCreatureToRoom(wumpus);

        PopStar popStar = new PopStar("OwO", "a PopStar that chases you", g, g.getRoom("dungeon"));
        g.addCreature(popStar);
        popStar.getCurrentRoom().addCreatureToRoom(popStar);

        String response;
        Scanner in = new Scanner(System.in);
        do{
            current = player.getCurrentRoom();
            System.out.println("You are currently in the " + current.getName());
            System.out.println("You can: look, go <roomname>, take <itemname>, drop <itemname>, add room <roomname>, quit");
            System.out.print("What do you want to do? >");
            response = in.nextLine();

            Command command = lookupCommand(response);
            command.execute();
            g.updateAllCreatures();

        }while(!response.equals("quit"));
    }

    private static Command lookupCommand(String response) {
        String commandWord = getFirstWord(response);
        Command c = commands.get(commandWord);
        if(c == null) return new NullCommand(g.getPlayer());
        c.Init(response);
        return c;
    }

    private static String getFirstWord(String response) {
        String[] words = response.split(" ");
        return words[0];
    }

    private static  void initCommands() {
        commands.put("take", new TakeCommand(g.getPlayer()));
        commands.put("look", new LookCommand(g.getPlayer()));
        commands.put("add room", new AddRoomCommand(g.getPlayer(), g));
        commands.put("go", new GoCommand(g.getPlayer()));
        commands.put("drop", new DropCommand(g.getPlayer()));
        commands.put("quit", new QuitCommand(g.getPlayer()));
    }
}