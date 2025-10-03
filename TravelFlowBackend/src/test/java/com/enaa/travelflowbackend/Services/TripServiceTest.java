package com.enaa.travelflowbackend.Services;

import com.enaa.travelflowbackend.Entity.Trip;
import com.enaa.travelflowbackend.Repository.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    private Trip testTrip1;
    private Trip testTrip2;

    @BeforeEach
    void setUp() {
        testTrip1 = new Trip(1L, "Paris", "2024-12-01", 500.0);
        testTrip2 = new Trip(2L, "London", "2024-12-15", 400.0);
    }

    @Test
    void getAllTrip_ShouldReturnAllTrips() {
        List<Trip> expectedTrips = Arrays.asList(testTrip1, testTrip2);
        when(tripRepository.findAll()).thenReturn(expectedTrips);

        List<Trip> actualTrips = tripService.getAllTrip();

        assertEquals(2, actualTrips.size());
        assertEquals("Paris", actualTrips.get(0).getDestination());
        assertEquals("London", actualTrips.get(1).getDestination());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    void getAllTrip_ShouldReturnEmptyList_WhenNoTripsExist() {
        when(tripRepository.findAll()).thenReturn(Arrays.asList());

        List<Trip> actualTrips = tripService.getAllTrip();

        assertTrue(actualTrips.isEmpty());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    void getTripById_ShouldReturnTrip_WhenTripExists() {
        when(tripRepository.findById(1L)).thenReturn(Optional.of(testTrip1));

        Trip actualTrip = tripService.GetTripById(1L);

        assertNotNull(actualTrip);
        assertEquals(1L, actualTrip.getId());
        assertEquals("Paris", actualTrip.getDestination());
        assertEquals(500.0, actualTrip.getPrice());
        verify(tripRepository, times(1)).findById(1L);
    }

    @Test
    void getTripById_ShouldReturnNull_WhenTripDoesNotExist() {
        
        when(tripRepository.findById(999L)).thenReturn(Optional.empty());

     
        Trip actualTrip = tripService.GetTripById(999L);

       
        assertNull(actualTrip);
        verify(tripRepository, times(1)).findById(999L);
    }
}