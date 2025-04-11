import Interfaces.TagDataInterface;
import Tags.CookState;
import Tags.CutState;
import Tags.IngredientType;
import Tags.IngredientUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TagsTest {

    @Test
    public void cookStateEqualityCheck() {
        CookState cookStateEnum = CookState.RAW;
        assertAll(() -> {
            assertTrue(cookStateEnum.isSameTagType(CookState.class));
            assertFalse(cookStateEnum.isSameTagType(CutState.class));
            assertTrue(cookStateEnum.isSameTag(CookState.RAW));
            assertFalse(cookStateEnum.isSameTag(CookState.BAKED));
            assertFalse(cookStateEnum.isSameTag(CutState.WHOLE));
        });
    }

    @Test
    public void cutStateEqualityCheck() {
        CutState cutStateEnum = CutState.WHOLE;
        assertAll(() -> {
            assertEquals(cutStateEnum.getClass(), CutState.class);
            assertTrue(cutStateEnum.isSameTagType(CutState.class));
            assertNotEquals(cutStateEnum.getClass(), CookState.class);
            assertFalse(cutStateEnum.isSameTagType(CookState.class));
            assertTrue(cutStateEnum.isSameTag(CutState.WHOLE));
            assertFalse(cutStateEnum.isSameTag(CutState.GROUND));
            assertFalse(cutStateEnum.isSameTag(CookState.RAW));
        });
    }

    @Test
    public void IngredientTypeEqualityCheck() {
        IngredientType ingredientEnum = IngredientType.NUT;
        assertAll(() -> {
            assertEquals(IngredientType.class, ingredientEnum.getClass());
            assertNotEquals(TagDataInterface.class, ingredientEnum.getClass());
            assertTrue(ingredientEnum.isSameTagType(IngredientType.class));
            assertFalse(ingredientEnum.isSameTagType(CutState.class));
            assertTrue(ingredientEnum.isSameTag(IngredientType.NUT));
            assertFalse(ingredientEnum.isSameTag(IngredientType.FAT));
            assertFalse(ingredientEnum.isSameTag(CutState.DICED));
        });
    }

    @Test
    public void IngredientUnitEqualityCheck() {
        IngredientUnit unitEnum = IngredientUnit.UNIT;
        assertAll(() -> {
            assertEquals(IngredientUnit.class, unitEnum.getClass());
            assertNotEquals(CutState.class, unitEnum.getClass());
            assertTrue(unitEnum.isSameTagType(IngredientUnit.class));
            assertFalse(unitEnum.isSameTagType(CookState.class));
            assertTrue(unitEnum.isSameTag(IngredientUnit.UNIT));
            assertFalse(unitEnum.isSameTag(IngredientUnit.GRAM));
            assertFalse(unitEnum.isSameTag(CookState.FRIED));
        });
    }

}
