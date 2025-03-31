package Main;

import Interfaces.IIDSetInterface;

public class Recipe {

        private final IIDSetInterface recipeIngredients;
        private final String name;
        private final String description;

        public Recipe(IIDSet recipeIngredients, String name, String description) {
            this.name = name;
            this.description = description;
            this.recipeIngredients = recipeIngredients;
        }






}
