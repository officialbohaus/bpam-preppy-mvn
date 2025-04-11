package Tags;

import Interfaces.TagDataInterface;

public abstract class AbstractStringTag implements TagDataInterface {

    private final String tagValue;

    public AbstractStringTag(String tagValue) {
        this.tagValue = tagValue;
    }

    @Override
    public String getTagString() { return tagValue; }

//    @Override
//    public <T extends IIDTag> boolean isSameTagType(Class<T> tagClass) {
//        return tagClass.equals(this.getClass());
//    }

//    @Override
//    public boolean isSameTagType(IIDTag tag) {
//        return tag.getClass().equals(this.getClass());
//    }
}
