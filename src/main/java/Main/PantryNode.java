package Main;

import Interfaces.IIDContainerInterface;
import Interfaces.IIDTag;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * <code>PantryNode</code> pairs an <code>IIDContainer</code> with a quantity.
 * <p>
 * It provides pass-through methods to access the <code>IIDContainer</code>, but will throw an exception
 * if the referenced container is modified in any way.
 *
 */
public class PantryNode implements IIDContainerInterface {

    private final IIDContainer iidContainer;
    private final int validModCount;
    private int quantity;


    /**
     * @param iidContainer the <code>IIDContainer</code> to be paired with this node
     */
    public PantryNode(IIDContainer iidContainer) {
        this(iidContainer, 0);
    }
    public PantryNode(IIDContainer iidContainer, int quantity) {
        if (!iidContainer.isValidIID()) { throw new IllegalArgumentException("Cannot create a PantryNode with invalid IIDContainer!"); }
        this.iidContainer = iidContainer;
        this.validModCount = iidContainer.getModCount();
        setQuantity(quantity);
    }

    // Helpers ==============================================================
    private void checkMod() {
        if (validModCount != iidContainer.getModCount()) { throw new ConcurrentModificationException(); }
    }

    public int getQuantity() {
        checkMod();
        return quantity;
    }

    public void setQuantity(int quantity) {
        checkMod();
        if (quantity < 0) { throw new IllegalArgumentException("Cannot set a negative quantity!"); }
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        checkMod();
        if (quantity < 0) { throw new IllegalArgumentException("Cannot add a negative quantity!"); }
        this.quantity += quantity;
    }

    public void addQuantity() { addQuantity(1); }

    public void removeQuantity(int quantity) {
        checkMod();
        if (quantity < 0) { throw new IllegalArgumentException("Cannot remove a negative quantity!"); }
        if (this.quantity < quantity) { throw new IllegalArgumentException("Cannot remove a quantity greater than the current quantity of " + this.quantity + "!"); }
        this.quantity -= quantity;
    }

    public void removeQuantity() { removeQuantity(1); }


    // <editor-fold desc="IIDContainer Implementation (Pass-through)">
    @Override
    public String getIIDString() {
        checkMod();
        return iidContainer.getIIDString();
    }

    @Override
    public boolean isIID(IIDContainerInterface iidContainer) {
        checkMod();
        return this.iidContainer.isIID(iidContainer);
    }

    @Override
    public ArrayList<IIDTag> getTags() {
        checkMod();
        return iidContainer.getTags();
    }

    @Override
    public boolean hasTag(IIDTag tag) {
        checkMod();
        return iidContainer.hasTag(tag);
    }

    @Override
    public <T extends IIDTag> IIDTag getTag(Class<T> tag) {
        checkMod();
        return iidContainer.getTag(tag);
    }

    @Override
    public String getNameTag() {
        checkMod();
        return iidContainer.getNameTag();
    }

    @Override
    public String getDescriptorTag() {
        checkMod();
        return iidContainer.getDescriptorTag();
    }

    @Override
    public String getNickname() {
        checkMod();
        return iidContainer.getNickname();
    }

    @Override
    public int getModCount() {
        checkMod();
        return iidContainer.getModCount();
    }
    // </editor-fold
}
