package Main;

import Interfaces.TagDataInterface;

public class Tag implements TagDataInterface {

    private final TagDataInterface tag;
    private final String tagString;
    private final Class<? extends TagDataInterface> tagClass;

    public Tag(TagDataInterface tag) {
        this.tag = tag;
        this.tagClass = tag.getClass();
        this.tagString = tag.getTagString();
    }

    public TagDataInterface getTag() {
        return tag;
    }

    @Override
    public String getTagString() {
        return tagString;
    }

    @Override
    public Class<? extends TagDataInterface> getTagClass() {
        return tagClass;
    }

//    @Override
    public boolean isSameTag(TagDataInterface tag) {
        if (isSameTagType(tag)) {
            return tag.getTagString().equals(this.tagString);
        }
        return false;
    }
    // will replace above method eventually
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) {
            Tag t = (Tag) o;
            return t.tagString.equals(this.tagString);
        }
        return false;
    }

//    @Override
    public <T extends TagDataInterface> boolean isSameTagType(Class<T> tagClass) {
        return tagClass.equals(this.tagClass);
    }

//    @Override
    public boolean isSameTagType(TagDataInterface tag) {
        return isSameTagType(tag.getClass());
    }

    @Override
    public String toString() {
        return "[" + tagClass + " / " + tagString + " ]";
    }

}
