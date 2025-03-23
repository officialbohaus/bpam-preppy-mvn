package LegacyFiles;

import Exceptions.InvalidIIDException;
import Exceptions.InvalidRequestException;
import Tags.CookState;
import Tags.IngredientType;
import Tags.CutState;
import Tags.IngredientUnit;

public class IIDParser {
    static final String IID_ORDER = "#NAME:[NAME] - BASE:[BASE] - DESCRIPTOR:[DESCRIPTOR] - INGREDIENT_TYPE:[INGREDIENT_TYPE] - COOK_STATE:[COOK_STATE] - CUT_STATE:[CUT_STATE] - UNIT:[UNIT]#";
    private static String[] IIDTags;
    private static String newIID;
    private static final int ACTUAL_TAG_INDEX = 1;
    private static final int NAME_INDEX = 0;
    private static final int BASE_INDEX = 1;
    private static final int DESCRIPTOR_INDEX = 2;
    private static final int TYPE_INDEX = 3;
    private static final int COOK_INDEX = 4;
    private static final int CUT_INDEX = 5;
    private static final int UNIT_INDEX = 6;
    private static final int MAX_INDEX = 6;
    private static final int IID_LENGTH = 7;

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getter for name

    public static String getName(String IID) {
        Guards.checkIID(IID);

        return getTags(IID, NAME_INDEX);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getter for base

    public static String getBase(String IID) {
        Guards.checkIID(IID);

        return getTags(IID, BASE_INDEX);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getter for descriptor

    public static String getDescriptor(String IID) {
        Guards.checkIID(IID);

        return getTags(IID, DESCRIPTOR_INDEX);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getters and a setter for IngredientType
    // The setter changes the IngredientType in an IID
    // Use getNewIID() to get the new IID

    public static String getType(String IID) {
        Guards.checkIID(IID);

        return getTags(IID, TYPE_INDEX);
    }

    public static IngredientType getTypeEnum(String IID) {
        Guards.checkIID(IID);

        String type = getTags(IID, TYPE_INDEX);

        return IngredientType.fromTypeID(type);
    }

    // needs a get tag to get the changed tag before putting the entire tag into the IID
    // WARNING: The method setType() should not exist
    // THIS IS JUST A TEST METHOD
    // TODO: DELETE METHOD OR MAKE PRIVATE
    public static void setType(String IID, IngredientType type) {
        String[] IIDTags = IIDParser.parseIID(IID);
        String[] typeTagSplit = IIDTags[TYPE_INDEX].split(":");
        typeTagSplit[ACTUAL_TAG_INDEX] = type.getTagString();
        String newTypeTag = String.join(":", typeTagSplit);
        IIDTags[TYPE_INDEX] = newTypeTag;
        newIID = getIID(IIDTags);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getters and a setter for CookState
    // The setter changes the CookState in an IID
    // Use getNewIID() to get the new IID

    public static String getCookState(String IID) {
        return getTags(IID, COOK_INDEX);
    }

    public static CookState getCookStateEnum(String IID) {
        Guards.checkIID(IID);

        String type = getTags(IID, COOK_INDEX);

        return CookState.fromCookID(type);
    }

    public static void setCookState(String IID, CookState cookState) {
        String IIDTags[] = IIDParser.parseIID(IID);
        String[] cookStateSplit = IIDTags[COOK_INDEX].split(":");
        cookStateSplit[ACTUAL_TAG_INDEX] = cookState.getCookID();
        String newCookState = String.join(":", cookStateSplit);
        IIDTags[COOK_INDEX] = newCookState;
        newIID = getIID(IIDTags);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getters and a setter for CutState
    // The setter changes the CutState in an IID
    // Use getNewIID() to get the new IID

    public static String getCutState(String IID) {
        return getTags(IID, CUT_INDEX);
    }

    public static CutState getCutStateEnum(String IID) {
        Guards.checkIID(IID);

        String type = getTags(IID, CUT_INDEX);

        return CutState.fromCutID(type);
    }

    public static void setCutState(String IID, CutState cutState) {
        String[] IIDTags = IIDParser.parseIID(IID);
        String[] cutStateSplit = IIDTags[CUT_INDEX].split(":");
        cutStateSplit[ACTUAL_TAG_INDEX] = cutState.getCutID();
        String newCutState = String.join(":", cutStateSplit);
        IIDTags[CUT_INDEX] = newCutState;
        newIID = getIID(IIDTags);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Getters and a setter for IngredientUnit
    // The setter changes the IngredientUnit in an IID
    // Use getNewIID() to get the new IID

    public static String getUnit(String IID) {
        return getTags(IID, UNIT_INDEX);
    }

    public static IngredientUnit getUnitEnum(String IID) {
        Guards.checkIID(IID);

        String type = getTags(IID, UNIT_INDEX);

        return IngredientUnit.fromUnitID(type);
    }

    // We may never need this
    public static void setUnit(String IID, IngredientUnit unit) {
        String[] IIDTags = (IIDParser.parseIID(IID));
        String[] unitSplit = IIDTags[UNIT_INDEX].split(":");
        unitSplit[ACTUAL_TAG_INDEX] = unit.getunitID();
        String newUnit = String.join(":", unitSplit);
        IIDTags[UNIT_INDEX] = newUnit;
        newIID = getIID(IIDTags);
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // getter for IID
    // Passes in a String array of IIDComponents
    // Returns a String of the IID

    public static String getIID(String[] IIDComponents) {
        String newIID;
        newIID = IIDComponents[0] + " - ";
        for (int i = 1; i < IIDComponents.length; i++) {
            newIID += (i != IIDComponents.length - 1 ? (IIDComponents[i] + " - ") : IIDComponents[i]);
        }
        return newIID;
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // getter for newIID
    // returns newIID that has been modified by a setter

    public static String getNewIID() {
        return newIID;
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // Splits and IID String into a String array that holds a tag in each index
    // Returns a String array of the tags

    public static String[] parseIID(String IID) {
        Guards.checkIID(IID);
        IIDTags = IID.split(" - ");
        return IIDTags;
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    // a private getter that returns a String of any of the tags
    //

    private static String getTags(String IID, int position) {
        if (parseIID(IID).length != IID_LENGTH) {throw new InvalidIIDException();}
        if (position < 0 || position > MAX_INDEX) {throw new IndexOutOfBoundsException();}

        switch (position) {
            case NAME_INDEX:
                String[] name = parseIID(IID)[NAME_INDEX].split(":");
                return name[ACTUAL_TAG_INDEX];
            case BASE_INDEX:
                String[] base = parseIID(IID)[BASE_INDEX].split(":");
                return base[ACTUAL_TAG_INDEX];
            case DESCRIPTOR_INDEX:
                String[] descriptor = parseIID(IID)[DESCRIPTOR_INDEX].split(":");
                return descriptor[ACTUAL_TAG_INDEX];
            case TYPE_INDEX:
                String[] type = parseIID(IID)[TYPE_INDEX].split(":");
                return type[ACTUAL_TAG_INDEX];
            case COOK_INDEX:
                String[] cookState = parseIID(IID)[COOK_INDEX].split(":");
                return cookState[ACTUAL_TAG_INDEX];
            case CUT_INDEX:
                String[] cutState = parseIID(IID)[CUT_INDEX].split(":");
                return cutState[ACTUAL_TAG_INDEX];
            case UNIT_INDEX:
                String[] unit = parseIID(IID)[UNIT_INDEX].split(":");
                return unit[ACTUAL_TAG_INDEX];
            default:
                throw new InvalidRequestException();
        }

        // TODO: has no error checking
    }
}
