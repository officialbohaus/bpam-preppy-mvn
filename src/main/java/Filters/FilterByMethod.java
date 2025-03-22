package Filters;

import Interfaces.FilterInterface;

public enum FilterByMethod implements FilterInterface {
    AIR_FRYER("Air-fryer"),
    BLENDER("Blender"),
    GRILL("Grill"),
    INSTANT_POT("Instant Pot"),
    MEAL_PREP_RECIPES("Meal Prep Recipes"),
    NO_BAKE("No Bake"),
    OVEN("Oven"),
    SLOW_COOKER("Slow Cooker"),
    SMOKER("Smoker"),
    STOVETOP("Stovetop");

    private String filterID;

    private FilterByMethod(String filterID) {
        this.filterID = filterID;
    }

    public String getFilterID() {
        return filterID;
    }
}
