import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // Helper method to perform Bubble Sort (same logic as in main)
    private int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // ✅ Test 1: Basic sorting
    @Test
    void testSort_BasicSorting() {
        int[] input = {72, 56, 24, 70, 60};
        int[] expected = {24, 56, 60, 70, 72};
        assertArrayEquals(expected, bubbleSort(input));
    }

    // ✅ Test 2: Already sorted array
    @Test
    void testSort_AlreadySortedArray() {
        int[] input = {24, 56, 60, 70, 72};
        int[] expected = {24, 56, 60, 70, 72};
        assertArrayEquals(expected, bubbleSort(input));
    }

    // ✅ Test 3: Duplicate values
    @Test
    void testSort_DuplicateValues() {
        int[] input = {72, 56, 56, 24};
        int[] expected = {24, 56, 56, 72};
        assertArrayEquals(expected, bubbleSort(input));
    }

    // ✅ Test 4: Single element array
    @Test
    void testSort_SingleElementArray() {
        int[] input = {50};
        int[] expected = {50};
        assertArrayEquals(expected, bubbleSort(input));
    }

    // ✅ Test 5: All equal values
    @Test
    void testSort_AllEqualValues() {
        int[] input = {40, 40, 40};
        int[] expected = {40, 40, 40};
        assertArrayEquals(expected, bubbleSort(input));
    }
}