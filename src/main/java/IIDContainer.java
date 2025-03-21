import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IIDContainer implements IIDContainerInterface {

    private List<IIDTag> tags;
    private String iidString;

    public IIDContainer() {
        this.tags = new ArrayList<IIDTag>();
    }

    public IIDContainer(IIDTag[] tags) {
        this(Arrays.asList(tags));
    }

    public IIDContainer(List<IIDTag> tags) {
        this.tags = new ArrayList<IIDTag>();
        for (IIDTag tag : tags) {
            tags.add(tag);
            //TODO error check
        };
    }

    @Override
    public String getIIDString() {
        return null;
    }

    @Override
    public boolean isIID(IIDContainer iidContainer) {
        return tags.containsAll(iidContainer.getTags());
    }

    @Override
    public ArrayList<IIDTag> getTags() {
        return new ArrayList<IIDTag>(tags);
    }

    @Override
    public boolean hasTag(IIDTag tag) {
        return tags.contains(tag);
    }

    @Override
    public IIDTag getTag(Class<IIDTag> tagEnum) {
        for (IIDTag tag : tags) {
            if (tag.isSameTagType(tagEnum)) {
                return tag;
            }
        }
        return null;
    }

    @Override
    public void addTag(IIDTag tag) {

    }
}
