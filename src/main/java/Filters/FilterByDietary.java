package Filters;

import Interfaces.FilterInterface;

public enum FilterByDietary implements FilterInterface {
    DAIRY_FREE("Dairy-Free"),
    EGG_FREE("Egg-Free"),
    GLUTEN_FREE("Gluten-Free"),
    GRAIN_FREE("Grain-Free"),
    NUT_FREE("Nut-Free"),
    PALEO("Paleo"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    WHOLE30("Whole30");

    private String filterID;

    private FilterByDietary(String filterID) {
        this.filterID = filterID;
    }

    public String getFilterID() {
        return filterID;
    }
}
