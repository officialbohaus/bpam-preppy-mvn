package Main;


import Interfaces.IIDContainerInterface;
import Interfaces.IngredientSetInterface;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * represents the differences between two <code>IngredientSet</code> objects. One object is the primarySet (likely
 * the object calling the comparison method), and the other is thecontrastSet.
 * <p>
 *     uses four lists that represents the similarity of <code>IIDContainers</code> between two sets
 *     <p> <code>fullMatchList</code> - IIDContainers that occur identically in both sets
 *     <p> <code>partialMatchList</code> - IIDContainers from the contrastSet that match on name but differ by some other tag to an IIDContainer in the primarySet
 *     <p> <code>noMatchInContrastList</code> - IIDContainers from the contrastSet that do not match on any level some IIDContainer in the primarySet
 *     <p> <code>noMatchInPrimaryList</code> - IIDContainers from the primarySet that did not have an exact matching IIDContainer in the contrastSet
 */
public class SetDifference {

    // { A B C D } + { C d e F } = { C }
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

    // { A B C D } + { C d e F } = { d }
    private static IngredientSet getPartialMatchList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {
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

    // { A B C D } + { C d e F } = { e F }
    private static IngredientSet getNoMatchInContrastList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {
        ArrayList<IIDContainerInterface> noMatchInContrastList = new ArrayList<>();
        for (IIDContainerInterface contrastIID : contrastSet.getIngredientSet()) {
            Iterator<IIDContainerInterface> iterator = primarySet.getIngredientSet().iterator();
            boolean foundAnyMatch = false;
            while (iterator.hasNext() && !foundAnyMatch) {
                IIDContainerInterface nextIID = iterator.next();
                if (nextIID.isIID(contrastIID) || nextIID.getNameTag().equals(contrastIID.getNameTag())) {
                    foundAnyMatch = true;
                }
            }
            if (!foundAnyMatch) {
                noMatchInContrastList.add(contrastIID);
            }
        }

        String setName = "noMatchInContrastList " + primarySet.getName() + "/" + contrastSet.getName();
        String setDesc = "noMatchInContrastList for primarySet \"" + primarySet.getName()  + "\" against contrastSet \"" + contrastSet.getName()  + "\"";
        return new IngredientSet(setName, setDesc, noMatchInContrastList);
    }

    // { A B C D } + { C d e F } = { A B }
    private static IngredientSet getNoMatchInPrimaryList(IngredientSetInterface primarySet, IngredientSetInterface contrastSet) {
        IngredientSet reversedDiff = SetDifference.getNoMatchInContrastList(contrastSet, primarySet); // can just call counterpart method, and reverse the inputs
        String setName = "noMatchInPrimaryList " + primarySet.getName() + "/" + contrastSet.getName();
        String setDesc = "noMatchInPrimaryList for primarySet \"" + primarySet.getName()  + "\" against contrastSet \"" + contrastSet.getName()  + "\"";
        return new IngredientSet(setName, setDesc, reversedDiff.getIngredients());
    }
        //        ArrayList<IIDContainerInterface> noMatchInPrimaryList = new ArrayList<>();
//        for (IIDContainerInterface primaryIID : primarySet.getIngredientSet()) {
//            Iterator<IIDContainerInterface> iterator = contrastSet.getIngredientSet().iterator();
//            boolean foundAnyMatch = false;
//            while (iterator.hasNext() && !foundAnyMatch) {
//                IIDContainerInterface nextIID = iterator.next();
//                if (nextIID.isIID(primaryIID) || nextIID.getNameTag().equals(primaryIID.getNameTag())) {
//                    foundAnyMatch = true;
//                }
//            }
//            if (!foundAnyMatch) {
//                noMatchInPrimaryList.add(primarySet);
//            }
}

