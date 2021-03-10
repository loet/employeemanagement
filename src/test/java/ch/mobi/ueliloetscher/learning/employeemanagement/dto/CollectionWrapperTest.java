package ch.mobi.ueliloetscher.learning.employeemanagement.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class CollectionWrapperTest {

    @Test
    public void testCollection() {
        CollectionWrapper wrapper = new CollectionWrapper(Arrays.asList("one", "two", "three"));
        Collection collection = wrapper.getData();
        assertEquals(3, collection.size());
        assertTrue(collection.contains("one"));
        assertTrue(collection.contains("two"));
        assertTrue(collection.contains("three"));
        assertFalse(collection.contains("four"));
    }
}
