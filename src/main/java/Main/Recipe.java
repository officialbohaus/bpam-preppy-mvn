package Main;

import Exceptions.InvalidRequestException;
import Interfaces.FilterInterface;
import Interfaces.IIDContainerInterface;
import Interfaces.IIDSetInterface;
import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {

    private final String name;
    private final String description;
    private final IIDSet recipeIngredients;
    private ArrayList<String> steps;
    private ArrayList<FilterInterface> filters;

    public Recipe(String name, String description, IIDSet recipeIngredients) {
        this(name, description, recipeIngredients, new ArrayList<String>(), new FilterInterface[]{});
    }

    public Recipe(String name, String description, IIDSet recipeIngredients, FilterInterface... filters) {
        this(name, description, recipeIngredients, new ArrayList<String>(), filters);
    }

    public Recipe(String name, String description, IIDSet recipeIngredients, ArrayList<String> steps, FilterInterface... filters) {
        this.name = name;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
        this.steps = new ArrayList<>(steps);
        this.filters = new ArrayList<>(Arrays.asList(filters));
    }

    public void addIngredient(IIDContainerInterface ingredient) {
        recipeIngredients.addIngredient(ingredient);
    }

    public void addIngredient(IIDContainerInterface ingredient, int quantity) {
        recipeIngredients.addIngredient(ingredient, quantity);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public void removeStep(int index) {
        steps.remove(index);
    }

    public void addFilter(FilterInterface filter) {
        if (filters.contains(filter)) { return; }
        filters.add(filter);
    }

    public void removeFilter(FilterInterface filter) {
        if (!filters.contains(filter)) { throw new InvalidRequestException("Filter not found"); }
        filters.remove(filter);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    // This toString() seems too complicated to be efficient
    public String toString() {
        String result = "";
        result += "NAME: " + name + "\nDESCRIPTION: " + description;
        result += "\nFilters: ";
        for (FilterInterface filter : filters) {
            result += "\n" + filter.toString();
        }
        result += "\nINGREDIENTS: ";
        for (IIDContainerInterface ingredient : recipeIngredients.getIIDSet()) {
            result += "\n– " + ingredient.toString();
        }

        int count = 1;

        result += "\nSTEPS: ";
        for (String step : steps) {
            if (step != null) {
                result += "\n" + count + ". " + step;
                count++;
            }
        }

        return result;
    }
}
