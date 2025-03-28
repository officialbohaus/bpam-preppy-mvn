package Main;

import Exceptions.IncompatibleArraysException;
import Exceptions.InvalidRequestException;
import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;
import Interfaces.IngredientSetInterface;
import LegacyFiles.IIDParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IngredientSet implements IngredientSetInterface {

    private String name, description;
    private ArrayList<IIDContainerInterface> ingredientSet, differenceSet;
    private ArrayList<String> differenceString;
    private final ArrayList<Integer> modCounts;
    private Iterator<IIDContainer> iterator;

    // Test Constructors
    public IngredientSet() {
        this(new ArrayList<>());
    }
    public IngredientSet(IIDContainer... iidContainers) {
        this(null, null, new ArrayList<>(Arrays.asList(iidContainers)));
    }

    public IngredientSet(ArrayList<IIDContainer> iidContainerArrayList) {
        this(null, null, iidContainerArrayList);
    }

    public IngredientSet(String name, String description, IIDContainer... iidContainerArray) {
        this(name, description, new ArrayList<>(Arrays.asList(iidContainerArray)));
    }

    public IngredientSet(String name, String description, ArrayList<IIDContainer> iidContainerArrayList) {
        this.name = name;
        this.description = description;
        ingredientSet = new ArrayList<>(iidContainerArrayList);
        modCounts = new ArrayList<>();

        for (int index = 0; index < iidContainerArrayList.size(); index++) {
            modCounts.add(index, iidContainerArrayList.get(index).getModCount());
        }
    }

    public void addIngredient(IIDContainer ingredient) {
        ingredientSet.add(ingredient);
        modCounts.add(ingredient.getModCount());
    }

    public void addIngredients(IIDContainer... ingredients) {
        for (IIDContainer ingredient : ingredients) {
            ingredientSet.add(ingredient);
            modCounts.add(ingredient.getModCount());
        }
    }

    public ArrayList<IIDContainerInterface> getIngredientSet() {
        return new ArrayList<IIDContainerInterface>(ingredientSet);
    }

    public int getIngredientIndex(IIDContainer ingredient) {
        if (!ingredientSet.contains(ingredient)) { throw new InvalidRequestException(); }
        return ingredientSet.indexOf(ingredient);
    }

    @Override
    public IIDContainerInterface getIngredient(int index) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        return ingredientSet.get(index);
    }

    @Override
    public ArrayList<IIDContainerInterface> getIngredients(int...indices) {
        for (int index : indices) {
            if (index < 0 || index >= ingredientSet.size()) {
                throw new IndexOutOfBoundsException();
            }
        }

        ArrayList<IIDContainerInterface> ingredients = new ArrayList<>();

        for (int index : indices) {
            ingredients.add(ingredientSet.get(index));
        }

        return new ArrayList<IIDContainerInterface>(ingredients);
    }

    @Override
    public void remove(IIDContainer ingredient) {
        if (!ingredientSet.contains(ingredient)) { throw new InvalidRequestException(ingredient + "is not in the ingredient set."); }
        ingredientSet.remove(ingredient);
    }

    public void remove(IIDContainer[] ingredients) {
        ArrayList<IIDContainer> invalidContainers = new ArrayList<>();
        for (IIDContainer thisIngredient : ingredients) {
            if (!ingredientSet.contains(thisIngredient)) {
                invalidContainers.add(thisIngredient);
            }
        }

        if (!invalidContainers.isEmpty()) {
            if (invalidContainers.size() == 1) {
                throw new InvalidRequestException("Unable to remove a container that is not in IngredientSet: " + invalidContainers);
            } else {
                throw new InvalidRequestException("Unable to remove containers that are not in IngredientSet: " + invalidContainers);
            }
        }

        Iterator<IIDContainerInterface> iterator = ingredientSet.iterator();

        while (iterator.hasNext()) {
            IIDContainerInterface iteratorContainer = iterator.next();
            for (IIDContainer thisIngredient : ingredients) {
                if (iteratorContainer.equals(thisIngredient)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<IIDContainerInterface> compare(IngredientSetInterface otherSet) {
        differenceSet = new ArrayList<>();

        for (IIDContainerInterface thisIngredient : otherSet.getIngredientSet()) {
            if (!ingredientSet.contains(thisIngredient)) {
                differenceSet.add(thisIngredient);
            }
        }

        return new ArrayList<>(differenceSet);
    }

    public ArrayList<IIDContainerInterface> getDifferenceSet() {
        return new ArrayList<IIDContainerInterface>(differenceSet);
    }

    public boolean isEqual(IngredientSetInterface otherSet) {
        if (otherSet.getIngredientSet().size() != ingredientSet.size()) {
            return false;
        }

        for (IIDContainerInterface thisContainer : otherSet.getIngredientSet()) {
            if (!ingredientSet.contains(thisContainer)) {
                return false;
            }
        }

        return true;
    }

    public boolean contains(IIDContainer ingredient) {
        return ingredientSet.contains(ingredient);
    }

    public int getSize() {
        return ingredientSet.size();
    }

    private void incrementModCount(int index) {
        int currentNumber = modCounts.get(index);
        modCounts.set(index, currentNumber + 1);
    }

    private int getModCount(int index) {
        return modCounts.get(index);
    }
}