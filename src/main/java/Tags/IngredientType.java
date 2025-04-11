package Tags;

import Interfaces.TagDataInterface;

public enum IngredientType implements TagDataInterface {
    SPICE("SPICE"),
    GRAIN("GRAIN"),
    FRUIT("FRUIT"),
    VEGGIE("VEGGIE"),
    PROTEIN("PROTEIN"),
    DAIRY("DAIRY"),
    BAKING("BAKING"),
    SAUCE("SAUCE"),
    FAT("FAT"),
    NUT("NUT"),
    MISC("MISC");
    private String typeID;
    private IngredientType(String typeID) { this.typeID = typeID; }

    public static String[] getTypeString() {
        String[] types = new String[values().length];

        for (int i = 0; i < values().length; i++) {
            types[i] = values()[i].toString();
        }
        return types;
    }

    public static IngredientType fromTypeID(String typeID) {
        for (IngredientType ingredientType : values()) {
            if (ingredientType.typeID.equalsIgnoreCase(typeID)) {
                return ingredientType;
            }
        }
        throw new IllegalArgumentException("No enum constant with abbreviation:" + typeID);
    }

    @Override
    public String getTagString() {
        return typeID;
    }

    @Override
    public Class<? extends TagDataInterface> getTagClass() {
        return IngredientType.class;
    }

//    @Override
//    public boolean isSameTag(IIDTag tag) {
//        if (!(tag instanceof IngredientType)) { return false; }
//        return tag.getTagString().equals(this.getTagString());
//    }
//
////    @Override
//    public <T extends IIDTag> boolean isSameTagType(Class<T> tagClass) {
//        return tagClass.equals(IngredientType.class);
//    }
//
//    @Override
//    public boolean isSameTagType(IIDTag tag) {
//        Class<? extends IIDTag> tagClass = tag.getClass();
//        return this.getClass().equals(tagClass);
//    }


}
