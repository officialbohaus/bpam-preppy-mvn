package Tags;

import Interfaces.IIDTag;

public enum CutState implements IIDTag {
    WHOLE("WHOLE"),
    SLICED("SLICE"),
    CHOPPED("CHOP"),
    DICED("DICE"),
    MINCED("MINCE"),
    SHREDDED("SHRED"),
    GROUND("GROUND"),
    JULIENNED("JULIENNE"),
    NONE("N/A");
    // CutState IDEA: BLENDED

    private String cutID;

    private CutState(String cutID) {
        this.cutID = cutID;
    }

    public String getCutID() {
        return cutID;
    }

    public static String[] getCutIDString() {
        String[] cutIDString = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            cutIDString[i] = values()[i].toString();
        }
        return cutIDString;
    }

    public static CutState fromCutID(String cutID) {
        for (CutState cutState : values()) {
            if (cutState.cutID.equalsIgnoreCase(cutID)) {
                return cutState;
            }
        }
        throw new IllegalArgumentException("No enum constant with abbreviation: " + cutID);
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
