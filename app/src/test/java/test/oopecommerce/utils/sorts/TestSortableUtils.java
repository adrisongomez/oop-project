package test.oopecommerce.utils.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.oopecommerce.utils.sorts.ISortable;
import com.oopecommerce.utils.sorts.SortDirection;
import com.oopecommerce.utils.sorts.SortableUtils;

class TestClass implements ISortable {
    int position;

    public TestClass(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return this.position;
    }
}

public class TestSortableUtils {

    @Test
    public void testSortableUtils() {
        List<TestClass> items = Arrays.asList(
                new TestClass(10),
                new TestClass(0),
                new TestClass(3),
                new TestClass(4),
                new TestClass(6),
                new TestClass(7),
                new TestClass(9),
                new TestClass(2),
                new TestClass(1),
                new TestClass(8),
                new TestClass(5));

        try {
            int[] value = SortableUtils.sortByPosition(items, SortDirection.ASC).stream()
                    .mapToInt(v -> v.getPosition()).toArray();
            int[] expectedValue = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            assertArrayEquals(expectedValue, value);

            int[] descValue = SortableUtils.sortByPosition(items, SortDirection.DESC).stream()
                    .mapToInt(v -> v.getPosition()).toArray();
            int[] expectedDesc = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
            assertArrayEquals(expectedDesc, descValue);
        } catch (Exception error) {
            fail("Error should not be thrown");
        }

    }
}
