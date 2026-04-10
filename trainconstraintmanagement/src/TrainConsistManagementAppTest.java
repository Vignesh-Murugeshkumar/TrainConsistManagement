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
    }
}