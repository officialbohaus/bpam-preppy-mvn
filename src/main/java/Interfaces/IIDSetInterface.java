package Interfaces;

import java.util.ArrayList;


/**
 * the interface for <code>IngredientSet</code>
 */
public interface IIDSetInterface {
    public ArrayList<IIDContainerInterface> getIngredients(int...indices);

    public ArrayList<IIDContainerInterface> getIngredientSet();

    public void addIngredient(IIDContainerInterface ingredient);

    public void addIngredients(IIDContainerInterface... ingredients);

    public String getDescription();

    public String getName();

    public int getSize();

    public IIDContainerInterface getIngredient(int index);

//    public void add(IIDContainerInterface iidContainer);



    public void remove(IIDContainerInterface iidContainer);

    public void remove(IIDContainerInterface[] iidContainers);


    public ArrayList<IIDContainerInterface> compare(IIDSetInterface otherSet);

    public ArrayList<IIDContainerInterface> getDifferenceSet();

    public boolean isEqual(IIDSetInterface otherSet);

    public boolean contains(IIDContainerInterface iidContainer);

}

/*
    should hold multiple IIDContainers in a "locked" state
        shouldn't allow incomplete IIDContainers, nor allow contained IIDContainers to be modified
    should be able to provide a list of contained IIDContainers
    should have a description, provided by user in constructor to define what the relation is between IIDContainers
    should have a name defining the relationship (e.g. gluten-free grains)
    should provide add method to add an IIDContainer
    should provide remove method to remove an IIDContainer
        these methods should not allow duplicate additions, or invalid removals
        can target IDDContainer for removal by specifying name + descriptor tag (or providing an IIDContainer that matches)
    should compare & list differences between two sets (see legacy code for example)
 */