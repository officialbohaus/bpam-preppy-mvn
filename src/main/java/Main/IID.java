package Main;

import Interfaces.TagDataInterface;
import Tags.*;
import Tags.Lists.LockedTagList;

public class IID {

        public static final Class[] TAG_ORDER = { IngredientType.class, RootName.class, RootDescriptor.class, CookState.class, CutState.class };
        public static int getTagPosition(Class<? extends TagDataInterface> tagClass) {
        for (int i = 0; i < TAG_ORDER.length; i++) {
            if (TAG_ORDER[i].equals(tagClass)) {
                return i;
            }
        }
            return -1;
        }

    private final LockedTagList TAGS;
    private final int IID_DEPTH;
    private final boolean linear;

    public IID(IngredientType type, RootName rootName, Tag... tags) {
        Tag[] tempArr = new Tag[tags.length + 2];
        tempArr[0] = new Tag(type);
        tempArr[1] = new Tag(rootName);
        for (int i = 0; i < tags.length; i++) {
            tempArr[i+2] = tags[i];
        }
        TAGS = new LockedTagList(tempArr);
        linear = checkLinear();
        if (!linear) { throw new IllegalStateException("Provided tags would create a non-linear IID!"); }
        IID_DEPTH = checkDepth();
    }

    public boolean isLinear() { return linear; }

    private boolean checkLinear() {
        // ensure there are no holes (no values past first empty value in tag order)
        boolean hasMissingTag = false;
        for (int i = 0; i < TAG_ORDER.length; i++) {
            if (!hasMissingTag) {
                hasMissingTag = !TAGS.containsTagType(TAG_ORDER[i]);
            } else {
                if (TAGS.containsTagType(TAG_ORDER[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getDepth() { return IID_DEPTH; }

    private int checkDepth() {
        for (int i = 0; i < TAG_ORDER.length; i++) {
            if (!TAGS.containsTagType(TAG_ORDER[i])) {
                return i;
            }
        }
        return TAG_ORDER.length;
    }





}
