package Main;

public class PantryNode {

    private IIDContainer iidContainer;
    private int validModCount;
    private int quantity;

    public PantryNode(IIDContainer iidContainer) {
        this(iidContainer, 0);
    }
    public PantryNode(IIDContainer iidContainer, int quantity) {
        if (!iidContainer.isValidIID()) { throw new IllegalArgumentException("Cannot create a PantryNode with invalid IIDContainer!"); }
        this.iidContainer = iidContainer;
        this.validModCount = iidContainer.getModCount();
        this.quantity = quantity;
    }

    // Helpers ==============================================================
    private void checkMod() {
        if (validModCount != iidContainer.getModCount()) { throw new IllegalStateException(); }
    }
}
