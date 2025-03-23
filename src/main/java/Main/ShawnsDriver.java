package Main;

import Interfaces.IIDTag;
import LegacyFiles.IIDGenerator;
import LegacyFiles.Step;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class ShawnsDriver {
    public static void main(String[] args) {
        String chicken = IIDGenerator.genIID("Chicken", IngredientType.PROTEIN, CookState.RAW, CutState.WHOLE, IngredientUnit.GRAM);

        Step step = new Step("roast chicken", "chicken", new IIDTag[]{CookState.RAW, CutState.WHOLE}, CutState.DICED);
        String newChicken = step.doThis(chicken);
        System.out.println("New Chicken IID: " + newChicken);

        String[] numbers = {"one", "two", "three"};
        ArrayList<String> numbersArrayList = new ArrayList<>(Arrays.asList(numbers));
    }
}
