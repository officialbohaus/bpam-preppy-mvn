package LegacyFiles;

import Interfaces.IngredientSetInterface;

import java.util.ArrayList;
import java.util.Iterator;

public class IngredientSet implements IngredientSetInterface, Iterable<String> {
    /*
     * The Others.IngredientSet represents a collection of ingredients.
     * - This class is intended to be used to group IIDs together as needed by user (we are the users).
     *      - The relation between IIDs can be described in the description String, if desired
     *      - e.g. all IIDs related to a recipe, etc.
     * - The set does not need to maintain a quantity for any IID
     * - This class should have a method that compares it to another Others.IngredientSet, returning the
     *   differences between the two.
     *      - For now, lets return a String array with each difference found. Example:
     *          [Chicken-Pr-Raw --> Chicken-Pr-Bake]
     *          [     OTHER-IID --> THIS-IID       ]
     * - Should be able to add and remove IIDs from Others.IngredientSet
     * - Should return a COPY of the Others.IngredientSet array
     *      - return a copy, because returning the actual array will break encapsulation.
     * - Should have a description String that the user can choose to fill
     *      - This description should describe what relation all the IIDs have
     *      - Adherence to this relation within the set is maintained by the user, not by code.
     *          - (i.e. if the user decides the relation should be "vegan ingredients", and the user
     *              later adds eggs, the invalid relation is on them.)
     * - Should have appropriate getters and setters
     * - Constructor should be overloaded with multiple options to fill the set.
     *  - empty
     *  - multiple args
     *  - some pre-existing iterable of IIDs (TODO once iterables are covered in class)
     *  -- all with optional description counterpart (6 constructors total)
     */

    private ArrayList<String> ingredients;
    private String description;

    public IngredientSet() {
        this("", (String[]) null);
    }

    // public Others.IngredientSet(String description) {
    //     this("", (String[]) null);
    // }

    // public Others.IngredientSet(String... IIDs) {
    //     this("", IIDs);
    // }

    public IngredientSet(String description, String... IIDs) {
        this.description = description;
        for (String IID : IIDs) {
            ingredients.add(IID);
        }
    }

    public void addIID(String IID) {
        if (!contains(IID)) {
            ingredients.add(IID);
        } else {
            // TODO: Throw exception
        }
    }

    @Override
    public ArrayList<String> getIngredients() { return new ArrayList<String>(ingredients); }

    @Override
    public String getDescription() { return description; }

    @Override
    public ArrayList<String> compare(IngredientSetInterface otherSet) {
        return null;
    }


    public ArrayList<String> compare(IngredientSet otherSet) {
        // this will be the string list that holds each difference
        ArrayList<String> differenceList = new ArrayList<String>();

        // loop through each IID in our ingredient set
        for (String thisIID : ingredients) {

            // get iterator for passed ingredient set (allows us to loop through the values in it)
            Iterator<String> otherSetIterator = otherSet.iterator();

            // end cases for loop
            boolean foundIID = false;
            boolean partialMatch = false;

            // loop while we have more IIDs in otherSet, and we haven't found a full or partial match
            while(otherSetIterator.hasNext() && !foundIID && !partialMatch) {
                String otherIID = otherSetIterator.next();
                if (thisIID.equals(otherIID)) {
                    // if we find an exact match
                    foundIID = true;
                } else {
                    // test if we match on name, but some IID component is different
                    String otherName = IIDParser.getName(otherIID);
                    String thisName = IIDParser.getName(thisIID);
                    if (otherName.equals(thisName)) {
                        partialMatch = true;
                        differenceList.add(
                                otherIID + " --> " + thisIID
                        );
                    }
                }
            }
            if (!foundIID && !partialMatch) {
                // we didn't find any match, we know other set did not contain this IID at all
                differenceList.add(
                        "NONE" + " --> " + thisIID
                );
            }
        }
        return differenceList;
    }

    @Override
    public boolean contains(String IID) {
        for (String ingredient : ingredients) {
            if (ingredient.equals(IID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return ingredients.iterator();
        // TODO should probably be more checks in place before straight sending this through

    }

    @Override
    public void remove(String IID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }



    // @Override
    // public String getID() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getID'");
    // }

    // @Override
    // public String getName() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getName'");
    // }

    // @Override
    // public IngredientType getType() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getType'");
    // }

    // @Override
    // public boolean isEqual(Ingredient ingredient) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isEqual'");
    // }
}