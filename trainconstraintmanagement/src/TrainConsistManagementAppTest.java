import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    @Test
    void testCargo_SafeAssignment() {
        assertDoesNotThrow(() -> {
            TrainConsistManagementApp.GoodsBogie bogie =
                    new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum");
            assertEquals("Petroleum", bogie.getCargoType());
        });
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        Exception exception = assertThrows(
                TrainConsistManagementApp.CargoSafetyException.class,
                () -> new TrainConsistManagementApp.GoodsBogie("Rectangular", "Petroleum")
        );
        assertEquals("Unsafe cargo assignment: Petroleum cannot be loaded in Rectangular bogie",
                exception.getMessage());
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        TrainConsistManagementApp.GoodsBogie bogie = null;
        try {
            bogie = new TrainConsistManagementApp.GoodsBogie("Rectangular", "Petroleum");
        } catch (TrainConsistManagementApp.CargoSafetyException e) {
            assertNull(bogie);
        }
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        try {
            new TrainConsistManagementApp.GoodsBogie("Rectangular", "Petroleum");
        } catch (TrainConsistManagementApp.CargoSafetyException e) {
            // handled
        }

        assertDoesNotThrow(() -> {
            TrainConsistManagementApp.GoodsBogie safeBogie =
                    new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum");
            assertEquals("Petroleum", safeBogie.getCargoType());
        });
    }
}