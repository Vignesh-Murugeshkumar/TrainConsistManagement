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

class TrainConsistManagementAppTest {

    // Regex patterns
    private final Pattern trainIdPattern = Pattern.compile("^TRN-\\d{4}$");
    private final Pattern cargoCodePattern = Pattern.compile("^[A-Z]{3}-[A-Z]{2}$");

    @Test
    void testRegexValidationBehavior() {
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        assertTrue(trainIdPattern.matcher(trainId).matches());
        assertTrue(cargoCodePattern.matcher(cargoCode).matches());
    }

    @Test
    void testTrainIdFormatValidation() {
        assertTrue(trainIdPattern.matcher("TRN-0001").matches());
        assertFalse(trainIdPattern.matcher("TRN1234").matches());
        assertFalse(trainIdPattern.matcher("TRN-12A4").matches());
        assertFalse(trainIdPattern.matcher("TRN-12345").matches());
    }

    @Test
    void testCargoCodeFormatValidation() {
        assertTrue(cargoCodePattern.matcher("PET-XY").matches());
        assertFalse(cargoCodePattern.matcher("PET-xyz").matches());
        assertFalse(cargoCodePattern.matcher("PET123").matches());
        assertFalse(cargoCodePattern.matcher("PET-123").matches());
    }

    @Test
    void testValidInputHandling() {
        assertTrue(trainIdPattern.matcher("TRN-6524").matches());
        assertTrue(cargoCodePattern.matcher("PET-FH").matches());
    }

    @Test
    void testInvalidInputDetection() {
        assertFalse(trainIdPattern.matcher("TRN-65").matches());
        assertFalse(cargoCodePattern.matcher("pet-ab").matches());
    }

    @Test
    void testExactPatternMatching() {
        assertTrue(trainIdPattern.matcher("TRN-9999").matches());
        assertFalse(trainIdPattern.matcher("TRN-9999 ").matches()); // trailing space
        assertTrue(cargoCodePattern.matcher("PET-ZZ").matches());
        assertFalse(cargoCodePattern.matcher(" PET-ZZ").matches()); // leading space
    }

    @Test
    void testCaseSensitivityValidation() {
        assertTrue(cargoCodePattern.matcher("PET-AB").matches());
        assertFalse(cargoCodePattern.matcher("pet-ab").matches());
    }

    @Test
    void testOriginalInputIntegrity() {
        String inputTrainId = "TRN-1234";
        String inputCargoCode = "PET-AB";

        trainIdPattern.matcher(inputTrainId).matches(); // validate
        cargoCodePattern.matcher(inputCargoCode).matches(); // validate

        assertEquals("TRN-1234", inputTrainId);
        assertEquals("PET-AB", inputCargoCode);
    }
}