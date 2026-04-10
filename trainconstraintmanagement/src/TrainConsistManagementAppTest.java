import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    private List<TrainConsistManagementApp.Bogie> bogies;

    @BeforeEach
    void setUp() {
        // Initialize the standard list used in UC7/UC8
        bogies = new ArrayList<>();
        bogies.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        bogies.add(new TrainConsistManagementApp.Bogie("AC Chair", 56));
        bogies.add(new TrainConsistManagementApp.Bogie("First Class", 24));
        bogies.add(new TrainConsistManagementApp.Bogie("General", 90));
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        // Tests: Bogies with capacity greater than 70 appear in the result
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 70)
                .collect(Collectors.toList());

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(b -> b.name.equals("Sleeper")));
        assertTrue(result.stream().anyMatch(b -> b.name.equals("General")));
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        // Tests: Bogies with capacity equal to 70 are excluded
        bogies.add(new TrainConsistManagementApp.Bogie("Special", 70));
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 70)
                .collect(Collectors.toList());

        assertFalse(result.stream().anyMatch(b -> b.capacity == 70));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        // Tests: Bogies with capacity less than 70 do not appear in result
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 70)
                .collect(Collectors.toList());

        assertTrue(result.stream().allMatch(b -> b.capacity > 70));
        assertFalse(result.stream().anyMatch(b -> b.name.equals("AC Chair"))); // 56 < 70
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        // Tests: All matching bogies are returned (using threshold 50)
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 50)
                .collect(Collectors.toList());

        assertEquals(3, result.size()); // Sleeper, AC Chair, General
    }

    @Test
    void testFilter_NoBogiesMatching() {
        // Tests: The filtered list is empty when no bogies exceed 100
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 100)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        // Tests: Filtered list contains all bogies if threshold is very low
        List<TrainConsistManagementApp.Bogie> result = bogies.stream()
                .filter(b -> b.capacity > 10)
                .collect(Collectors.toList());

        assertEquals(bogies.size(), result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        // Tests: Filtering an empty list returns an empty list
        List<TrainConsistManagementApp.Bogie> emptyList = new ArrayList<>();
        List<TrainConsistManagementApp.Bogie> result = emptyList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        // Tests: Source list size and contents remain identical after processing
        int originalSize = bogies.size();
        bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(originalSize, bogies.size());
        assertEquals("Sleeper", bogies.get(0).name);
    }
}
