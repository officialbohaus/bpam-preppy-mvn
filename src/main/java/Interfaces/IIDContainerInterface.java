package Interfaces;

import java.util.ArrayList;


/**
 * enforces getter commands to read an IID within some object that holds a single IID
 */
public interface IIDContainerInterface {

    // not yet implemented
    public String getIIDString();

    // gets the a list of all tags
    public ArrayList<TagDataInterface> getTags();

    // gets the tag of the class extending IIDTag provided
    public <T extends TagDataInterface> TagDataInterface getTag(Class<T> tag);

    public String getNameTag();

    public String getDescriptorTag();

    public String getNickname();

    // gets current count of modifications, for checking if any modifications have happened at a later state.
    public int getModCount();

    // checks IID for provided tag
    public boolean hasTag(TagDataInterface tag);

    // checks if IID matches the one provided
    public boolean isIID(IIDContainerInterface iidContainer);

}
