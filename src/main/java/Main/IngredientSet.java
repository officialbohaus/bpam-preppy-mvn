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

// functionality:
/*
    should hold multiple IIDContainers in a "locked" state
    should be able to provide a list of contained IIDContainers
    should have a description, provided by user in constructor to define what the relation is between IIDContainers
    should have a name defining the relationship (e.g. gluten free grains)
    should provide add method to add an IIDContainer
    should provide remove method to remove an IIDContainer
        these methods should not allow duplicate additions, or invalid removals
        can target IDDContainer for removal by specifying name + descriptor tag (or providing an IIDContainer that matches)
    should compare & list differences between two sets (see legacy code for example)
 */