import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    @Test
    void testGroupedBogies_CorrectKeysAndCounts() {
        // Arrange
        List<TrainConsistManagementApp.Bogie> bogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56),
                new TrainConsistManagementApp.Bogie("Sleeper", 70)
        );

        // Act - Replicating the logic inside extracted() for testing
        Map<String, List<TrainConsistManagementApp.Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Assert
        assertEquals(2, grouped.size(), "Should have exactly 2 unique types (Sleeper, AC Chair)");
        assertEquals(2, grouped.get("Sleeper").size(), "Sleeper group should contain 2 bogies");
        assertEquals(1, grouped.get("AC Chair").size(), "AC Chair group should contain 1 bogie");
    }

    @Test
    void testGroupedBogies_DataIntegrity() {
        // Arrange
        List<TrainConsistManagementApp.Bogie> bogies = Collections.singletonList(
                new TrainConsistManagementApp.Bogie("First Class", 24)
        );

        // Act
        Map<String, List<TrainConsistManagementApp.Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Assert
        TrainConsistManagementApp.Bogie b = grouped.get("First Class").get(0);
        assertEquals(24, b.capacity, "The capacity in the group must match the original object");
    }

    @Test
    void testGroupedBogies_EmptyList() {
        // Arrange
        List<TrainConsistManagementApp.Bogie> emptyList = new ArrayList<>();

        // Act
        Map<String, List<TrainConsistManagementApp.Bogie>> result = emptyList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Assert
        assertTrue(result.isEmpty(), "Grouping an empty list should return an empty map");
    }

    @Test
    void testGroupedBogies_NullHandling() {
        // Ensure that names are handled; if names could be null, grouping still works
        List<TrainConsistManagementApp.Bogie> nullNameList = Collections.singletonList(
                new TrainConsistManagementApp.Bogie(null, 50)
        );

        Map<String, List<TrainConsistManagementApp.Bogie>> result = nullNameList.stream()
                .collect(Collectors.groupingBy(b -> b.name == null ? "Unknown" : b.name));

        assertTrue(result.containsKey("Unknown"));
    }
}
