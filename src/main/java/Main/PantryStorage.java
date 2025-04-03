package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class PantryStorage implements Serializable {

    private final ArrayList<PantryNode> pantryList;

    public PantryStorage() {
        pantryList = new ArrayList<PantryNode>();
    }

    public void addPantryNode(PantryNode node) {
        pantryList.add(node);
    }

    public ArrayList<PantryNode> getPantryNodes() {
        return new ArrayList<PantryNode>(pantryList);
    }

    public String toString() {
        String msg = "";
        for (PantryNode node : pantryList) {
            msg += node.toString() + "\n";
        }
        return msg;
    }

}
