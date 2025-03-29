package Main;

import Interfaces.IngredientSetInterface;

public class Recipe {

        private final IngredientSetInterface recipeIngredients;
        private final String name;
        private final String description;

        public Recipe(IngredientSet recipeIngredients, String name, String description) {
            this.name = name;
            this.description = description;
            this.recipeIngredients = recipeIngredients;
        }






}
