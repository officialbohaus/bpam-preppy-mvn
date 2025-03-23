package Main;

import Interfaces.IngredientSetInterface;
import LegacyFiles.IIDParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IngredientSet {

    private ArrayList<IIDContainer> ingredientSet;
    private String name, description;

    public IngredientSet(IIDContainer[] iidContainerArray) {
        this(new ArrayList<>(Arrays.asList(iidContainerArray)));
    }

    public IngredientSet(ArrayList<IIDContainer> iidContainerArrayList) {
        ingredientSet = new ArrayList<>();
        ingredientSet.addAll(iidContainerArrayList);
    }

    public void addIngredient(IIDContainer ingredient) {
        ingredientSet.add(ingredient);
    }

    public ArrayList<IIDContainer> getIngredientSet() {
        return ingredientSet;
    }

    public int getIngredientIndex(IIDContainer ingredient) {
        return ingredientSet.indexOf(ingredient);
    }
}