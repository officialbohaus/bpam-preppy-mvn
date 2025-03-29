import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;
import Main.IIDContainer;
import Main.IIDSet;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SetDifferenceTest {

    private static final ArrayList<IIDContainerInterface> primaryIIDContainers = new ArrayList<>(Arrays.asList(
            new IIDContainer("Potato", "Baked Potato", new IIDTag[] { CookState.BAKED, CutState.WHOLE, IngredientType.VEGGIE, IngredientUnit.UNIT }),
            new IIDContainer("Chicken Breast", "Sliced Seared Chicken Breast", new IIDTag[] { CookState.SEARED, CutState.SLICED, IngredientType.PROTEIN, IngredientUnit.GRAM }),
            new IIDContainer( "Garlic", "Roasted Garlic Clove", new IIDTag[]{ CookState.ROASTED, CutState.WHOLE, IngredientType.VEGGIE, IngredientUnit.UNIT }),
            new IIDContainer( "Parsley", "Parsley Bunch", new IIDTag[]{ CookState.NONE, CutState.NONE, IngredientType.SPICE, IngredientUnit.UNIT }),
            new IIDContainer( "Lemon", "Lemon Wedge", new IIDTag[]{ CookState.RAW, IngredientType.FRUIT, CutState.SLICED, IngredientUnit.UNIT })
    ));
    private static final ArrayList<IIDContainerInterface> contrastIIDContainers = new ArrayList<>(Arrays.asList(
            new IIDContainer("Potato", "Raw Potato", new IIDTag[]{ CookState.RAW, CutState.WHOLE, IngredientType.VEGGIE, IngredientUnit.UNIT }),
            new IIDContainer("Chicken Breast", "Raw Chicken Breast", new IIDTag[]{ CookState.RAW, CutState.WHOLE, IngredientType.PROTEIN, IngredientUnit.UNIT }),
            new IIDContainer("Parsley", "Parsley Bunch", new IIDTag[]{ CookState.NONE, CutState.NONE, IngredientType.SPICE, IngredientUnit.UNIT }),
            new IIDContainer( "Milk", "Whole Milk", new IIDTag[]{ CookState.NONE, CutState.NONE, IngredientType.DAIRY, IngredientUnit.MILLILITER })
    ));
    public static final IIDSet primaryIIDSet = new IIDSet("Primary", "Primary Set", primaryIIDContainers);
    public static final IIDSet contrastIIDSet = new IIDSet("Contrast", "Constrast Set", contrastIIDContainers);

    // Intended output
    /*
        SetDifference.getFullMatchList(primaryIIDSet, contrastIIDSet);
            --> { "Parsley/Parsley Bunch/CookState:ROASTED/CutState:NONE/IngredientType:SPICE/IngredientUnit:UNIT" }

        SetDifference.getPartialMatchList(primaryIIDSet, contrastIIDSet);
            --> { "Potato/Raw Potato/CookState:RAW/CutState:WHOLE/IngredientType:VEGGIE/IngredientUnit:UNIT",
                  "Chicken Breast/Raw Chicken Breast/CookState:RAW/CutState:WHOLE/IngredientType:PROTEIN/IngredientUnit:GRAM" }

        SetDifference.getNoMatchInContrastList(primaryIIDSet, contrastIIDSet);
            --> { "Milk/Whole Milk/CookState:NONE/CutState:NONE/IngredientType:DAIRY/IngredientUnit:MILLILITER" }

        SetDifference.getNoMatchInPrimaryList(primaryIIDSet, contrastIIDSet);
            --> { "Garlic/Roasted Garlic Clove/CookState:ROASTED/CutState:WHOLE/IngredientType:VEGGIE/IngredientUnit:UNIT",
                  "Lemon/Lemon Wedge/CookState:RAW/CutState:SLICED/IngredientType:FRUIT/IngredientUnit:UNIT" }
     */

}