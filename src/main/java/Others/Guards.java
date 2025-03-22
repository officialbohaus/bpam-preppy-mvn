package Others;

import java.util.List;
import Exceptions.InvalidIIDException;
import java.util.Arrays;
import Tags.IngredientType;
import Tags.CookState;
import Tags.CutState;

public class Guards {
    private static final int ACTUAL_TAG_INDEX = 1;
    private static final int TYPE_INDEX = 3;
    private static final int COOK_INDEX = 4;
    private static final int CUT_INDEX = 5;
    private static final int UNIT_INDEX = 6;
    private static final int IID_LENGTH = 7; // TODO remember to change this if we change IID!
    private static List<String> list;

    public static void checkIID(String IID) {
        String[] IIDTags = IID.split(" - ");
        String[] ingredientType = IIDTags[TYPE_INDEX].split(":");
        String[] cookState = IIDTags[COOK_INDEX].split(":");
        String[] cutState = IIDTags[CUT_INDEX].split(":");
        if (IIDTags.length != IID_LENGTH) {
            throw new InvalidIIDException("INVALID IID LENGTH");
        }

        if (!Arrays.asList(IngredientType.getTypeString()).contains(ingredientType[ACTUAL_TAG_INDEX])) {
            throw new InvalidIIDException("INVALID INGREDIENT TYPE");
        }

        if (!Arrays.asList(CookState.getCookIDString()).contains(cookState[ACTUAL_TAG_INDEX])) {
            throw new InvalidIIDException("INVALID COOK STATE");
        }

        if (!Arrays.asList(CutState.getCutIDString()).contains(cutState[ACTUAL_TAG_INDEX])) {
            throw new InvalidIIDException("INVALID CUT STATE");
        }
    }

    public static void checkCutEnum(String IID) {
        list = Arrays.asList(CutState.getCutIDString());
        if (!list.contains(IIDParser.getCutState(IID))) { // Maybe use getComponent()
            throw new InvalidIIDException();
        }
    }

    public static void checkTypeEnum(String IID) {
        list = Arrays.asList(IngredientType.getTypeString());
        if (!list.contains(IIDParser.getType(IID))) { // Maybe use getComponent()?
            throw new InvalidIIDException();
        }

    }

    public static void checkCookEnum(String IID) {
        list = Arrays.asList(CookState.getCookIDString());
        if (!list.contains(IIDParser.getCookState(IID))) {
            throw new InvalidIIDException();
        }
    }
}
