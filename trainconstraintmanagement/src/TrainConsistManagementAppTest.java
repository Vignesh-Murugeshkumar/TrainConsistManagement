import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    // Safety rule: Cylindrical bogies must carry only Petroleum
    private boolean isTrainFormationSafe(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !(b.type.equals("Cylindrical") && !b.cargo.equals("Petroleum")));
    }

    @Test
    void testStreamSafetyValidationBehavior() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain"),
                new GoodsBogie("Cylindrical", "Coal") // violates rule
        );

        boolean result = isTrainFormationSafe(bogies);
        assertFalse(result);
    }

    @Test
    void testCylindricalBogieCargoRule() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Petroleum")
        );

        assertTrue(isTrainFormationSafe(bogies));

        bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Grain") // invalid
        );

        assertFalse(isTrainFormationSafe(bogies));
    }

    @Test
    void testValidTrainFormation() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain"),
                new GoodsBogie("Flat", "Machinery")
        );

        assertTrue(isTrainFormationSafe(bogies));
    }

    @Test
    void testEmptyTrainFormationIsSafe() {
        List<GoodsBogie> bogies = new ArrayList<>();
        assertTrue(isTrainFormationSafe(bogies));
    }

    @Test
    void testMixedBogiesWithValidCylindricalCargo() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain")
        );

        assertTrue(isTrainFormationSafe(bogies));
    }

    @Test
    void testMixedBogiesWithInvalidCylindricalCargo() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Coal"), // invalid
                new GoodsBogie("Box", "Grain")
        );

        assertFalse(isTrainFormationSafe(bogies));
import java.util.regex.Pattern;
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
