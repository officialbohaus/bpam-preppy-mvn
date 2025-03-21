package Interfaces;

public interface IngredientInterface {
    public String getID();

    public String getName();

    public IngredientType getType();

    // should compare itself to passed in ingredient by ID. Same ID then true, else false
    public boolean isEqual(Ingredient ingredient);

}
