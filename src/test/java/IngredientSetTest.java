import Exceptions.InvalidRequestException;
import Interfaces.IIDTag;
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
    private IIDContainer[] containersArray = {beef, duck, chicken};
    private ArrayList<IIDContainer> containersArrayList = new ArrayList<>(Arrays.asList(containersArray));

    // TODO Test modCounts in the constructor tests?
    @Test
    void ingredientSetWithNoParametersTest() {
        ingredientSet = new IngredientSet();

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(0, ingredientSet.getSize());
        });

        ingredientSet.addIngredient(beef);

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(1, ingredientSet.getSize());
            assertEquals(beef, ingredientSet.getIngredient(0));
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

        ingredientSet.addIngredient(rockyMountainOysters);

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(4, ingredientSet.getSize());
            assertEquals(rockyMountainOysters, ingredientSet.getIngredient(3));
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

        ingredientSet.addIngredient(rockyMountainOysters);

        assertAll(() -> {
            assertNull(ingredientSet.getName());
            assertNull(ingredientSet.getDescription());
            assertEquals(4, ingredientSet.getSize());
            assertEquals(rockyMountainOysters, ingredientSet.getIngredient(3));
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

        ingredientSet.addIngredient(rockyMountainOysters);

        assertAll(() -> {
            assertEquals(4, ingredientSet.getSize());
            assertEquals(rockyMountainOysters, ingredientSet.getIngredient(3));
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

        ingredientSet.addIngredient(rockyMountainOysters);

        assertAll(() -> {
            assertEquals(4, ingredientSet.getSize());
            assertEquals(rockyMountainOysters, ingredientSet.getIngredient(3));
        });
    }

    @Test
    void addIngredientTest() {
        ingredientSet = new IngredientSet();

        ingredientSet.addIngredient(chicken);

        assertEquals(chicken, ingredientSet.getIngredient(0));
        assertEquals(1, ingredientSet.getSize());


    }

    @Test
    void getIngredientSetTest() {
        ingredientSet = new IngredientSet();

        assertEquals(new ArrayList<IIDContainer>(), ingredientSet.getIngredientSet());
        assertEquals(0, ingredientSet.getSize());

        ingredientSet = new IngredientSet(containersArray);
        assertEquals(containersArrayList, ingredientSet.getIngredientSet());

        ingredientSet.remove(beef);
        assertEquals(new ArrayList<IIDContainer>(Arrays.asList(new IIDContainer[]{duck, chicken})), ingredientSet.getIngredientSet());
    }

    @Test
    void getIngredientIndexGuardTest() {
        ingredientSet = new IngredientSet(containersArrayList);

        assertThrows(InvalidRequestException.class, () -> {
            ingredientSet.getIngredientIndex(rockyMountainOysters);
            ingredientSet.getIngredientIndex(new IIDContainer("Pork", "Oink Oink", new IIDTag[] { CookState.STEAMED, CutState.SLICED }));
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

    }

    @Test
    void getIngredientTest() {
    }

    @Test
    void getIngredientsGuardTest() {

    }

    @Test
    void getIngredientsTest() {

    }

    @Test
    void removeOneIngredientTest() {

    }

    @Test
    void removeMultipleIngredientsTest() {

    }

    @Test
    void getNameTest() {

    }

    @Test
    void getDescriptionTest() {

    }

    @Test
    void compareTest() {

    }

    @Test
    void getDifferenceSetTest() {

    }

    @Test
    void isEqualTest() {

    }

    @Test
    void containsTest(){

    }

    @Test
    void getSizeTest() {

    }
}