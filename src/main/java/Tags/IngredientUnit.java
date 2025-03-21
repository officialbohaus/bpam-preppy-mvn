package Tags;

import Interfaces.IIDTag;

public enum IngredientUnit implements IIDTag {
    GRAM("g"),
    MILLILITER("mL"),
    UNIT("Ut");

    private String unitID;

    private IngredientUnit(String unitID) {
        this.unitID = unitID;
    }

    public String getunitID() {
        return unitID;
    }

    public static IngredientUnit fromUnitID(String unitID) {
        for (IngredientUnit UnitID : values()) {
            if (UnitID.unitID.equalsIgnoreCase(unitID)) {
                return UnitID;
            }
        }
        throw new IllegalArgumentException("No enum constant with abbreviation: " + unitID);
    }

    @Override
    public String getTagString() {
        return "";
    }

    @Override
    public boolean isSameTag(IIDTag tag) {
        return false;
    }

    @Override
    public boolean isSameTagType(IIDTag tag) {
        return false;
    }
}
