package Main;

import Interfaces.IIDTag;
import Interfaces.IngredientSetInterface;
import LegacyFiles.IIDGenerator;
import LegacyFiles.Step;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class ShawnsDriver {
    public static void main(String[] args) {

        IIDContainer beef = new IIDContainer("Beef", "I'm the beef.", new IIDTag[]{IngredientType.PROTEIN, CookState.GRILLED, CutState.SLICED, IngredientUnit.GRAM});
        IIDContainer duck = new IIDContainer("Duck", "quack!", new IIDTag[]{IngredientType.PROTEIN, CookState.FRIED, CutState.WHOLE, IngredientUnit.MILLILITER});
        IIDContainer chicken = new IIDContainer("Chicken", "Some chicken noise", new IIDTag[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
        IIDContainer testSpinach = new IIDContainer("Spinach", "Just a vegetable", new IIDTag[]{IngredientType.VEGGIE, CookState.ROASTED, CutState.WHOLE, IngredientUnit.GRAM});
        IIDContainer testChicken = new IIDContainer("Chicken", "Some other chicken noise", new IIDTag[]{IngredientType.PROTEIN, IngredientType.MISC, CookState.ROASTED, CutState.GROUND,IngredientUnit.GRAM});

        IIDContainer[] iidContainers = {beef, duck, testChicken};

        IIDContainer[] iidContainersToBeRemoved = {beef, duck};

        IIDContainer[] iidContainersToBeRemovedTest = {beef, testSpinach};

        ArrayList<IIDContainer> ingredientSet = new ArrayList<>(Arrays.asList(iidContainers));
        ArrayList<IIDContainer> otherSet = new ArrayList<>(Arrays.asList(iidContainersToBeRemoved));

        IngredientSet setOne = new IngredientSet(iidContainers);
        IngredientSet setTwo = new IngredientSet(iidContainersToBeRemoved);
        IngredientSet setThree = new IngredientSet(iidContainersToBeRemovedTest);

//        ArrayList<IIDContainer> differenceSet = setOne.compare(setThree);
//
//        System.out.println(differenceSet);

        System.out.println(setOne.getIngredients(2));

    }
}

