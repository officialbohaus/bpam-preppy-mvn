package Main;

import Filters.FilterByDietary;
import Filters.FilterByMethod;
import Interfaces.FilterInterface;
import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;
import LegacyFiles.OldIIDSet;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Filter;

public class ShawnsDriver {
    public static void main(String[] args) {

        IIDContainer beef = new IIDContainer("Beef", "I'm the beef.", new IIDTag[]{IngredientType.PROTEIN, CookState.GRILLED, CutState.SLICED, IngredientUnit.GRAM});
        IIDContainer duck = new IIDContainer("Duck", "quack!", new IIDTag[]{IngredientType.PROTEIN, CookState.FRIED, CutState.WHOLE, IngredientUnit.MILLILITER});
        IIDContainer chicken = new IIDContainer("Chicken", "Some chicken noise", new IIDTag[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
        IIDContainer testSpinach = new IIDContainer("Spinach", "Just a vegetable", new IIDTag[]{IngredientType.VEGGIE, CookState.ROASTED, CutState.WHOLE, IngredientUnit.GRAM});
        IIDContainer testChicken = new IIDContainer("Chicken", "Some other chicken noise", new IIDTag[]{IngredientType.PROTEIN, IngredientType.MISC, CookState.ROASTED, CutState.GROUND, IngredientUnit.GRAM});
        IIDContainer uninstantiatedIID;

        IIDContainerInterface[] iids = {beef, duck, chicken, testSpinach, testChicken};

        OldIIDSet oldIIDSet = new OldIIDSet(iids);
        IIDSet iidSet = new IIDSet(iids);

        System.out.println("IIDCount: " + iidSet.size());

        Recipe newRecipe = new Recipe("New Recipe", "This is a new recipe.", iidSet);
        newRecipe.addStep("Chop some onions");
        newRecipe.addStep("Sautee onions");
        newRecipe.addStep("Voila");
        System.out.println(newRecipe);
    }
}

