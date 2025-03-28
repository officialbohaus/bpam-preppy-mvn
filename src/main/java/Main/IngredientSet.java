package Main;

import Exceptions.IncompatibleArraysException;
import Exceptions.InvalidRequestException;
import Interfaces.IIDTag;
import Interfaces.IngredientSetInterface;
import LegacyFiles.IIDParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IngredientSet implements IngredientSetInterface {

    private String name, description;
    private ArrayList<IIDContainer> ingredientSet, differenceSet;
    private ArrayList<String> differenceString;
    private ArrayList<Integer> modCounts;
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
            modCounts.add(0);
        }
    }

    public void addIngredient(IIDContainer ingredient) {
        ingredientSet.add(ingredient);
        modCounts.add(0);
    }

    public ArrayList<IIDContainer> getIngredientSet() {
        return new ArrayList<>(ingredientSet);
    }

    public int getIngredientIndex(IIDContainer ingredient) {
        if (!ingredientSet.contains(ingredient)) { throw new InvalidRequestException(); }
        return ingredientSet.indexOf(ingredient);
    }

    // PASS IN IIDCONTAINER INSTEAD OF INDEX AND/OR INGREDIENTNAME
    public void addNameTag(int index, String newNameTag) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).unlock();
        ingredientSet.get(index).addNameTag(newNameTag);
        incrementModCount(index);
        ingredientSet.get(index).lock();
    }

    public void addDescriptorTag(int index, String newDescriptorTag) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).unlock();
        ingredientSet.get(index).addDescriptorTag(newDescriptorTag);
        incrementModCount(index);
        ingredientSet.get(index).lock();
    }

    public void addNicknameTag(int index, String newNicknameTag) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).unlock();
        ingredientSet.get(index).addNicknameTag(newNicknameTag);
        incrementModCount(index);
        ingredientSet.get(index).lock();
    }

    public void addTag(int index, IIDTag newTag) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).unlock();
        ingredientSet.get(index).addTag(newTag);
        incrementModCount(index);
        ingredientSet.get(index).lock();
    }

    public void addTags(int index, IIDTag[] newTags) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).unlock();
        ingredientSet.get(index).addTags(Arrays.asList(newTags));
        incrementModCount(index);
        ingredientSet.get(index).lock();
    }

    public void addTags(int index, List<IIDTag> newTags) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        ingredientSet.get(index).addTags(newTags);
        incrementModCount(index);
    }

    @Override
    public IIDContainer getIngredient(int index) {
        if (index < 0 || index >= ingredientSet.size()) { throw new IndexOutOfBoundsException(); }
        return ingredientSet.get(index);
    }

    @Override
    public ArrayList<IIDContainer> getIngredients(int...indices) {
        for (int index : indices) {
            if (index < 0 || index >= ingredientSet.size()) {
                throw new IndexOutOfBoundsException();
            }
        }

        ArrayList<IIDContainer> ingredients = new ArrayList<>();

        for (int index : indices) {
            ingredients.add(ingredientSet.get(index));
        }

        return new ArrayList<>(ingredients);
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

        Iterator<IIDContainer> iterator = ingredientSet.iterator();

        while (iterator.hasNext()) {
            IIDContainer iteratorContainer = iterator.next();
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
    public ArrayList<IIDContainer> compare(IngredientSetInterface otherSet) {
        differenceSet = new ArrayList<>();

        for (IIDContainer thisIngredient : otherSet.getIngredientSet()) {
            if (!ingredientSet.contains(thisIngredient)) {
                differenceSet.add(thisIngredient);
            }
        }

        return new ArrayList<>(differenceSet);
    }

    public ArrayList<IIDContainer> getDifferenceSet() {
        return new ArrayList<>(differenceSet);
    }

    public boolean isEqual(IngredientSetInterface otherSet) {
        if (otherSet.getIngredientSet().size() != ingredientSet.size()) {
            return false;
        }

        for (IIDContainer thisContainer : otherSet.getIngredientSet()) {
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