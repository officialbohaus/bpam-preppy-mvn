package Tags.Lists;

import Main.Tag;

import java.util.Collection;

public class LockedTagList extends AbstractTagArrayList {



    public LockedTagList(Tag... tags) {
        super();
        for (Tag tag : tags) {
            if (contains(tag)) { throw new RuntimeException("Duplicate tag in LockedTagList: " + tag); }
                TAGS.add(tag);
        }
    }






    // Unsupported Methods
        // <editor-fold desc="methods">
    private UnsupportedOperationException readOnly() {
        return new UnsupportedOperationException("This list is read-only");
    }

    @Override
    public boolean add(Tag tag) {
        throw readOnly();
    }
    @Override
    public boolean remove(Object o) {
        throw readOnly();
    }

    @Override
    public boolean addAll(Collection<? extends Tag> c) {
        throw readOnly();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw readOnly();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw readOnly();
    }

    @Override
    public void clear() {
        throw readOnly();
    }

    // </editor-fold>
}
