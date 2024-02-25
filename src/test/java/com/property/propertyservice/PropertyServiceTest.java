package com.property.propertyservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Test
    public void testGetAllProperties() {

        List<Property> properties = List.of(
                new Property(1L, "123 Test Street", "Test1 Owner", LocalDate.now(), true),
                new Property(2L, "456 X Street", "Test2 Owner", LocalDate.now().minusDays(1), true)
        );
        Mockito.when(propertyRepository.findAll()).thenReturn(properties);

        // Act
        List<Property> result = propertyService.getAllProperties();

        // Assert
        assertEquals(2, result.size());
        assertEquals("123 Test Street", result.get(0).getAddress());
        assertEquals("Test1 Owner", result.get(0).getOwner());
    }

    @Test
    public void testGetAllPropertiesNoData() {
        Mockito.when(propertyRepository.findAll()).thenReturn(Collections.emptyList());
        List<Property> result = propertyService.getAllProperties();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllPropertiesFilteredByOccupied() {
        List<Property> properties = List.of(
                new Property(1L, "123 Test Street", "Test1 Owner", LocalDate.now(), true),
                new Property(2L, "456 X Street", "Test2 Owner", LocalDate.now().minusDays(1), false)
        );
        Mockito.when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> result = propertyService.getAllOccupiedProperties();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(properties.get(0), result.get(0));
    }

    @Test
    public void testGetAllPropertiesOrderedByDate() {
        List<Property> expectedProperties = List.of(
                new Property(1L, "123 Test Street", "Test1 Owner", LocalDate.now(), true),
                new Property(2L, "456 X Street", "Test2 Owner", LocalDate.now().minusDays(1), true),
                new Property(3L, "789 Y Street", "Test3 Owner", LocalDate.now().minusDays(2), true),
                new Property(4L, "222 Z Street", "Test4 Owner", LocalDate.now().minusDays(3), true),
                new Property(5L, "156 T Street", "Test5 Owner", LocalDate.now().minusDays(4), true)
        );

        List<Property> properties = List.of(
                new Property(1L, "123 Test Street", "Test1 Owner", LocalDate.now(), true),
                new Property(4L, "222 Z Street", "Test4 Owner", LocalDate.now().minusDays(3), true),
                new Property(2L, "456 X Street", "Test2 Owner", LocalDate.now().minusDays(1), true),
                new Property(5L, "156 T Street", "Test5 Owner", LocalDate.now().minusDays(4), true),
                new Property(3L, "789 Y Street", "Test3 Owner", LocalDate.now().minusDays(2), true)
        );

        Mockito.when(propertyRepository.findAll()).thenReturn(properties);
        List<Property> result = propertyService.getAllOccupiedProperties();
        int i = 0;
        for (Property p : result) {
            assertEquals(expectedProperties.get(i).getDate(), p.getDate());
            i++;
        }
    }
}