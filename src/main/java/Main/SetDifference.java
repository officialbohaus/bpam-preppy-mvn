package Main;


import Interfaces.IIDContainerInterface;
import Interfaces.IngredientSetInterface;

import java.util.ArrayList;

/**
 * represents the differences between two <code>IngredientSet</code> objects. One object is the primarySet (likely
 * the object calling the comparison method), and the other is thecontrastSet.
 * <p>
 *     uses four lists that represents the similarity of <code>IIDContainers</code> between two sets
 *     <p> <code>fullMatchList</code> - IIDContainers that occur identically in both sets
 *     <p> <code>partialMatchList</code> - IIDContainers from the contrastSet that match on name but differ by some other tag to an IIDContainer in the primarySet
 *     <p> <code>noMatchList</code> - IIDContainers from the contrastSet that do not match on any level some IIDContainer in the primarySet
 *     <p> <code>missingMatchList</code> - IIDContainers from the primarySet that did not have an exact matching IIDContainer in the contrastSet
 */
public class SetDifference {


    public static IngredientSet getFullMatchList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {
        ArrayList<IIDContainerInterface> fullMatchList = new ArrayList<>();
        for (IIDContainerInterface primaryIID : primarySet.getIngredientSet()) {
            if (contrastSet.contains(primaryIID)) {
                fullMatchList.add(primaryIID);
            }
        }
        String setName = "fullMatchList " + primarySet.getName() + "/" + contrastSet.getName();
        String setDesc = "fullMatchList for primarySet \"" + primarySet.getName()  + "\" against contrastSet \"" + contrastSet.getName()  + "\"";
        return new IngredientSet(setName, setDesc, fullMatchList);
    }

    private static ArrayList<IIDContainerInterface> getPartialMatchList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {
        ArrayList<IIDContainerInterface> partialMatchList = new ArrayList<>();
        for (IIDContainerInterface primaryIID : primarySet.getIngredientSet()) {
            if (!contrastSet.contains(primaryIID)) {
                for (IIDContainerInterface contrastIID : contrastSet.getIngredientSet()) {
                    if (contrastIID.getNameTag().equals(primaryIID.getNameTag())) {
                        partialMatchList.add(contrastIID);
                    }
                }
            }
        }
        String setName = "partialMatchList " + primarySet.getName() + "/" + contrastSet.getName();
        String setDesc = "partialMatchList for primarySet \"" + primarySet.getName()  + "\" against contrastSet \"" + contrastSet.getName()  + "\"";
        return new IngredientSet(setName, setDesc, partialMatchList);
    }

    private static ArrayList<IIDContainerInterface> getNoMatchList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {

    }

    private static ArrayList<IIDContainerInterface> getMissingMatchList(IIDContainerInterface primarySet, IIDContainerInterface contrastSet) {

    }

    public SetDifference() {}

}
