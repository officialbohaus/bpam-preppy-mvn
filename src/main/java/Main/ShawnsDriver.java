package Main;

import Interfaces.IIDContainerInterface;
import Interfaces.TagDataInterface;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class ShawnsDriver {
    public static void main(String[] args) {

        IIDContainer beef = new IIDContainer("Beef", "I'm the beef.", new TagDataInterface[]{IngredientType.PROTEIN, CookState.GRILLED, CutState.SLICED, IngredientUnit.GRAM});
        IIDContainer duck = new IIDContainer("Duck", "quack!", new TagDataInterface[]{IngredientType.PROTEIN, CookState.FRIED, CutState.WHOLE, IngredientUnit.MILLILITER});
        IIDContainer chicken = new IIDContainer("Chicken", "Some chicken noise", new TagDataInterface[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
        IIDContainer testSpinach = new IIDContainer("Spinach", "Just a vegetable", new TagDataInterface[]{IngredientType.VEGGIE, CookState.ROASTED, CutState.WHOLE, IngredientUnit.GRAM});
        IIDContainer testChicken = new IIDContainer("Chicken", "Some other chicken noise", new TagDataInterface[]{IngredientType.PROTEIN, IngredientType.MISC, CookState.ROASTED, CutState.GROUND,IngredientUnit.GRAM});

        IIDContainer[] iidContainers = {beef, duck, chicken};

        IIDContainer[] iidContainersToBeRemoved = {beef, duck};

        IIDContainer[] iidContainersToBeRemovedTest = {beef, testSpinach};

        ArrayList<IIDContainer> ingredientSet = new ArrayList<>(Arrays.asList(iidContainers));
        ArrayList<IIDContainer> otherSet = new ArrayList<>(Arrays.asList(iidContainersToBeRemoved));

        IIDSet setOne = new IIDSet(iidContainers);
        IIDSet setTwo = new IIDSet(iidContainersToBeRemoved);
        IIDSet setThree = new IIDSet(iidContainersToBeRemovedTest);

//        setOne.remove(beef, chicken, testSpinach);

        for (IIDContainerInterface ingredient : setOne.getIngredientSet()) {
            System.out.println(ingredient);
        }

//        ArrayList<IIDContainer> differenceSet = setOne.compare(setThree);
//
//        System.out.println(differenceSet);

        System.out.println(setOne.getIngredients(2));

    }
}

