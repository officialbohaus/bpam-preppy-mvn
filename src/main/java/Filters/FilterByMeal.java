package Filters;

import Interfaces.FilterInterface;

public enum FilterByMeal implements FilterInterface {
    APPETIZER("APPETIZER"),
    BREAKFAST("BREAKFAST"),
    CONDIMENTS("CONDIMENT"),
    DESSERT("DESSERT"),
    DRINK_SMOOTHIE("DRINK/SMOOTHIE"),
    LUNCH_DINNER("LUNCH/DINNER"),
    SALAD_SIDE("SALAD/SIDES"),
    SNACK("SNACK"),
    SOUP_CHILI("SOUP/CHILI");

    private String filterID;

    private FilterByMeal(String filterID) {
        this.filterID = filterID;
    }

    public String getFilterID() {
        return filterID;
    }
}
