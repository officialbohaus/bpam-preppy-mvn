import Interfaces.IIDTag;
import Main.IIDContainer;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IIDTests {

    // Constructor Tests ==============================================
    // TODO: all constructors need testing
    @Test
    public void EmptyConstructorTests() {
        assertDoesNotThrow(() -> { emptyIIDContainer(); });
    }

    @Test
    public void ArrayConstructorTests() {
        assertDoesNotThrow(() -> { new IIDContainer(new IIDTag[0]); });
        assertDoesNotThrow(() -> { new IIDContainer(new IIDTag[10]); });
        assertDoesNotThrow(() -> { new IIDContainer(validTagsArray); });
        // these should likely throw exceptions in future?
        assertDoesNotThrow(() -> { new IIDContainer(underValidTagsArray); });
        assertDoesNotThrow(() -> { new IIDContainer(overValidTagsArray); });
    }

    @Test
    public void ListConstructorTests() {
        assertDoesNotThrow(() -> { new IIDContainer(new ArrayList<IIDTag>()); });
        assertDoesNotThrow(() -> { new IIDContainer(new ArrayList<IIDTag>(10)); });
        assertDoesNotThrow(() -> { new IIDContainer(Arrays.asList(validTagsArray)); });
        assertDoesNotThrow(() -> { new IIDContainer(Arrays.asList(underValidTagsArray)); });
        assertDoesNotThrow(() -> { new IIDContainer(Arrays.asList(overValidTagsArray)); });
    }

    // Available Methods & Variables (via Constructor Tests) ======================================
    private IIDContainer emptyIIDContainer() { return new IIDContainer(); }
    private final IIDTag[] validTagsArray = new IIDTag[] {CutState.NONE, CookState.NONE, IngredientUnit.UNIT, IngredientType.MISC };
    private final IIDTag[] underValidTagsArray = new IIDTag[] { CutState.NONE };
    private final IIDTag[] overValidTagsArray = new IIDTag[] { CutState.NONE, CookState.NONE, IngredientUnit.UNIT, IngredientType.MISC, CutState.CHOPPED, CookState.RAW, IngredientUnit.UNIT };


    // Validity Tests ================================================
    @Test
    public void emptyIIDContainerValidityTests() {
        // an IIDContainer holds a valid IID if it has one of each critical tag, and a nonempty nameTag and descriptorTag
        assertFalse(emptyIIDContainer().isValidIID());
        assertFalse(new IIDContainer(new IIDTag[10]).isValidIID());
        assertFalse(new IIDContainer(new ArrayList<IIDTag>(10)).isValidIID());
    }

    @Test
    public void noNameTagIIDContainerValidityTests() {
        IIDContainer testContainer = getNoNameTagIIDContainer();
        assertFalse(testContainer.isValidIID());
        testContainer.addNameTag("TestFood");
        assertTrue(testContainer.isValidIID());
        testContainer.unlock();
        testContainer.addNameTag("TestFood2");
        assertTrue(testContainer.isValidIID());
        testContainer.addNameTag("");
        assertFalse(testContainer.isValidIID());
    }

    @Test
    public void noDescriptorTagIIDContainerValidityTests() {
        IIDContainer testContainer = new IIDContainer("TestFood", "", validTagsArray);
        assertFalse(testContainer.isValidIID());
        testContainer.addDescriptorTag("Steamed Gruel");
        assertTrue(testContainer.isValidIID());
        testContainer.unlock();
        testContainer.addDescriptorTag("Over-steamed Gruel");
        assertTrue(testContainer.isValidIID());
        testContainer.addDescriptorTag("");
        assertFalse(testContainer.isValidIID());
    }

    // Available Methods & Variables (via Validity Tests) ===========================
    private IIDContainer getValidIIDContainer() { return new IIDContainer("TestFood", "Steamed Gruel", validTagsArray); }
    private IIDContainer getNoNameTagNoDescriptorTagIIDContainer() { return new IIDContainer(validTagsArray); }
    private IIDContainer getNoNameTagIIDContainer() { return new IIDContainer("", "Steamed Gruel", validTagsArray); }

    // Lock Tests ==============================================================================
    @Test
    public void isLockedTest() {
        // should default to unlocked from incomplete constructions
        assertFalse(getNoNameTagIIDContainer().isLocked());
        assertFalse((new IIDContainer()).isLocked());
        assertFalse((new IIDContainer(validTagsArray)).isLocked());
        assertFalse((new IIDContainer("","",validTagsArray)).isLocked());
        assertFalse((new IIDContainer("","",underValidTagsArray)).isLocked());
        assertFalse((new IIDContainer("","",overValidTagsArray)).isLocked());
        assertFalse((new IIDContainer("TestFood","",overValidTagsArray)).isLocked());
        assertFalse((new IIDContainer("TestFood","Steamed Gruel",underValidTagsArray)).isLocked());

        // should default to locked from complete constructions
        assertTrue(getValidIIDContainer().isLocked());

        // should autolock once valid IID is stored if container has never locked (defaulted to unlocked)
        IIDContainer testContainer = getNoNameTagIIDContainer();
        assertFalse(testContainer.isLocked()); // verify it is unlocked
        testContainer.addNameTag(""); // adds invalid nameTag
        assertFalse(testContainer.isLocked()); // should still be locked, still holds an incomplete IID
        testContainer.addNameTag("TestFood"); // this should cause container to lock, as this completes the contained IID
        assertTrue(testContainer.isLocked());
    }

    @Test
    public void lockTest() {
        // should not be able to lock container holding incomplete IID
        assertThrows(IllegalStateException.class, () -> {
            IIDContainer testContainer = getNoNameTagIIDContainer();
            testContainer.lock();
        });
        assertThrows(IllegalStateException.class, () -> {
            IIDContainer testContainer = getValidIIDContainer();
            testContainer.unlock();
            testContainer.addNameTag("");
            testContainer.lock();
        });

        // should not be able to lock container that is already locked
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().lock();
        });
        assertThrows(IllegalStateException.class, () -> {
            IIDContainer testContainer = getNoNameTagIIDContainer();
            testContainer.addNameTag("TestFood");
            testContainer.lock();
        });

        // should not be able to unlock container that is already unlocked
        assertThrows(IllegalStateException.class, () -> {
            getNoNameTagIIDContainer().unlock();
        });

        // should not allow setting access when locked
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().addTag(CutState.GROUND);
        });
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().addNameTag("TestFood2");
        });
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().addDescriptorTag("Over-steamed Gruel");
        });
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().addNicknameTag("Mom's favorite");
        });
        assertThrows(IllegalStateException.class, () -> {
            getValidIIDContainer().addTags(List.of(CutState.NONE, CookState.NONE));
        });

        // should allow getting access when locked
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getTag(CutState.class);
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getTags();
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getDescriptorTag();
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getNameTag();
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getNickname();
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getContainerID();
        });
        assertDoesNotThrow(() -> {
            getValidIIDContainer().getIIDString();
        });
    }

    @Test
    public void unlockTest() {
        // should not be able to unlock an unlocked container
        assertThrows(IllegalStateException.class, () -> {
            getNoNameTagIIDContainer().unlock();
        });
        assertThrows(IllegalStateException.class, () -> {
            IIDContainer testContainer = getValidIIDContainer();
            testContainer.unlock();
            testContainer.unlock();
        });

        // should be able to unlock a locked container
        assertDoesNotThrow(() -> {
            getValidIIDContainer().unlock();
        });
        assertDoesNotThrow(() -> {
            IIDContainer testContainer = getNoNameTagIIDContainer();
            testContainer.addNameTag("TestFood");
            testContainer.unlock();
        });

        // a manually unlocked container should remain unlocked
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        assertFalse(testContainer.isLocked());
        testContainer.addNameTag("");
        testContainer.addNameTag("TestFood2");
        assertFalse(testContainer.isLocked());
        testContainer.addTag(CutState.GROUND);
        assertFalse(testContainer.isLocked());
        testContainer.lock();
        assertTrue(testContainer.isLocked());

    }
    // Available Methods & Variables (via Lock Tests) ==========================================


    // Get Tests ====================================================
    @Test
    public void getIIDStringTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getTagsTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getNameTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getDescriptorTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getNicknameTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getContainerIDTest() {
        fail("Not yet implemented");
    }

    @Test
    public void toStringTest() {
        fail("Not yet implemented");
    }
    // Available Methods & Variables (via Get Tests) ===========================================

    // Checkers Tests ==========================================================================
    @Test
    public void isIIDTest() {
        fail("Not yet implemented");
    }

    @Test
    public void hasTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void isValidIIDTest() {
        fail("Not yet implemented");
    }

    // Available Methods & Variables (via Checkers Tests) ========================================

    // Setters Tests =============================================================================
    @Test
    public void addTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void addNameTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void addDescriptorTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void addNicknameTagTest() {
        fail("Not yet implemented");
    }

    @Test
    public void addTagsTest() {
        fail("Not yet implemented");
    }

}
