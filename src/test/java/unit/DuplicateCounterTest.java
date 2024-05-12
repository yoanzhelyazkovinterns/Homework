package unit;

import org.example.Main;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateCounterTest {

    @Test
    public void testCountDuplicates() {
        // First sample
        var firstSampleNumbers = Arrays.asList(8, 1, 3, 1, 4, 5, 6, 3, 2);

        assertEquals(2, Main.countDuplicates(firstSampleNumbers));

        // Second sample
        var secondSampleNumbers = Arrays.asList(6, 1, 1, 2, 2, 2, 3);

        assertEquals(2, Main.countDuplicates(secondSampleNumbers));
    }
}
