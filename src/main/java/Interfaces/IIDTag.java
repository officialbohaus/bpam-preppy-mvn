package Interfaces;

public interface IIDTag {

    public String getTagString();

    public boolean isSameTag(IIDTag tag);

    public boolean isSameTagType(Class<IIDTag> tagEnum);

}
