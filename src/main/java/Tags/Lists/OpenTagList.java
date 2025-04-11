package Tags.Lists;

import Main.Tag;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class OpenTagList extends AbstractTagArrayList {


    // iff multi-tag
//    @Override
//    public boolean add(Tag tag) {
//        Tag existingTag = getTagOfType(tag);
//        if (existingTag != null) {
//            System.out.println("Overwriting existing tag: " + existingTag + " for new tag: " + tag);
//            TAGS.set(TAGS.indexOf(existingTag), tag);
//        } else {
//            int index = AbstractTagArrayList.getTagClassIndex(tag.getTag().getClass());
//            // index is < 0 iff the provided tag class does not have a declared order in above constant
//            if (index < 0) {
//                TAGS.add(tag);
//            } else {
//                TAGS.set(index, tag);
//            }
//        }
//        return true;
//    }

    public OpenTagList(Tag... tags) {
        super();
        for (Tag tag : tags) {
            add(tag);
        }
    }

    @Override
    public boolean add(Tag tag) {
        if (!contains(tag)) {
            TAGS.add(tag);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
//        if (!contains(o)) { throw new NoSuchElementException(); }
        return TAGS.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends Tag> c) {
        for (Tag tag : c) {
            add(tag);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return TAGS.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return TAGS.retainAll(c);
    }

    @Override
    public void clear() {
        TAGS.clear();
    }

}
