import Interfaces.IIDInterface;
import Interfaces.IIDTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class IID implements IIDInterface {

    private List<IIDTag> tags;
    private String iidString;

    public IID() {

    }

    public IID(IIDTag[] tags) {
        this(Arrays.asList(tags));
    }

    public IID(List<IIDTag> tags) {
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
    public boolean isIID() {
        return false;
    }

    @Override
    public ArrayList<IIDTag> getTags() {
        return null;
    }

    @Override
    public boolean hasTag(IIDTag tag) {
        return false;
    }

    @Override
    public IIDTag getTag(IIDTag tag) {
        return null;
    }

    @Override
    public void addTag(IIDTag tag) {

    }
}
