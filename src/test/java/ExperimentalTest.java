import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExperimentalTest {

    @Test
    public void testTesterTest() {

        Experimental exp = new Experimental();
        assertAll(() -> {
            assertEquals(exp.testTester(1), "greater than 0");
            assertEquals(exp.testTester(0), "less than or equal to 0");
            assertEquals(exp.testTester(-1), "less than or equal to 0");
        });

    }
}