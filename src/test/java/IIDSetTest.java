import Exceptions.InvalidRequestException;
import Interfaces.IIDTag;
import Main.IIDContainer;
import Main.IIDSet;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IIDSetTest {

    /* Create tests for the following methods:
     * - void addIngredient(IIDContainer ingredient)
     * - ArrayList<IIDContainer> getIngredientSet()
     * - int getIngredientSetIndex(IIDContainer ingredient)
     * - void addNameTag(int index, String newNameTag)
     * - void addDescriptorTag(int index, String newDescriptorTag)
     * - void addNicknameTag(int index, String newNicknameTag)
     * - void addTag(int index, IIDTag newTag)
     * - void addTags(int index, IIDTag[] newTags)
     * - void addTags(int index, List<IIDTag> newTags)
     * - IIDContainer getIngredient(int index)
     * - ArrayList<IIDContainer> getIngredients(int... indices)
     * - void remove(IIDContainer ingredient)
     * - void remove(IIDContainer[] ingredients)
     * - String getName()
     * - String getDescription()
     * - ArrayList<IIDContainer> compare(IngredientSetInterface otherSet)
     * - ArrayList<IIDContainer> getDifferenceSet()
     * - boolean isEqual(IngredientSetInterface otherSet)
     * - boolean contains(IIDContainer ingredient)
     * - int getSize()
     *  */

    private IIDSet iidSet;
    private IIDContainer beef = new IIDContainer("Beef", "I'm the beef.", new IIDTag[]{IngredientType.PROTEIN, CookState.GRILLED, CutState.SLICED, IngredientUnit.GRAM});
    private IIDContainer duck = new IIDContainer("Duck", "quack!", new IIDTag[]{IngredientType.PROTEIN, CookState.FRIED, CutState.WHOLE, IngredientUnit.MILLILITER});
    private IIDContainer chicken = new IIDContainer("Chicken", "Some chicken noise", new IIDTag[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
    private IIDContainer rockyMountainOysters = new IIDContainer("Rocky Mountain Oysters", "Bull Balls", new IIDTag[] {IngredientType.PROTEIN, CookState.RAW, CutState.WHOLE, IngredientUnit.GRAM});
    private IIDContainer spinach = new IIDContainer("Spinach", "Some green veggie", new IIDTag[] {IngredientType.VEGGIE, CookState.STEAMED, CutState.JULIENNED, IngredientUnit.GRAM});
    private IIDContainer[] containersArray = {beef, duck, chicken};
    private ArrayList<IIDContainer> containersArrayList = new ArrayList<>(Arrays.asList(containersArray));
    private IIDContainer[] containersArrayOf4 = {beef, duck, chicken, rockyMountainOysters};
    private IIDContainer[] containersArrayOf5 = {beef, duck, chicken, rockyMountainOysters, spinach};

    // TODO Test modCounts in the constructor tests?
    @Test
    void ingredientSetWithNoParametersTest() {
        iidSet = new IIDSet();

        assertAll(() -> {
            assertNull(iidSet.getName());
            assertNull(iidSet.getDescription());
            assertEquals(0, iidSet.getSize());
        });
    }

    @Test
    void ingredientSetWithAnArray() {
        iidSet = new IIDSet(containersArray);

        assertAll(() -> {
                    assertNull(iidSet.getName());
                    assertNull(iidSet.getDescription());
                    assertEquals(3, iidSet.getSize());
                    assertEquals(chicken, iidSet.getIngredient(2));
        });
    }

    @Test
    void ingredientSetWithAnArrayList() {
        iidSet = new IIDSet(containersArrayList);

        assertAll(() -> {
            assertNull(iidSet.getName());
            assertNull(iidSet.getDescription());
            assertEquals(3, iidSet.getSize());
            assertEquals(beef, iidSet.getIngredient(0));
        });
    }

    @Test
    void ingredientSetWithNameDescriptionAndArray() {
        iidSet = new IIDSet("Set One", "So so eepy", containersArray);

        assertAll (() -> {
            assertEquals("Set One", iidSet.getName());
            assertEquals("So so eepy", iidSet.getDescription());
            assertEquals(3, iidSet.getSize());
            assertEquals(beef, iidSet.getIngredient(0));
        });
    }

    @Test
    void ingredientSetWithNameDescriptionAndArrayList() {
        iidSet = new IIDSet("Set One", "So so eepy", containersArrayList);

        assertAll (() -> {
            assertEquals("Set One", iidSet.getName());
            assertEquals("So so eepy", iidSet.getDescription());
            assertEquals(3, iidSet.getSize());
            assertEquals(beef, iidSet.getIngredient(0));
        });
    }

    @Test
    void getIngredientSetTest() {
        iidSet = new IIDSet();
        assertEquals(new ArrayList<IIDContainer>(), iidSet.getIngredientSet());

        iidSet = new IIDSet(containersArray);
        assertEquals(containersArrayList, iidSet.getIngredientSet());

        iidSet = new IIDSet(new IIDContainer[] {beef, duck});
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck})), iidSet.getIngredientSet());
    }

    @Test
    void addIngredientTest() {
        iidSet = new IIDSet();

        iidSet.addIngredient(chicken);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {chicken})), iidSet.getIngredientSet());

        iidSet = new IIDSet(containersArray);

        iidSet.addIngredient(rockyMountainOysters);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck, chicken, rockyMountainOysters})), iidSet.getIngredientSet());
    }

    @Test
    void addIngredientsTest() {
        iidSet = new IIDSet();

        iidSet.addIngredients(beef);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef})), iidSet.getIngredientSet());

        iidSet.addIngredients(duck, chicken);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck, chicken})), iidSet.getIngredientSet());

        iidSet = new IIDSet(containersArray);
        iidSet.addIngredients(rockyMountainOysters, spinach);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck, chicken, rockyMountainOysters, spinach})), iidSet.getIngredientSet());
    }

    @Test
    void getIngredientIndexGuardTest() {
        iidSet = new IIDSet(containersArrayList);

        assertThrows(InvalidRequestException.class, () -> {
            iidSet.getIngredientIndex(rockyMountainOysters);
            iidSet.getIngredientIndex(new IIDContainer("Pork", "Oink Oink", new IIDTag[] { CookState.STEAMED, CutState.SLICED }));
        });

        assertDoesNotThrow(() -> {
            iidSet.getIngredientIndex(beef);
            iidSet.getIngredientIndex(duck);
            iidSet.getIngredientIndex(chicken);
        });
    }

    @Test
    void getIngredientIndexTest() {
        iidSet = new IIDSet(containersArrayList);

        assertAll(() -> {
            assertEquals(0, iidSet.getIngredientIndex(beef));
            assertEquals(1, iidSet.getIngredientIndex(duck));
            assertEquals(2, iidSet.getIngredientIndex(chicken));
        });

        iidSet.remove(duck);

        assertAll(() -> {
            assertEquals(0, iidSet.getIngredientIndex(beef));
            assertEquals(1, iidSet.getIngredientIndex(chicken));
        });
    }

    @Test
    void getIngredientGuardTest() {
        iidSet = new IIDSet(containersArray);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iidSet.getIngredient(-1);
            iidSet.getIngredient(3);
            iidSet.getIngredient(4);
        });

        assertDoesNotThrow(() -> {
           iidSet.getIngredient(0);
           iidSet.getIngredient(1);
            iidSet.getIngredient(2);
        });
    }

    @Test
    void getIngredientTest() {
        iidSet = new IIDSet(containersArray);

        assertEquals(containersArray[0], iidSet.getIngredient(0));
        assertEquals(containersArray[1], iidSet.getIngredient(1));
        assertEquals(containersArray[2], iidSet.getIngredient(2));

        IIDContainer[] newContainerArray = {beef, duck, chicken, rockyMountainOysters};
        iidSet = new IIDSet(newContainerArray);

        assertEquals(newContainerArray[0], iidSet.getIngredient(0));
        assertEquals(newContainerArray[1], iidSet.getIngredient(1));
        assertEquals(newContainerArray[2], iidSet.getIngredient(2));
        assertEquals(newContainerArray[3], iidSet.getIngredient(3));
    }

    @Test
    void getIngredientsGuardTest() {
        iidSet = new IIDSet(containersArray);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iidSet.getIngredients(-1, 0, 1 , 2);
            iidSet.getIngredients(0, 1, 2, 3);
            iidSet.getIngredients(0, 1, 2, 3, 4);
            iidSet.getIngredients(-1, 0, 1, 2, 3, 4);
        });

        assertDoesNotThrow(() -> {
           iidSet.getIngredients(0);
           iidSet.getIngredients(0, 1);
           iidSet.getIngredients(0, 1, 2);
        });
    }

    @Test
    void getIngredientsTest() {
        iidSet = new IIDSet(containersArray);

        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef})), iidSet.getIngredients(0));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, chicken})), iidSet.getIngredients(0, 2));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck, chicken})), iidSet.getIngredients(0, 1, 2));

        iidSet.addIngredient(rockyMountainOysters);

        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters})), iidSet.getIngredients(3));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, rockyMountainOysters})), iidSet.getIngredients(0, 3));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, duck, chicken, rockyMountainOysters})), iidSet.getIngredients(0, 1, 2, 3));
    }

    @Test
    void removeOneIngredientGuardTest() {
        iidSet = new IIDSet(containersArray);

        assertThrows(InvalidRequestException.class, () -> {
           iidSet.remove(rockyMountainOysters);
           iidSet.remove(new IIDContainer("Spinach", "Some green veggie", new IIDTag[] {IngredientType.VEGGIE, CookState.STEAMED, CutState.DICED, IngredientUnit.GRAM}));
        });

        assertDoesNotThrow(() -> {
            iidSet.remove(beef);
            iidSet.remove(duck);
            iidSet.remove(chicken);
        });
    }

    @Test
    void removeOneIngredientTest() {
        iidSet = new IIDSet(containersArray);

        iidSet.remove(duck);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {beef, chicken})), iidSet.getIngredientSet());
        assertEquals(0, iidSet.getIngredientIndex(beef));
        assertEquals(1, iidSet.getIngredientIndex(chicken));

        iidSet.remove(beef);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {chicken})), iidSet.getIngredientSet());
        assertEquals(0, iidSet.getIngredientIndex(chicken));

        iidSet.addIngredient(rockyMountainOysters);
        iidSet.remove(chicken);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters})), iidSet.getIngredientSet());
        assertEquals(0, iidSet.getIngredientIndex(rockyMountainOysters));
    }

    @Test
    void removeMultipleIngredientsGuardTest() {
        iidSet = new IIDSet(containersArray);

        assertThrows(InvalidRequestException.class, () -> {
           iidSet.remove(rockyMountainOysters);
           iidSet.remove(spinach);
           iidSet.remove(new IIDContainer("John Pork", "Yabbadabbadoo", new IIDTag[] {IngredientType.PROTEIN, CookState.RAW, CutState.WHOLE, IngredientUnit.GRAM}));
        });

        assertDoesNotThrow(() -> {
            iidSet.remove(beef, duck);
        });

        iidSet.addIngredients(rockyMountainOysters, spinach);

        assertDoesNotThrow(() -> {
            iidSet.remove(chicken, rockyMountainOysters);
        });
    }

    @Test
    void removeMultipleIngredientsTest() {
        iidSet = new IIDSet(containersArray);

        iidSet.remove(beef, chicken);
        assertEquals(0, iidSet.getIngredientIndex(duck));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {duck})), iidSet.getIngredientSet());

        iidSet.addIngredients(rockyMountainOysters, spinach);
        iidSet.remove(duck, spinach);
        assertEquals(0, iidSet.getIngredientIndex(rockyMountainOysters));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters})), iidSet.getIngredientSet());
    }

    @Test
    void getNameTest() {
        iidSet = new IIDSet("Set One", "Generic Description", containersArray);
        assertEquals("Set One", iidSet.getName());

        iidSet = new IIDSet("", "", containersArray);
        assertEquals("", iidSet.getName());
    }

    @Test
    void getDescriptionTest() {
        iidSet = new IIDSet("Set One", "Generic Description", containersArray);
        assertEquals("Generic Description", iidSet.getDescription());

        iidSet = new IIDSet("", "", containersArray);
        assertEquals("", iidSet.getDescription());
    }

    @Test
    void compareTest() {
        IIDSet setOne = new IIDSet(containersArray);
        IIDSet setTwo = new IIDSet(containersArrayList);

        assertEquals(new ArrayList<IIDContainer>(), setOne.compare(setTwo));

        setTwo = new IIDSet(containersArrayOf4);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters})), setOne.compare(setTwo));

        setTwo = new IIDSet(containersArrayOf5);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters, spinach})), setOne.compare(setTwo));
    }

    @Test
    void getDifferenceSetTest() {
        IIDSet setOne = new IIDSet(containersArray);
        IIDSet setTwo = new IIDSet(containersArrayList);

        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(), setOne.getDifferenceSet());

        setTwo = new IIDSet(containersArrayOf4);
        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters})), setOne.getDifferenceSet());

        setTwo = new IIDSet(containersArrayOf5);
        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[] {rockyMountainOysters, spinach})), setOne.getDifferenceSet());
    }

    @Test
    void isEqualTest() {
        IIDSet setOne = new IIDSet(containersArray);
        IIDSet setTwo = new IIDSet(containersArrayList);

        assertTrue(setOne.isEqual(setTwo));

        setTwo = new IIDSet(containersArrayOf4);
        assertFalse(setOne.isEqual(setTwo));

        setTwo = new IIDSet(containersArrayOf5);
        assertFalse(setOne.isEqual(setTwo));

        setTwo = new IIDSet(chicken, beef, duck);
        assertTrue(setOne.isEqual(setTwo));

        setTwo = new IIDSet();
        assertFalse(setOne.isEqual(setTwo));
    }

    @Test
    void containsTest(){
        iidSet = new IIDSet(containersArray);

        assertTrue(iidSet.contains(beef));
        assertTrue(iidSet.contains(duck));
        assertTrue(iidSet.contains(chicken));
        IIDContainer otherChicken = new IIDContainer("Chicken", "Some chicken noise", new IIDTag[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
        assertFalse(iidSet.contains(otherChicken));
        assertFalse(iidSet.contains(rockyMountainOysters));
        assertFalse(iidSet.contains(spinach));
    }

    @Test
    void getSizeTest() {
        iidSet = new IIDSet(containersArray);
        assertEquals(3, iidSet.getSize());

        iidSet.addIngredient(rockyMountainOysters);
        assertEquals(4, iidSet.getSize());

        iidSet.addIngredient(spinach);
        assertEquals(5, iidSet.getSize());

        iidSet.remove(beef, chicken, rockyMountainOysters, spinach);
        assertEquals(1, iidSet.getSize());
    }
}