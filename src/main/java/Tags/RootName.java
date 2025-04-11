package Tags;

import Interfaces.TagDataInterface;

// a simple string wrapper
public class RootName extends AbstractStringTag {

    public RootName(String tagValue) {
        super(tagValue);
    }

    @Override
    public Class<? extends TagDataInterface> getTagClass() {
        return RootName.class;
    }
}

//    private final String tagValue;
//
//    public RootName(String rootName) { this.tagValue = rootName; }
//
//    @Override
//    public String getTagString() {
//        return tagValue;
//    }
//
//    @Override
//    public boolean isSameTag(IIDTag tag) {
//        if (isSameTagType(tag)) {
//            return tagValue.equals(tag.getTagString());
//        }
//        return false;
//    }
//
////    @Override
//    public <T extends IIDTag> boolean isSameTagType(Class<T> tagClass) {
//        return false; //TODO
//    }
//
//    @Override
//    public boolean isSameTagType(IIDTag tag) {
//        return tag instanceof RootName;
//    }
