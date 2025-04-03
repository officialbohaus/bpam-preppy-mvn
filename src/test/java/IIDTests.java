import Interfaces.IIDTag;
import Main.IIDContainer;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IIDTests {



    // Constructor Tests ==============================================
    // TODO: all constructors need testing
    // <editor-fold desc="Constructor Tests Fold">
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
    // </editor-fold>
    // Available Methods & Variables (via Constructor Tests) ======================================
    private static IIDContainer emptyIIDContainer() { return new IIDContainer(); }
    private static final IIDTag[] validTagsArray = new IIDTag[] {CutState.NONE, CookState.NONE, IngredientUnit.UNIT, IngredientType.MISC };
    private static final IIDTag[] underValidTagsArray = new IIDTag[] { CutState.NONE };
    private static final IIDTag[] overValidTagsArray = new IIDTag[] { CutState.NONE, CookState.NONE, IngredientUnit.UNIT, IngredientType.MISC, CutState.CHOPPED, CookState.RAW, IngredientUnit.UNIT };

    public static final IIDContainer IID_TEST_CONTAINER_1 = new IIDContainer("TestFood1", "Test Food 1", validTagsArray);
    public static final IIDContainer IID_TEST_CONTAINER_2 = new IIDContainer("TestFood2", "Test Food 2", validTagsArray);
    public static final IIDContainer IID_TEST_CONTAINER_3 = new IIDContainer("TestFood3", "Test Food 3", validTagsArray);
    public static final IIDContainer IID_TEST_CONTAINER_4 = new IIDContainer("TestFood4", "Test Food 4", validTagsArray);

    // Validity Tests ================================================
    // <editor-fold desc="Validity Tests Fold">
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
    // </editor-fold>
    // Available Methods & Variables (via Validity Tests) ===========================
    private static final String validNameTag = "TestFood";
    private static final String validDescriptorTag = "Steamed Gruel";
    public static IIDContainer getValidIIDContainer() { return new IIDContainer(validNameTag, validDescriptorTag, validTagsArray); }
    private static IIDContainer getNoNameTagNoDescriptorTagIIDContainer() { return new IIDContainer(validTagsArray); }
    private static IIDContainer getNoNameTagIIDContainer() { return new IIDContainer("", validDescriptorTag, validTagsArray); }


    // Lock Tests ==============================================================================
    // <editor-fold desc="Lock Tests Fold">
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
    // </editor-fold>
    // Available Methods & Variables (via Lock Tests) ==========================================



    // Get Tests ====================================================
    // TODO: Finish
    // <editor-fold desc="Get Tests Fold">
    @Test
    public void getIIDStringTest() {
        fail("Not yet implemented");
    }

    @Test
    public void getTagsTest() {
        IIDContainer testContainer = getValidIIDContainer();
        ArrayList<IIDTag> tagContainer = testContainer.getTags();
        assertArrayEquals(validTagsArray, tagContainer.toArray());
    }

    @Test
    public void getTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        IIDTag CutStateTest = testContainer.getTag(CutState.class);
        assertTrue(CutStateTest.isSameTag(validTagsArray[0]));
        IIDTag CookStateTest = testContainer.getTag(CookState.class);
        assertTrue(CookStateTest.isSameTag(validTagsArray[1]));
        IIDTag IngredientUnitTest = testContainer.getTag(IngredientUnit.class);
        assertTrue(IngredientUnitTest.isSameTag(validTagsArray[2]));
        IIDTag IngredientTypeTest = testContainer.getTag(IngredientType.class);
        assertTrue(IngredientTypeTest.isSameTag(validTagsArray[3]));
    }

    @Test
    public void getNameTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        String nameTagTest = testContainer.getNameTag();
        assertEquals(validNameTag, nameTagTest);
    }

    @Test
    public void getDescriptorTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        String descriptorTagTest = testContainer.getDescriptorTag();
        assertEquals(validDescriptorTag, descriptorTagTest);
    }

    @Test
    public void getNicknameTest() {
        IIDContainer testContainer = getValidIIDContainer();
        String nickNameTest = testContainer.getNickname();
        assertNull(nickNameTest);
        testContainer.unlock();
        testContainer.addNicknameTag("Mom's Favorite");
        testContainer.lock();
        nickNameTest = testContainer.getNickname();
        assertEquals("Mom's Favorite", nickNameTest);
    }

    @Test
    public void getContainerIDTest() {

    }

    @Test
    public void toStringTest() {
        // calling toString does not return null and an empty string
        IIDContainer testContainer = getValidIIDContainer();
        String expectedTest = testContainer.toString();
        assertEquals(expectedTest, testContainer.toString());
        assertNotNull(expectedTest);
    }
    // </editor-fold>
    // Available Methods & Variables (via Get Tests) ===========================================



    // Checkers Tests ==========================================================================
    // <editor-fold desc="Checkers Tests Fold">
    @Test
    public void isIIDTest() {
        IIDContainer testContainer = getValidIIDContainer();
        assertTrue(testContainer.isValidIID(), "The variable is a valid IID.");
        IIDContainer falseContainer = getNoNameTagIIDContainer();
        assertFalse(falseContainer.isValidIID(), "The variable is an invalid IID: No Name Tag.");
        IIDContainer falseContainer2 = getNoNameTagNoDescriptorTagIIDContainer();
        assertFalse(falseContainer2.isValidIID(), "The variable is an invalid IID: No Name Tag, No Descriptor Tag.");
        IIDContainer falseContainer3 = emptyIIDContainer();
        assertFalse(falseContainer3.isValidIID(), "The variable is an invalid IID: Is empty of all tags");

    }

    @Test
    public void hasTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        assertTrue(testContainer.hasTag(CutState.NONE));
        assertTrue(testContainer.hasTag(CookState.NONE));
        assertTrue(testContainer.hasTag(IngredientUnit.UNIT));
        assertTrue(testContainer.hasTag(IngredientType.MISC));
    }

    @Test
    public void isValidIIDTest() {
        IIDContainer testContainer = getValidIIDContainer();
        assertTrue(testContainer.isValidIID());
        IIDContainer falseContainer = getNoNameTagIIDContainer();
        assertFalse(falseContainer.isValidIID());
        IIDContainer falseContainer2 = getNoNameTagNoDescriptorTagIIDContainer();
        assertFalse(falseContainer2.isValidIID());
    }
    // </editor-fold>
    // Available Methods & Variables (via Checkers Tests) ========================================



    // Setters Tests =============================================================================
    // <editor-fold desc="Setters Tests Fold">
    @Test
    public void addTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        testContainer.addTag(CutState.GROUND);
        assertTrue(testContainer.hasTag(CutState.GROUND));
        testContainer.addTag(CookState.RAW);
        assertTrue(testContainer.hasTag(CookState.RAW));
        testContainer.addTag(IngredientUnit.GRAM);
        assertTrue(testContainer.hasTag(IngredientUnit.GRAM));
        testContainer.addTag(IngredientType.PROTEIN);
        assertTrue(testContainer.hasTag(IngredientType.PROTEIN));
        testContainer.lock();
    }

    @Test
    public void addNameTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        testContainer.addNameTag("Bacon");
        testContainer.lock();
        assertTrue(testContainer.isValidIID());
        IIDContainer testContainer2 = getNoNameTagIIDContainer();
        testContainer2.addNameTag("Pineapple"); // adding a Name tag completes the auto lock of container
        assertTrue(testContainer2.isValidIID());
    }

    @Test
    public void addDescriptorTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        testContainer.addDescriptorTag("Finely chopped");
        testContainer.lock();
        assertTrue(testContainer.isValidIID());
        IIDContainer testContainer2 = getNoNameTagNoDescriptorTagIIDContainer();
        testContainer2.addNameTag("Red Kidney Beans"); // adding a Name tag is a step in the auto lock of container
        testContainer2.addDescriptorTag("Twice Baked"); // adding a Descriptor tag completes the auto lock of container
        assertTrue(testContainer2.isValidIID());

    }

    @Test
    public void addNicknameTagTest() {
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        testContainer.addNicknameTag("Grandma's Affair Chocolate Cake");
        testContainer.lock();
        assertTrue(testContainer.isValidIID());
    }

    @Test
    public void addTagsTest() {
        IIDContainer testContainer = getValidIIDContainer();
        testContainer.unlock();
        IIDTag[] testList = {CutState.WHOLE, CookState.RAW};
        ArrayList<IIDTag> testList2 = new ArrayList(Arrays.asList(testList));
        testContainer.addTags(testList2);
        testContainer.lock();
        assertTrue(testContainer.hasTag(CutState.WHOLE));
        assertTrue(testContainer.hasTag(CookState.RAW));
    }

    // </editor-fold>

}
