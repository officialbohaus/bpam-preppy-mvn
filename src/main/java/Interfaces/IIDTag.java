package Interfaces;

public interface IIDTag {

    public String getTagString();

    public boolean isSameTag(IIDTag tag);

    public <T extends Enum<T>> boolean isSameTagType(Class<T> tagEnum);

    public boolean isSameTagType(IIDTag tag);

}
