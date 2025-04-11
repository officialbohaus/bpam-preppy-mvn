package Tags.Lists;

import Interfaces.TagDataInterface;
import Main.Tag;

import java.util.*;

public abstract class AbstractTagArrayList implements List<Tag> {

    // Static code





    // Instance code

    protected final ArrayList<Tag> TAGS;

    public AbstractTagArrayList() {
        TAGS = new ArrayList<>();
    }

    // Custom Methods
    public boolean containsTagType(TagDataInterface tag) {
        return containsTagType(tag.getClass());
    }

    public boolean containsTagType(Class<? extends TagDataInterface> tagClass) {
        for (Tag t : TAGS) {
            if (t.isSameTagType(tagClass)) {
                return true;
            }
        }
        return false;
    }

    public Tag getTagOfType(TagDataInterface tag) {
        return getTagOfType(tag.getClass());
    }

    public Tag getTagOfType(Class<? extends TagDataInterface> tagClass) {
        for (Tag t : TAGS) {
            if (t.isSameTagType(tagClass)) {
                return t;
            }
        }
        return null;
    }

    // List Methods

    @Override
    public int size() {
        return TAGS.size();
    }

    @Override
    public boolean isEmpty() {
        return TAGS.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        for (Tag t : TAGS) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }



    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return TAGS.toArray(a);
    }





//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends Tag> c) {
//        return false;
//    }



//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException();
//    }



    // TODO:
    @Override
    public ListIterator<Tag> listIterator() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ListIterator<Tag> listIterator(int index) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterator<Tag> iterator() {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    // Unsupported Methods
    // <editor-fold desc="methods">

    private UnsupportedOperationException indexCallException() {
        return new UnsupportedOperationException("Indexed calls are not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends Tag> c) {
        throw indexCallException();
    }

    @Override
    public Tag set(int index, Tag element) {
        throw indexCallException();
    }

    @Override
    public void add(int index, Tag element) {
        throw indexCallException();
    }

    @Override
    public Tag remove(int index) {
        throw indexCallException();
    }

    @Override
    public int indexOf(Object o) {
        throw indexCallException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw indexCallException();
    }

    @Override
    public List<Tag> subList(int fromIndex, int toIndex) {
        throw indexCallException();
    }

    @Override
    public Tag get(int index) {
        throw indexCallException();
    }

    // </editor-fold>


}
