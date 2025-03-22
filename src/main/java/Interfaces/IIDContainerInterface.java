package Interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IIDContainerInterface {

    public String getIIDString();

    public boolean isIID(IIDContainerInterface iidContainer);

    public ArrayList<IIDTag> getTags();

    public boolean hasTag(IIDTag tag);

    public <T extends IIDTag> IIDTag getTag(Class<T> tag);

    // eventually will "lock down", not able to change IIDs in container. For now, we can

    public String getNameTag();

    public String getDescriptorTag();

    public String getNickname();

    public void addTag(IIDTag tag);

    public void addTags(List<IIDTag> tags);

}
