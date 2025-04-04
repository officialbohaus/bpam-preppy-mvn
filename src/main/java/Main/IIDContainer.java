package Main;

import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class   IIDContainer implements IIDContainerInterface, Serializable {
    /*
    The IIDContainer Class represents an object that holds all the components to an IID.

    In order for the IIDContainer to have a valid IID, it must have one of each tag listed in the "criticalTags" variable below.
    Only one tag can be held for each tag type.
    The nameTag and descriptorTag are also important tags, but they are defined as Strings instead of enums.
    Because of this, they are not kept in the "tags" list-- but they should still be considered tags in any methods interacting with tags.

    The IIDContainer toggles between locked and unlocked states.
        You may only "get" values when the container is locked
        You may only "set" values when the container is unlocked
        An invalid IID will not be allowed to lock
        If an IIDContainer is constructed with an invalid IID the default state will be unlocked.
            The container will automatically lock the first time a valid IID is contained
        Similarly, if an IIDContainer is constructed with a valid IID, the default state will be locked.
     */

    // critialTags are the classes, besides nameTag and descriptorTag, that must be included to be a valid IID
    public static final Class<? extends IIDTag>[] criticalTags = new Class[] {IngredientType.class, IngredientUnit.class, CutState.class, CookState.class};

    private final List<IIDTag> tags;
    private static int containerCount = 0;
    private final int containerID;
    private int modCount;
    private String nameTag;
    private String descriptorTag;

    private String nicknameTag; // not a comparison metric, entirely user chosen

    private String iidString;
    private boolean validIID;
    private boolean locked;
    private boolean hasNeverLocked;

    // Constructors ==============================================================

    public IIDContainer() {
        this(new ArrayList<IIDTag>());
    }

    public IIDContainer(IIDTag[] tags) {
        this(Arrays.asList(tags));
    }

    public IIDContainer(List<IIDTag> tags) {
        this("", "", tags);

    }

    public IIDContainer(String nameTag, String descriptorTag, IIDTag[] tags) {
        this(nameTag, descriptorTag, Arrays.asList(tags));
    }

    public IIDContainer(String nameTag, String descriptorTag, List<IIDTag> tags) {
        this.nameTag = nameTag;
        this.descriptorTag = descriptorTag;
        this.tags = new ArrayList<IIDTag>();
        this.addTags(tags);
        this.validIID = verifyValidIID();
        this.containerID = ++containerCount;
        this.locked = validIID;
        this.hasNeverLocked = !locked;
        this.modCount = 0;
    }

    // Getters ====================================================================
    @Override
    public String getIIDString() {
        guardGet();
        return null;
    }

    @Override
    public ArrayList<IIDTag> getTags() {
        guardGet();
        return new ArrayList<IIDTag>(tags);
    }

    @Override
    public <T extends IIDTag> IIDTag getTag(Class<T> tagEnum) {
        guardGet();
        for (IIDTag tag : tags) {
            if (tag.getClass().equals(tagEnum))
                return tag;
        }
        throw new IllegalArgumentException("No tag of type " + tagEnum + " found");
    }

    @Override
    public String getNameTag() {
        guardGet();
        return nameTag;
    }

    @Override
    public String getDescriptorTag() {
        guardGet();
        return descriptorTag;
    }

    @Override
    public String getNickname() {
        guardGet();
        return nicknameTag;
    }

    public int getContainerID() {
        guardGet();
        return containerID;
    }

    public int getModCount() {
        guardGet();
        return modCount;
    }

    // Checkers =======================================================
    @Override
    public boolean isIID(IIDContainerInterface iidContainer) {
        if (!(this.nameTag.equals(iidContainer.getNameTag()) && this.descriptorTag.equals(iidContainer.getDescriptorTag()))) { return false; }
        return iidContainer.getTags().containsAll(tags);
    }

    @Override
    public boolean hasTag(IIDTag tag) {
        return tags.contains(tag);
    }

    public boolean isValidIID() { return validIID; }

    // Setters ========================================================

    public void addTag(IIDTag tag) {
        guardSet();
        Iterator<IIDTag> tagsIter = tags.iterator();
        boolean foundTag = false;
        while (tagsIter.hasNext() && !foundTag) {
            IIDTag curr = tagsIter.next();
            if ( curr != null && curr.isSameTagType(tag)) {
                System.out.println("Overwriting tag: " + curr + " for new value: " + tag);
                tagsIter.remove();
                foundTag = true;
            }
        }
        modCount++;
        tags.add(tag);
        this.validIID = verifyValidIID();
    }

    public void addNameTag(String nameTag) {
        guardSet();
        if (!(this.nameTag == null || this.nameTag.isEmpty())) {
            System.out.println("Overwriting nameTag: " + this.nameTag + " for new value: " + nameTag);
        }
        modCount++;
        this.nameTag = nameTag;
        this.validIID = verifyValidIID();
    }

    public void addDescriptorTag(String descriptorTag) {
        guardSet();
        if (!(this.descriptorTag == null || this.descriptorTag.isEmpty())) {
            System.out.println("Overwriting descriptorTag: " + this.descriptorTag + " for new value: " + descriptorTag);
        }
        modCount++;
        this.descriptorTag = descriptorTag;
        this.validIID = verifyValidIID();
    }

    public void addNicknameTag(String nicknameTag) {
        guardSet();
        if (!(this.nicknameTag == null || this.nicknameTag.isEmpty())) {
            System.out.println("Overwriting nicknameTag: " + this.nicknameTag + " for new value: " + nicknameTag);
        }
        modCount++;
        this.nicknameTag = nicknameTag;
    }


    public void addTags(List<IIDTag> tags) {
        guardSet();
        for (IIDTag tag : tags) {
            addTag(tag);
        }
    }

    public boolean isLocked() { return locked; }

    public void lock() throws IllegalStateException {
        if (!validIID) { throw new IllegalStateException("Cannot lock IIDContainer because IID is invalid"); }
        if (locked) { throw new IllegalStateException("Cannot lock a locked IIDContainer!"); }
        locked = true;
    }

    public void unlock() throws IllegalStateException {
        if (!locked) { throw new IllegalStateException("Cannot unlock an unlocked IIDContainer!"); }
        locked = false;
    }

    // Helpers =========================================================

    private boolean verifyValidIID() {
        // this is where IID standardization happens!
        if (nameTag == null || descriptorTag == null || nameTag.isEmpty() || descriptorTag.isEmpty()) { return false; }
        for (Class classType : criticalTags) {
            boolean foundClass = false;
            Iterator<IIDTag> tagIter = tags.iterator();
            while (tagIter.hasNext() && !foundClass) {
                Class<? extends IIDTag> currTagClass = tagIter.next().getClass();
                if (currTagClass.equals(classType)) { foundClass = true; }
            }
            if (!foundClass) { return false; }
        }
        if (hasNeverLocked) {
            System.out.println("Valid IID detected, automatically locking IIDContainer #" + containerID);
            locked = true;
            hasNeverLocked = false;
        }
        return true;
    }

    private void guardGet() {
        if (!locked) { throw new IllegalStateException("Cannot get value while IIDContainer is unlocked!"); }
    }

    private void guardSet() {
        if (locked) { throw new IllegalStateException("Cannot set value while IIDContainer is locked!"); }
    }

    @Override
    public String toString() {
        String msg = " IIDContainer #" + containerID + "\n";
        msg += " - NameTag: " + nameTag + "\n";
        msg += " - DescriptorTag: " + descriptorTag + "\n";
        msg += " - NicknameTag: " + nicknameTag + "\n";
        for (IIDTag tag : tags) {
            msg += " - " + tag.getClass().getName() + ": " + tag.getTagString() + "\n";
        }
        msg += isValidIID() ? " VALID" : " INVALID";
        return msg;
    }
}
