import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TrainConsistManagementTest {

    @Test
    void testLoopFilteringLogic() {
        List<TrainConsistManagementApp.Bogie> bogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56)
        );
        List<TrainConsistManagementApp.Bogie> result = TrainConsistManagementApp.filterWithLoop(bogies);
        assertEquals(1, result.size());
        assertEquals("Sleeper", result.get(0).name);
    }

    @Test
    void testStreamFilteringLogic() {
        List<TrainConsistManagementApp.Bogie> bogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56)
        );
        List<TrainConsistManagementApp.Bogie> result = TrainConsistManagementApp.filterWithStream(bogies);
        assertEquals(1, result.size());
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<TrainConsistManagementApp.Bogie> bogies = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56),
                new TrainConsistManagementApp.Bogie("General", 90)
        );
        assertEquals(TrainConsistManagementApp.filterWithLoop(bogies).size(),
                TrainConsistManagementApp.filterWithStream(bogies).size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long start = System.nanoTime();
        // Small operation
        Math.pow(10, 10);
        long end = System.nanoTime();
        assertTrue((end - start) > 0, "Elapsed time should be positive");
    }

    @Test
    void testLargeDatasetProcessing() {
        List<TrainConsistManagementApp.Bogie> largeList = new ArrayList<>();
        for(int i=0; i<5000; i++) largeList.add(new TrainConsistManagementApp.Bogie("Test", 80));

        List<TrainConsistManagementApp.Bogie> result = TrainConsistManagementApp.filterWithStream(largeList);
        assertEquals(5000, result.size());
    }
}
