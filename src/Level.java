import java.util.*;

public class Level {
    private HashMap<String, Room> nodes;
    private ArrayList<Creature> creatures;
    private Player p;

    public Level() {
        nodes = new HashMap<>();
        creatures = new ArrayList<>();
        p = new Player("Suryan", "sad dab", null);
    }

    public Player getPlayer(){
        return this.p;
    }

    public void setPlayer(Player newPlayer){
        this.p = newPlayer;
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Room(name, description));
    }

    public void addDirectedEdge(String node1, String node2) {
        Room from = getRoom(node1);
        Room to = getRoom(node2);
        from.addNeighbor(to);
    }

    public void addUndirectedEdge(String node1, String node2) {
        Room one = getRoom(node1);
        Room two = getRoom(node2);
        one.addNeighbor(two);
        two.addNeighbor(one);
    }

    public Room getRoom(String name) {
        for (String n : nodes.keySet()) {
            if (n.equals(name)) return nodes.get(name);
        }
        return null;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    public void updateAllCreatures(){
        for(Creature c : creatures){
            c.act();
        }
    }

    public class Room {
        private String nodeName;
        private String description;
        private ArrayList<Room> neighbors;
        private ArrayList<Item> items;
        private ArrayList<Creature> creaturesInRoom;

        private Room(String name, String nodeDescription) {
            this.nodeName = name;
            this.description = nodeDescription;
            neighbors = new ArrayList<>();
            items = new ArrayList<>();
            creaturesInRoom = new ArrayList<>();
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private void addNeighbor(Room n) {
            neighbors.add(n);
        }

        public String getNeighborNames() {
            String output = "";
            for (Room n : neighbors) {
                output += n.getName() + ", ";
            }
            return output;
        }

        public Room getNeighbor(String name) {
            for (Room n : neighbors) {
                if (n.getName().equals(name)) return n;
            }
            return null;
        }

        public Room getNeighbor(int index) {
            return neighbors.get(index);
        }

        public String getName() {
            return nodeName;
        }

        public void setName(String nodeName) {
            this.nodeName = nodeName;
        }

        public ArrayList<Room> getNeighbors() {return neighbors;}

        public void setNeighbors(ArrayList<Room> newNeigbors) { this.neighbors = newNeigbors; }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems(){
            String output = "";
            for(Item i: items){
                output += i.getName() + ", ";
            }
            return output;
        }

        public void addItem(String name){
            Item i = new Item(name, "");
            items.add(i);
        }

        public void addItem(String name, String description){
            Item i = new Item(name, description);
            items.add(i);
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            for(Item i:items){
                if(i.getName().equals(name)){
                    items.remove(i);
                    return i;
                }
            }
            return null;
        }

        public boolean destroyItem(String name){
            for(Item i:items){
                if(i.getName().equals(name)){
                    items.remove(i);
                    return true;
                }
            }
            return false;
        }

        public void removeCreatureFromRoom(Creature creature) {
            creaturesInRoom.remove(creature);
        }

        public void addCreatureToRoom(Creature creature) {
            creaturesInRoom.add(creature);
        }

        public Room getRandomNeighbor() {
            return neighbors.get((int)(Math.random() * neighbors.size()));
        }

        public boolean isNeighbor(Level.Room room){
            return neighbors.contains(room);
        }

        public String displayCreatures(){
            String output = "";
            for(Creature c:creaturesInRoom) output += c.getName() + ", ";
            return output;
        }
    }
}
