import Exceptions.InvalidIIDException;
import Interfaces.IIDTag;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        testContainer.addDescriptorTag("Over-steamed Gruel");
        assertTrue(testContainer.isValidIID());
        testContainer.addDescriptorTag("");
        assertFalse(testContainer.isValidIID());
    }

    // Available Methods & Variables (via Validity Tests) ===========================
    private IIDContainer getValidIIDContainer() { return new IIDContainer("TestFood", "Steamed Gruel", validTagsArray); }
    private IIDContainer getNoNameTagNoDescriptorTagIIDContainer() { return new IIDContainer(validTagsArray); }
    private IIDContainer getNoNameTagIIDContainer() { return new IIDContainer("", "Steamed Gruel", validTagsArray); }

}
