package Interfaces;

/**
 * The interface implemented by any class that can be added to an <code>IIDContainer</code>
 */
public interface TagDataInterface {

    /**
     * @return the legacy IID format
     */
    public String getTagString();
    // returns String representation of enum

//    public boolean isSameTag(IIDTag tag);
    // usage: someTag.isSameTag(otherTag)
    // returns true is someTag is both the same tag type and tag value as otherTag
    // e.g. CookState.RAW.isSameTag(CookState.RAW) --> true
    //      CookState.RAW.isSameTag(CookState.BAKED) --> false

//    public <T extends IIDTag> boolean isSameTagType(Class<T> tagClass);
    // usage: someTag.isSameTagType(IIDTag.class)
    // true if someTag is a CookState tag
    // e.g. CookState.RAW.isSameTagType(CookState.class) --> true
    //      CookState.RAW.isSameTagType(CutState.class) --> false

//    public boolean isSameTagType(IIDTag tag);
    // usage: someTag.isSameTagType(otherTag)
    // true if someTag is same tag type as otherTag (e.g. both are CookState tags)
    // e.g. CookState.RAW.isSameTagType(CookState.BAKED) --> true
    //      CookState.RAW.isSameTagType(CutState.WHOLE) --> false

    public Class<? extends TagDataInterface> getTagClass();
}
