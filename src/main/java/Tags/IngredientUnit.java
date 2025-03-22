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
        return unitID;
    }

    @Override
    public boolean isSameTag(IIDTag tag) {
        if (!(tag instanceof IngredientUnit)) { return false; }
        return tag.getTagString().equals(this.getTagString());
    }

    @Override
    public <T extends Enum<T>> boolean isSameTagType(Class<T> tagEnum) {
        return tagEnum == this.getClass();
    }

    @Override
    public boolean isSameTagType(IIDTag tag) {
        Class<? extends IIDTag> tagClass = tag.getClass();
        return this.getClass().equals(tagClass);
    }

}
