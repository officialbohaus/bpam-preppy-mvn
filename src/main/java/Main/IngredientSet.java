package Main;

import Interfaces.IngredientSetInterface;
import LegacyFiles.IIDParser;

import java.util.ArrayList;
import java.util.Iterator;

public class IngredientSet implements IngredientSetInterface {

    public IngredientSet()

    @Override
    public ArrayList<String> getIngredients() {
        return null;
    }

    @Override
    public void remove(String IID) {

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public ArrayList<String> compare(IngredientSetInterface otherSet) {
        return null;
    }

    @Override
    public boolean contains(String IID) {
        return false;
    }
}