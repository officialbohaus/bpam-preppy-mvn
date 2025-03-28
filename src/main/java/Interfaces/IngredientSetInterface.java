package Interfaces;

import Main.IIDContainer;

import java.util.ArrayList;

public interface IngredientSetInterface {
    public ArrayList<IIDContainer> getIngredients(int...indices);

    public ArrayList<IIDContainer> getIngredientSet();

    public String getDescription();

    public String getName();

    public int getSize();

    public IIDContainer getIngredient(int index);

    public void remove(IIDContainer iidContainer);

    public void remove(IIDContainer[] iidContainers);

    public ArrayList<IIDContainer> compare(IngredientSetInterface otherSet);

    public ArrayList<IIDContainer> getDifferenceSet();

    public boolean isEqual(IngredientSetInterface otherSet);

    public boolean contains(IIDContainer iidContainer);
}
