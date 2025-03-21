package Interfaces;

import java.util.ArrayList;

public interface IIDInterface {

    public String getIIDString();

    public boolean isIID();

    public ArrayList<IIDTag> getTags();

    public boolean hasTag(IIDTag tag);

    public IIDTag getTag(IIDTag tag);

    // eventually will "lock down", not able to change IIDs in container. For now, we can

    public void addTag(IIDTag tag);

}
