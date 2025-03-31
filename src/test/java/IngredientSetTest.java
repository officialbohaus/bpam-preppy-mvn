import Exceptions.InvalidRequestException;
import Interfaces.IIDTag;
import Interfaces.IngredientSetInterface;
import Main.IIDContainer;
import Main.IngredientSet;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class IngredientSetTest {

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

    private IngredientSet ingredientSet;
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
        ingredientSet = new IngredientSet();

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(0, ingredientSet.getSize());
        });
    }

    @Test
    void ingredientSetWithAnArray() {
        ingredientSet = new IngredientSet(containersArray);

        assertAll(() -> {
                    assertNull(ingredientSet.getName());
                    assertNull(ingredientSet.getDescription());
                    assertEquals(3, ingredientSet.getSize());
                    assertEquals(chicken, ingredientSet.getIngredient(2));
        });
    }

    @Test
    void ingredientSetWithAnArrayList() {
        ingredientSet = new IngredientSet(containersArrayList);

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(3, ingredientSet.getSize());
            assertEquals(beef, ingredientSet.getIngredient(0));
        });
    }

    @Test
    void ingredientSetWithNameDescriptionAndArray() {
        ingredientSet = new IngredientSet("Set One", "So so eepy", containersArray);

        assertAll (() -> {
            assertEquals("Set One", ingredientSet.getName());
            assertEquals("So so eepy", ingredientSet.getDescription());
            assertEquals(3, ingredientSet.getSize());
            assertEquals(beef, ingredientSet.getIngredient(0));
        });
    }

    @Test
    void ingredientSetWithNameDescriptionAndArrayList() {
        ingredientSet = new IngredientSet("Set One", "So so eepy", containersArrayList);

        assertAll (() -> {
            assertEquals("Set One", ingredientSet.getName());
            assertEquals("So so eepy", ingredientSet.getDescription());
            assertEquals(3, ingredientSet.getSize());
            assertEquals(beef, ingredientSet.getIngredient(0));
        });
    }

    @Test
    void getIngredientSetTest() {
        ingredientSet = new IngredientSet();
        assertEquals(new ArrayList<IIDContainer>(), ingredientSet.getIngredientSet());
        ingredientSet.getIngredientSet().add(beef);
        assertEquals(new ArrayList<IIDContainer>(), ingredientSet.getIngredientSet());

        ingredientSet = new IngredientSet(containersArray);
        assertEquals(containersArrayList, ingredientSet.getIngredientSet());
        ingredientSet.getIngredientSet().add(rockyMountainOysters);
        assertEquals(containersArrayList, ingredientSet.getIngredientSet());

        ingredientSet = new IngredientSet(beef, duck);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck)), ingredientSet.getIngredientSet());
        ingredientSet.getIngredientSet().add(chicken);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck)), ingredientSet.getIngredientSet());
    }

    @Test
    void addIngredientTest() {
        ingredientSet = new IngredientSet();

        ingredientSet.addIngredient(chicken);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(chicken)), ingredientSet.getIngredientSet());

        ingredientSet = new IngredientSet(containersArray);

        ingredientSet.addIngredient(rockyMountainOysters);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck, chicken, rockyMountainOysters)), ingredientSet.getIngredientSet());
    }

    @Test
    void addIngredientsTest() {
        ingredientSet = new IngredientSet();

        ingredientSet.addIngredients(beef);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(beef)), ingredientSet.getIngredientSet());

        ingredientSet.addIngredients(duck, chicken);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck, chicken)), ingredientSet.getIngredientSet());

        ingredientSet = new IngredientSet(containersArray);
        ingredientSet.addIngredients(rockyMountainOysters, spinach);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck, chicken, rockyMountainOysters, spinach)), ingredientSet.getIngredientSet());
    }

    @Test
    void getIngredientIndexGuardTest() {
        ingredientSet = new IngredientSet(containersArrayList);

        assertThrows(InvalidRequestException.class, () -> {
            ingredientSet.getIngredientIndex(rockyMountainOysters);
            ingredientSet.getIngredientIndex(new IIDContainer("Pork", "Oink Oink", new IIDTag[] { CookState.STEAMED, CutState.SLICED }));
        });

        assertDoesNotThrow(() -> {
            ingredientSet.getIngredientIndex(beef);
            ingredientSet.getIngredientIndex(duck);
            ingredientSet.getIngredientIndex(chicken);
        });
    }

    @Test
    void getIngredientIndexTest() {
        ingredientSet = new IngredientSet(containersArrayList);

        assertAll(() -> {
            assertEquals(0, ingredientSet.getIngredientIndex(beef));
            assertEquals(1, ingredientSet.getIngredientIndex(duck));
            assertEquals(2, ingredientSet.getIngredientIndex(chicken));
        });

        ingredientSet.remove(duck);

        assertAll(() -> {
            assertEquals(0, ingredientSet.getIngredientIndex(beef));
            assertEquals(1, ingredientSet.getIngredientIndex(chicken));
        });
    }

    @Test
    void getIngredientGuardTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ingredientSet.getIngredient(-1);
            ingredientSet.getIngredient(3);
            ingredientSet.getIngredient(4);
        });

        assertDoesNotThrow(() -> {
           ingredientSet.getIngredient(0);
           ingredientSet.getIngredient(1);
            ingredientSet.getIngredient(2);
        });
    }

    @Test
    void getIngredientTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertEquals(containersArray[0], ingredientSet.getIngredient(0));
        assertEquals(containersArray[1], ingredientSet.getIngredient(1));
        assertEquals(containersArray[2], ingredientSet.getIngredient(2));

        IIDContainer[] newContainerArray = {beef, duck, chicken, rockyMountainOysters};
        ingredientSet = new IngredientSet(newContainerArray);

        assertEquals(newContainerArray[0], ingredientSet.getIngredient(0));
        assertEquals(newContainerArray[1], ingredientSet.getIngredient(1));
        assertEquals(newContainerArray[2], ingredientSet.getIngredient(2));
        assertEquals(newContainerArray[3], ingredientSet.getIngredient(3));
    }

    @Test
    void getIngredientsGuardTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ingredientSet.getIngredients(-1, 0, 1 , 2);
            ingredientSet.getIngredients(0, 1, 2, 3);
            ingredientSet.getIngredients(0, 1, 2, 3, 4);
            ingredientSet.getIngredients(-1, 0, 1, 2, 3, 4);
        });

        assertDoesNotThrow(() -> {
           ingredientSet.getIngredients(0);
           ingredientSet.getIngredients(0, 1);
           ingredientSet.getIngredients(0, 1, 2);
        });
    }

    @Test
    void getIngredientsTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(beef)), ingredientSet.getIngredients(0));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, chicken)), ingredientSet.getIngredients(0, 2));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck, chicken)), ingredientSet.getIngredients(0, 1, 2));

        ingredientSet.addIngredient(rockyMountainOysters);

        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), ingredientSet.getIngredients(3));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, rockyMountainOysters)), ingredientSet.getIngredients(0, 3));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, duck, chicken, rockyMountainOysters)), ingredientSet.getIngredients(0, 1, 2, 3));
    }

    @Test
    void removeOneIngredientGuardTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertThrows(InvalidRequestException.class, () -> {
           ingredientSet.remove(rockyMountainOysters);
           ingredientSet.remove(new IIDContainer("Spinach", "Some green veggie", new IIDTag[] {IngredientType.VEGGIE, CookState.STEAMED, CutState.DICED, IngredientUnit.GRAM}));
        });

        assertDoesNotThrow(() -> {
            ingredientSet.remove(beef);
            ingredientSet.remove(duck);
            ingredientSet.remove(chicken);
        });
    }

    @Test
    void removeOneIngredientTest() {
        ingredientSet = new IngredientSet(containersArray);

        ingredientSet.remove(duck);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(beef, chicken)), ingredientSet.getIngredientSet());
        assertEquals(0, ingredientSet.getIngredientIndex(beef));
        assertEquals(1, ingredientSet.getIngredientIndex(chicken));

        ingredientSet.remove(beef);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(chicken)), ingredientSet.getIngredientSet());
        assertEquals(0, ingredientSet.getIngredientIndex(chicken));

        ingredientSet.addIngredient(rockyMountainOysters);
        ingredientSet.remove(chicken);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), ingredientSet.getIngredientSet());
        assertEquals(0, ingredientSet.getIngredientIndex(rockyMountainOysters));
    }

    @Test
    void removeMultipleIngredientsGuardTest() {
        ingredientSet = new IngredientSet(containersArray);

        assertThrows(InvalidRequestException.class, () -> {
           ingredientSet.remove(rockyMountainOysters);
           ingredientSet.remove(spinach);
           ingredientSet.remove(new IIDContainer("John Pork", "Yabbadabbadoo", new IIDTag[] {IngredientType.PROTEIN, CookState.RAW, CutState.WHOLE, IngredientUnit.GRAM}));
        });

        assertDoesNotThrow(() -> {
            ingredientSet.remove(beef, duck);
        });

        ingredientSet.addIngredients(rockyMountainOysters, spinach);

        assertDoesNotThrow(() -> {
            ingredientSet.remove(chicken, rockyMountainOysters);
        });
    }

    @Test
    void removeMultipleIngredientsTest() {
        ingredientSet = new IngredientSet(containersArray);

        ingredientSet.remove(beef, chicken);
        assertEquals(0, ingredientSet.getIngredientIndex(duck));
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(duck)), ingredientSet.getIngredientSet());

        ingredientSet.addIngredients(rockyMountainOysters, spinach);
        ingredientSet.remove(duck, spinach);
        assertEquals(0, ingredientSet.getIngredientIndex(rockyMountainOysters));
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), ingredientSet.getIngredientSet());
    }

    @Test
    void getNameTest() {
        ingredientSet = new IngredientSet("Set One", "Generic Description", containersArray);
        assertEquals("Set One", ingredientSet.getName());

        ingredientSet = new IngredientSet("", "", containersArray);
        assertEquals("", ingredientSet.getName());
    }

    @Test
    void getDescriptionTest() {
        ingredientSet = new IngredientSet("Set One", "Generic Description", containersArray);
        assertEquals("Generic Description", ingredientSet.getDescription());

        ingredientSet = new IngredientSet("", "", containersArray);
        assertEquals("", ingredientSet.getDescription());
    }

    @Test
    void compareTest() {
        IngredientSet setOne = new IngredientSet(containersArray);
        IngredientSet setTwo = new IngredientSet(containersArrayList);

        assertEquals(new ArrayList<IIDContainer>(), setOne.compare(setTwo));

        setTwo = new IngredientSet(containersArrayOf4);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), setOne.compare(setTwo));

        setTwo = new IngredientSet(containersArrayOf5);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(rockyMountainOysters, spinach)), setOne.compare(setTwo));
    }

    @Test
    void getDifferenceSetTest() {
        IngredientSet setOne = new IngredientSet(containersArray);
        IngredientSet setTwo = new IngredientSet(containersArrayList);

        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(), setOne.getDifferenceSet());
        setOne.compare(setTwo).add(rockyMountainOysters);
        assertEquals(new ArrayList<IIDContainer>(), setOne.getDifferenceSet());

        setTwo = new IngredientSet(containersArrayOf4);
        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), setOne.getDifferenceSet());
        setOne.compare(setTwo).add(spinach);
        assertEquals(new ArrayList<IIDContainer>(Collections.singletonList(rockyMountainOysters)), setOne.getDifferenceSet());

        setTwo = new IngredientSet(containersArrayOf5);
        setOne.compare(setTwo);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(rockyMountainOysters, spinach)), setOne.getDifferenceSet());
        setOne.compare(setTwo).add(new IIDContainer("milk", "Got Milk?", new IIDTag[] {IngredientType.DAIRY, CookState.NONE, CutState.NONE, IngredientUnit.MILLILITER}));
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(rockyMountainOysters, spinach)), setOne.getDifferenceSet());
    }

    @Test
    void isEqualTest() {
        IngredientSet setOne = new IngredientSet(containersArray);
        IngredientSet setTwo = new IngredientSet(containersArrayList);

        assertTrue(setOne.isEqual(setTwo));

        setTwo = new IngredientSet(containersArrayOf4);
        assertFalse(setOne.isEqual(setTwo));

        setTwo = new IngredientSet(containersArrayOf5);
        assertFalse(setOne.isEqual(setTwo));

        setTwo = new IngredientSet(chicken, beef, duck);
        assertTrue(setOne.isEqual(setTwo));

        setTwo = new IngredientSet();
        assertFalse(setOne.isEqual(setTwo));
    }

    @Test
    void containsTest(){
        ingredientSet = new IngredientSet(containersArray);

        assertTrue(ingredientSet.contains(beef));
        assertTrue(ingredientSet.contains(duck));
        assertTrue(ingredientSet.contains(chicken));
        IIDContainer otherChicken = new IIDContainer("Chicken", "Some chicken noise", new IIDTag[]{IngredientType.PROTEIN, CookState.STEAMED, CutState.SHREDDED, IngredientUnit.GRAM});
        assertFalse(ingredientSet.contains(otherChicken));
        assertFalse(ingredientSet.contains(rockyMountainOysters));
        assertFalse(ingredientSet.contains(spinach));
    }

    @Test
    void getSizeTest() {
        ingredientSet = new IngredientSet(containersArray);
        assertEquals(3, ingredientSet.getSize());

        ingredientSet.addIngredient(rockyMountainOysters);
        assertEquals(4, ingredientSet.getSize());

        ingredientSet.addIngredient(spinach);
        assertEquals(5, ingredientSet.getSize());

        ingredientSet.remove(beef, chicken, rockyMountainOysters, spinach);
        assertEquals(1, ingredientSet.getSize());
    }
}