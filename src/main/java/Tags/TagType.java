package Tags;

import Interfaces.TagDataInterface;

public enum TagType {
    // central tags
    INGREDIENT_TYPE(IngredientType.class),
    ROOT_NAME(RootName.class),
//    ROOT_DESCRIPTOR(RootDescriptor.class),
    COOK_STATE(CookState.class),
    CUT_STATE(CutState.class),

    // less-central tags
    UNIT_TYPE(IngredientUnit.class);

    private Class<? extends TagDataInterface> tagClass;

    private TagType(Class<? extends TagDataInterface> tagClass) { this.tagClass = tagClass; }



}
