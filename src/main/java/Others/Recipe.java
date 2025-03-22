package Others;

import Exceptions.InvalidIIDException;
import Exceptions.InvalidQuantityException;
import Interfaces.IngredientSetInterface;
import Interfaces.RecipeInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipe implements RecipeInterface {
    /*
     * - The recipe class should take an ingredient set and pair each IID in the set
     * with a quantity value.
     * -This pairing can be done multiple ways, a matching array may be easy, the
     * PantryNode class already has this functionality too.
     * - The class should maintain some sort of "steps" for the recipe. Up to
     * programmer how to implement
     * - May be useful to have a RecipeStep class, that has ingredient in and
     * ingredient out, along with processing of said ingredient?
     * - The class should also have a recipe description and name.
     * - Appropriate getters, setter, and constructors
     *
     */

    private ArrayList<String> ingredients;
    private ArrayList<Integer> ingredientQuantity;
    private String name, description;
    private String[] filterTags;
    private final int SERVING_SIZE; // Utilize SERVING_SIZE as a multiplier for the ingredients in the recipe

    // TODO: Implement description and name

    //
    public Recipe(String name, String description, IngredientSetInterface ingredients, String[] filterTags, int servingSize) {
        this.name = name;
        this.description = description;
        this.filterTags = Arrays.copyOf(filterTags, filterTags.length);
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients.getIngredients());
        SERVING_SIZE = servingSize;

        ingredientQuantity = new ArrayList<>();
    }

    public void setIngredientAndQuantity(String IID, int quantity) {
        Guards.checkIID(IID);
        if (quantity < 0) { throw new InvalidQuantityException(quantity); }

        ingredients.add(IID);
        ingredientQuantity.set(ingredients.indexOf(IID), quantity);
    }

    public void setIngredientQuantity(String IID, int quantity) {
        for (int i = 0; i < ingredients.size(); i++) {
            // Use iterator
            // Pass in IID
            if (IID.equalsIgnoreCase(ingredients.get(i))){
                ingredientQuantity.set(i, quantity);
            }
        }
    }

    public int getIngredientQuantity(String IID) {
        // Use IID, not ingredientName
        Guards.checkIID(IID);
        if (!ingredients.contains(IID)) { throw new InvalidIIDException(IID); }
        return ingredientQuantity.get(ingredients.indexOf(IID));
    }

    @Override
    public void setIngredient(String name) {

    }

    @Override
    public String getIngredient() {
        return "";
    }

    @Override
    public void steps() {

    }
}