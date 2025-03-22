import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class   IIDContainer implements IIDContainerInterface {

    // critialTags are the classes, besides nameTag and descriptorTag, that must be included to be a valid IID
    public static final Class[] criticalTags = {IngredientType.class, IngredientUnit.class, CutState.class, CookState.class};

    private List<IIDTag> tags;
    private String nameTag;
    private String descriptorTag;

    private String nicknameTag; // not a comparison metric, entirely user chosen

    private String iidString;
    private boolean validIID;

    public IIDContainer() {
        this(new ArrayList<IIDTag>());
    }

    public IIDContainer(IIDTag[] tags) {
        this(Arrays.asList(tags));
    }

    public IIDContainer(List<IIDTag> tags) {
        this.tags = new ArrayList<IIDTag>();
        this.addTags(tags);
        this.validIID = verifyValidIID();

    }

    @Override
    public String getIIDString() {
        return null;
    }

    @Override
    public boolean isIID(IIDContainerInterface iidContainer) {
        if (!(this.nameTag.equals(iidContainer.getNameTag()) && this.descriptorTag.equals(iidContainer.getDescriptorTag()))) { return false; }
        return iidContainer.getTags().containsAll(tags);
    }

    @Override
    public ArrayList<IIDTag> getTags() {
        return new ArrayList<IIDTag>(tags);
    }

    @Override
    public boolean hasTag(IIDTag tag) {
        return tags.contains(tag);
    }

    @Override
    public <T extends IIDTag> IIDTag getTag(Class<T> tagEnum) {
        for (IIDTag tag : tags) {
            if (tag.getClass().equals(tagEnum))
                return tag;
        }
        throw new IllegalArgumentException("No tag of type " + tagEnum + " found");
    }

    @Override
    public String getNameTag() {
        return nameTag;
    }

    @Override
    public String getDescriptorTag() {
        return descriptorTag;
    }

    @Override
    public String getNickname() {
        return nicknameTag;
    }


    @Override
    public void addTag(IIDTag tag) {
        Iterator<IIDTag> tagsIter = tags.iterator();
        boolean foundTag = false;
        while (tagsIter.hasNext() && !foundTag) {
            IIDTag curr = tagsIter.next();
            if (curr.isSameTagType(tag)) {
                System.out.println("Overwriting tag: " + curr + " for new value: " + tag);
                tagsIter.remove();
                foundTag = true;
            }
        }
        tags.add(tag);
        this.validIID = verifyValidIID();
    }

    @Override
    public void addTags(List<IIDTag> tags) {
        for (IIDTag tag : tags) {
            addTag(tag);
        }
    }

    public boolean isValidIID() { return validIID; }

    private boolean verifyValidIID() {
        // this is where IID standardization happens!
        for (Class classType : criticalTags) {
            boolean foundClass = false;
            Iterator<IIDTag> tagIter = tags.iterator();
            while (tagIter.hasNext() && !foundClass) {
                Class<? extends IIDTag> currTagClass = tagIter.next().getClass();
                if (currTagClass.equals(classType)) { foundClass = true; }
            }
            if (!foundClass) { return false; }
        }
        return true;
    }
}
