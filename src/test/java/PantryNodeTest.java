import Main.IIDContainer;
import Main.PantryNode;
import Tags.CutState;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

class PantryNodeTest {

    IIDContainer getValidIIDContainer() { return IIDTests.getValidIIDContainer(); };

    @Test
    void getQuantityTest() {
        assertEquals(10, (new PantryNode(getValidIIDContainer(), 10)).getQuantity());
        assertEquals(0, (new PantryNode(getValidIIDContainer())).getQuantity());
    }

    @Test
    void setQuantityTest() {
        PantryNode testPantryNode = new PantryNode(getValidIIDContainer());
        assertEquals(0, testPantryNode.getQuantity());
        testPantryNode.setQuantity(10);
        assertEquals(10, testPantryNode.getQuantity());
        testPantryNode.setQuantity(0);
        assertEquals(0, testPantryNode.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {testPantryNode.setQuantity(-10);} );
        assertEquals(0, testPantryNode.getQuantity());

    }

    @Test
    void addQuantityTest() {
        PantryNode testPantryNode = new PantryNode(getValidIIDContainer());
        assertEquals(0, testPantryNode.getQuantity());
        testPantryNode.addQuantity(10);
        assertEquals(10, testPantryNode.getQuantity());
        testPantryNode.addQuantity(1);
        assertEquals(11, testPantryNode.getQuantity());
        testPantryNode.addQuantity(0);
        assertEquals(11, testPantryNode.getQuantity());
        testPantryNode.addQuantity();
        assertEquals(12, testPantryNode.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {testPantryNode.addQuantity(-1);} );
        assertEquals(12, testPantryNode.getQuantity());
    }


    @Test
    void removeQuantity() {
        PantryNode testPantryNode = new PantryNode(getValidIIDContainer(), 10);
        assertEquals(10, testPantryNode.getQuantity());
        testPantryNode.removeQuantity(1);
        assertEquals(9, testPantryNode.getQuantity());
        testPantryNode.removeQuantity(0);
        assertEquals(9, testPantryNode.getQuantity());
        testPantryNode.removeQuantity();
        assertEquals(8, testPantryNode.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {testPantryNode.removeQuantity(-1);} );
        assertEquals(8, testPantryNode.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {testPantryNode.removeQuantity(9);} );
        assertEquals(8, testPantryNode.getQuantity());

        testPantryNode.removeQuantity(8);
        assertEquals(0, testPantryNode.getQuantity());


    }

    @Test
    void concurrentFailTest() {
        IIDContainer iidContainer = getValidIIDContainer();
        PantryNode testPantryNode = new PantryNode(iidContainer);

        assertDoesNotThrow(() -> {
            testPantryNode.addQuantity();
            testPantryNode.removeQuantity();
            testPantryNode.getQuantity();
        });

        iidContainer.unlock();
        iidContainer.addTag(CutState.NONE);
        iidContainer.lock();

        assertThrows(ConcurrentModificationException.class, testPantryNode::addQuantity);
        assertThrows(ConcurrentModificationException.class, testPantryNode::removeQuantity);
        assertThrows(ConcurrentModificationException.class, testPantryNode::getQuantity);
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.addQuantity(10);});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.removeQuantity(10);});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.setQuantity(10);});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.getTag(CutState.class);});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.getTags();});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.getDescriptorTag();});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.getNameTag();});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.getNickname();});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.isIID(getValidIIDContainer());});
        assertThrows(ConcurrentModificationException.class, () -> {testPantryNode.hasTag(CutState.NONE);});
    }
}