import org.junit.jupiter.api.Test;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    static class Bogie {
        String name;
        int capacity;
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
    private List<TrainConsistManagementApp.Bogie> bogies;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    @Test
    void testStreamAggregationBehavior() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70)
        );

        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(222, totalCapacity);
    }

    @Test
    void testCapacityExtractionUsingMap() {
        List<Bogie> bogies = Arrays.asList(new Bogie("Sleeper", 72));
        List<Integer> capacities = bogies.stream()
                .map(b -> b.capacity)
                .collect(Collectors.toList());

        assertEquals(Collections.singletonList(72), capacities);
    }

    @Test
    void testTotalSeatCalculation() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("AC Chair", 56),
                new Bogie("Sleeper", 70)
        );

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(126, total);
    }

    @Test
    void testMultipleBogieAggregation() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 70),
                new Bogie("AC Chair", 56)
        );

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(198, total);
    }

    @Test
    void testSingleBogieHandling() {
        List<Bogie> bogies = Arrays.asList(new Bogie("First Class", 24));

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(24, total);
    }

    @Test
    void testEmptyCollectionHandling() {
        List<Bogie> bogies = new ArrayList<>();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total);
    }

    @Test
    void testOriginalCollectionIntegrity() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );

        List<Bogie> copy = new ArrayList<>(bogies);

        bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(copy.size(), bogies.size());
        assertEquals(copy.get(0).name, bogies.get(0).name);
        assertEquals(copy.get(1).capacity, bogies.get(1).capacity);
    }

    @Test
    void testNumericAggregationValidation() {
        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70)
        );

        int expectedSum = 72 + 56 + 24 + 70;
        int actualSum = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(expectedSum, actualSum);
    }
}