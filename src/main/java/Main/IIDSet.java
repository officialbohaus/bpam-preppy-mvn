package Main;

import Exceptions.InvalidRequestException;
import Interfaces.IIDContainerInterface;
import Interfaces.IIDSetInterface;

import java.util.*;

public class IIDSet implements IIDSetInterface {

    private String name, description;
    private ArrayList<IIDContainerInterface> ingredientSet, differenceSet;
    private ArrayList<String> differenceString;
    private final ArrayList<Integer> modCounts;
    private HashMap<IIDContainerInterface, Integer> ingredientQuantity;
    private Iterator<IIDContainer> iterator;

    // Test Constructors
    public IIDSet() {
        this(new ArrayList<>());
    }
    public IIDSet(IIDContainerInterface... iidContainers) {
        this(null, null, new ArrayList<>(Arrays.asList(iidContainers)));
    }

    public IIDSet(ArrayList<IIDContainerInterface> iidContainerArrayList) {
        this(null, null, iidContainerArrayList);
    }

    public IIDSet(String name, String description, IIDContainer... iidContainerArray) {
        this(name, description, new ArrayList<>(Arrays.asList(iidContainerArray)));
    }

    public IIDSet(String name, String description, ArrayList<IIDContainerInterface> iidContainerArrayList) {
        this.name = name;
        this.description = description;
        ingredientSet = new ArrayList<>(iidContainerArrayList);
        modCounts = new ArrayList<>();
        ingredientQuantity = new HashMap<>(ingredientSet.size());

        for (int index = 0; index < iidContainerArrayList.size(); index++) {
            modCounts.add(index, iidContainerArrayList.get(index).getModCount());
            ingredientQuantity.put(ingredientSet.get(index), 0);
        }
    }

    public void addIngredient(IIDContainerInterface ingredient) {
        addIngredient(ingredient, 0);
    }

    public void addIngredient(IIDContainerInterface ingredient, int quantity) {
        ingredientSet.add(ingredient);
        ingredientQuantity.put(ingredient, quantity);
        modCounts.add(ingredient.getModCount());
    }

    // FIX THIS. IT MAY HAVE SOME REDUNDANT LOGIC.
    public void addIngredients(IIDContainerInterface... ingredients) {
        for (IIDContainerInterface ingredient : ingredients) {
            ingredientSet.add(ingredient);
            if (!ingredientQuantity.containsKey(ingredient)) {
                ingredientQuantity.put(ingredient, 0);
            }
            modCounts.add(ingredient.getModCount());
        }
    }

    public int getQuantity(IIDContainerInterface ingredient) {
        return ingredientQuantity.get(ingredient);
    }

    public ArrayList<IIDContainerInterface> getIIDSet() {
        return new ArrayList<IIDContainerInterface>(ingredientSet);
    }

    public int getIngredientIndex(IIDContainerInterface ingredient) {
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
    public ArrayList<IIDContainerInterface> getIngredientSet() {
        return new ArrayList<IIDContainerInterface>(ingredientSet);
    }

    @Override
    public void remove(IIDContainerInterface ingredient) {
        if (!ingredientSet.contains(ingredient)) { throw new InvalidRequestException(ingredient + "is not in the ingredient set."); }
        ingredientSet.remove(ingredient);
    }


//    public void remove(IIDContainer... ingredients) {
//        ArrayList<IIDContainer> invalidContainers = new ArrayList<>();
//        for (IIDContainer thisIngredient : ingredients) {

    public void remove(IIDContainerInterface... ingredients) {
        ArrayList<IIDContainerInterface> invalidContainers = new ArrayList<>();
        for (IIDContainerInterface thisIngredient : ingredients) {

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
            for (IIDContainerInterface thisIngredient : ingredients) {
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
    public ArrayList<IIDContainerInterface> compare(IIDSetInterface otherSet) {
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

    public boolean isEqual(IIDSetInterface otherSet) {
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

    public boolean contains(IIDContainerInterface ingredient) {
        return ingredientSet.contains(ingredient);
    }

    public int size() {
        return ingredientQuantity.size();
    }

    private void incrementModCount(int index) {
        int currentNumber = modCounts.get(index);
        modCounts.set(index, currentNumber + 1);
    }

    private int getModCount(int index) {
        return modCounts.get(index);
    }
}