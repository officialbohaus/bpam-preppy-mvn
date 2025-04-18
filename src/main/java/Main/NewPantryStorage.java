package Main;

import Interfaces.IIDContainerInterface;

import java.util.ArrayList;

public class NewPantryStorage {
    private IIDSet pantry;

    public NewPantryStorage() {
        pantry = new IIDSet(new ArrayList<>());
    }

    public void addIID(IIDContainerInterface iid) {
        pantry.addIngredient(iid);
    }

    public void addIID(IIDContainerInterface iid, int quantity) {
        pantry.addIngredient(iid, quantity);
    }

    public void remove(IIDContainerInterface iid) {
        pantry.remove(iid);
    }

    public void size() {
        pantry.size();
    }
}
