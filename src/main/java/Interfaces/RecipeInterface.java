package Interfaces;

public interface RecipeInterface {
    public void setIngredientQuantity(String name, int quantity);
    public int getIngredientQuantity(String name);
    public void setIngredient(String name);
    public String getIngredient();
    public void steps();

}
