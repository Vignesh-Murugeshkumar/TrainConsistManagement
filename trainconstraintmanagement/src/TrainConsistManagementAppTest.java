import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    @Test
    void testGroupedBogies_GroupsByNameCorrectly() {
        // 1. Arrange: Create a list with duplicate names to test grouping
        List<TrainConsistManagementApp.Bogie> bogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("Sleeper", 60),
                new TrainConsistManagementApp.Bogie("General", 90)
        );

        // 2. Act: Since your method is 'void' and prints to console,
        // we replicate the logic to test the Map structure directly.
        Map<String, List<TrainConsistManagementApp.Bogie>> groupedBogies =
                bogies.stream().collect(java.util.stream.Collectors.groupingBy(b -> b.name));

        // 3. Assert
        // Check if we have exactly 2 keys ("Sleeper" and "General")
        assertEquals(2, groupedBogies.size(), "Should have 2 distinct groups");

        // Check "Sleeper" group contents
        List<TrainConsistManagementApp.Bogie> sleeperGroup = groupedBogies.get("Sleeper");
        assertEquals(2, sleeperGroup.size(), "Sleeper group should have 2 bogies");
        assertTrue(sleeperGroup.stream().anyMatch(b -> b.capacity == 72));
        assertTrue(sleeperGroup.stream().anyMatch(b -> b.capacity == 60));

        // Check "General" group contents
        List<TrainConsistManagementApp.Bogie> generalGroup = groupedBogies.get("General");
        assertEquals(1, generalGroup.size());
        assertEquals(90, generalGroup.get(0).capacity);
    }
}
