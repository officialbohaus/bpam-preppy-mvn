package Tags;

import Interfaces.TagDataInterface;

public enum IngredientUnit implements TagDataInterface {
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
    public Class<? extends TagDataInterface> getTagClass() {
        return IngredientUnit.class;
    }


//    @Override
//    public boolean isSameTag(IIDTag tag) {
//        if (!(tag instanceof IngredientUnit)) { return false; }
//        return tag.getTagString().equals(this.getTagString());
//    }
//
//    @Override
//    public <T extends IIDTag> boolean isSameTagType(Class<T> tagClass) {
//        return tagClass.equals(IngredientUnit.class);
//    }
//
//    @Override
//    public boolean isSameTagType(IIDTag tag) {
//        Class<? extends IIDTag> tagClass = tag.getClass();
//        return this.getClass().equals(tagClass);
//    }

}
