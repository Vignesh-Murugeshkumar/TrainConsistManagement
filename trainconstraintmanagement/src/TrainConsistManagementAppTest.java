import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    @Test
    void testGroupedBogies_CorrectKeysAndCounts() {
        // Arrange
        List<TrainConsistManagementApp.Bogie> testBogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56),
                new TrainConsistManagementApp.Bogie("Sleeper", 70)
        );

        // Act - Use the public static method from the App class
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.getGroupedBogies(testBogies);

        // Assert
        assertEquals(2, result.size(), "Should have 2 unique groups");
        assertEquals(2, result.get("Sleeper").size(), "Sleeper group count mismatch");
        assertEquals(1, result.get("AC Chair").size());
    }
}
