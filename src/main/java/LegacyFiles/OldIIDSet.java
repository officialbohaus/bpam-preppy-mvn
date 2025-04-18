package LegacyFiles;

import Exceptions.InvalidRequestException;
import Interfaces.IIDContainerInterface;
import Main.IIDContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OldIIDSet {
    private String name, description;
    private int ingredientCount;
    private HashMap<IIDContainerInterface, Integer> iidAndQuantity;

    public OldIIDSet(IIDContainerInterface... iids) {
        this(null, null, new ArrayList<>(Arrays.asList(iids)));
    }

    public OldIIDSet(ArrayList<IIDContainerInterface> iids) {
        this(null, null, iids);
    }

    public OldIIDSet(String name, String description, IIDContainerInterface... iids) {
        this(name,description, new ArrayList<>(Arrays.asList(iids)));
    }

    public OldIIDSet(String name, String description, ArrayList<IIDContainerInterface> iids) {
        this.name = name;
        this.description = description;
        iidAndQuantity = new HashMap<>(iids.size());

        for (IIDContainerInterface iid : iids) {
            iidAndQuantity.put(iid, 0);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<IIDContainerInterface> getIIDSet() {
        return new ArrayList<>(iidAndQuantity.keySet());
    }

    public void addIngredient(IIDContainerInterface iid) {
        if (!iidAndQuantity.containsKey(iid)) {
            iidAndQuantity.put(iid, 0);
            ingredientCount++;
        }
    }

    // Also setQuantity()
    public void addIngredient(IIDContainerInterface iid, int quantity) {
        iidAndQuantity.put(iid, quantity);
        ingredientCount++;
    }

    public int getQuantity(IIDContainerInterface iid) {
        return iidAndQuantity.get(iid);
    }

    public void removeIngredient(IIDContainerInterface iid) {
        if (ingredientCount == 0) { throw new InvalidRequestException(); }
        iidAndQuantity.remove(iid);
        ingredientCount--;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }
}
