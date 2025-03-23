package LegacyFiles;

import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

public class IIDGenerator {
    /*
     * The IID is a system of nomenclature for different food items
     * Similar to how molecules are named in chemistry in relation to some inherent property of the molecule,
     *  e.g. Carbon Monoxide (CO) having 1 Oxygen atom, Carbon Dioxide (CO2) having 2 Oxygen atoms
     * Similar is the IID. Just how when we say "Carbon Dioxide" we're hardly refering to some specific atom of
     * CO2 but rather the concept of the molecule as a whole, so is how we use the IID.
     * Therefore, when we refer to a "baked sliced carrot", we're not referring to a specific, real object represented
     * in our code space-- rather the concept of a baked sliced carrot. When we wish to quantify the "baked sliced carrot",
     * we use a PantryNode to combine the IID with a number to represent how many items we have of the objects referred
     * to by the IID.
     */

    // the IID_TEMPLATE is used to ensure a standardized arrangement of IID components (i.e. name, cookstate, etc)
    // this ensures our parser works correctly. Do not deviate from the form below!
    // TODO: Error checking not yet implemented for invalid IID forms
    private static final String IID_TEMPLATE = "#NAME:...-BASE:...-DESCRIPTOR:...-INGREDIENTTYPE:...-COOKSTATE:...-CUTSTATE:...-INGREDIENTUNIT:...#";
    private static final String NOT_APPLICABLE_CHAR = "N/A";

    public static String genIID(IngredientType type, String name) {
        System.out.println("Generating IID without CookState, CutState, or IngredientUnit specified! Are you sure this is what you want?");
        return genIID(name, type, CookState.NONE, CutState.NONE, IngredientUnit.UNIT);
    }

    public static String genIID(String name, IngredientType type, IngredientUnit unit) {
        return genIID(name, type, CookState.NONE, CutState.NONE, unit);
    }

    // public static String genIID(String name, IngredientType type, CookState cookState, CutState cutState, IngredientUnit unit) {
    //     return
    //     "#" +
    //     "NAME:"+ name + "-" +
    //     "INGREDIENTTYPE:" + type.getTypeID() + "-" +
    //     "COOKSTATE:" + cookState.getCookID() + "-" +
    //     "CUTSTATE:" + cutState.getCutID() + "-" +
    //     "UNIT:" + unit.getunitID() +
    //     "#";
    // }

    // This generates an IID string matching the template above.
    // TODO: create an actual template with String class methods (such as format) instead of relying on valid order of input.
    // Create cases for acceptable bases and descriptors
    public static String genIID(String name, IngredientType type, CookState cookState, CutState cutState, IngredientUnit unit) {
        return
                "#" +
                        "NAME:"+ name + " - " +
                        "BASE:" + "[BASE]" + " - " +
                        "DESCRIPTOR:" + "[DESCRIPTOR]" + " - " +
                        "INGREDIENT_TYPE:" + type.getTagString() + " - " +
                        "COOK_STATE:" + cookState.getCookID() + " - " +
                        "CUT_STATE:" + cutState.getCutID() + " - " +
                        "UNIT:" + unit.getunitID() +
                        "#";
    }
}
