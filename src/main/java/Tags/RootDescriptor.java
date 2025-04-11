package Tags;

import Interfaces.TagDataInterface;

public class RootDescriptor extends AbstractStringTag {

    public RootDescriptor(String tagValue) {
        super(tagValue);
    }

    @Override
    public Class<? extends TagDataInterface> getTagClass() {
        return RootDescriptor.class;
    }
}
