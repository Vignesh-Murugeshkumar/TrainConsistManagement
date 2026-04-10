import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    @Test
    void testException_ValidCapacityCreation() {
        // Verifies successful instantiation
        assertDoesNotThrow(() -> {
            new TrainConsistManagementApp.Bogie("Sleeper", 72);
        });
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        // Verifies negative capacity triggers exception
        assertThrows(InvalidCapacityException.class, () -> {
            new TrainConsistManagementApp.Bogie("AC Chair", -10);
        });
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        // Verifies zero capacity triggers exception
        assertThrows(InvalidCapacityException.class, () -> {
            new TrainConsistManagementApp.Bogie("First Class", 0);
        });
    }

    @Test
    void testException_ExceptionMessageValidation() {
        // Verifies the error message text
        InvalidCapacityException exception = assertThrows(InvalidCapacityException.class, () -> {
            new TrainConsistManagementApp.Bogie("General", -5);
        });
        assertEquals("Capacity must be greater than zero", exception.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws InvalidCapacityException {
        // Verifies properties are stored correctly
        TrainConsistManagementApp.Bogie bogie = new TrainConsistManagementApp.Bogie("Sleeper", 72);
        assertEquals("Sleeper", bogie.name);
        assertEquals(72, bogie.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() {
        // Verifies multiple successes
        assertDoesNotThrow(() -> {
            new TrainConsistManagementApp.Bogie("B1", 10);
            new TrainConsistManagementApp.Bogie("B2", 20);
            new TrainConsistManagementApp.Bogie("B3", 30);
        });
    }
}
