package com.enaa.travelflowbackend.Services;

import com.enaa.travelflowbackend.Entity.Reservation;
import com.enaa.travelflowbackend.Entity.Trip;
import com.enaa.travelflowbackend.Entity.Client;
import com.enaa.travelflowbackend.Repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation testReservation1;
    private Reservation testReservation2;

    @BeforeEach
    void setUp() {
        testReservation1 = new Reservation(1L, LocalDate.of(2024, 12, 1));
        testReservation2 = new Reservation(2L, LocalDate.of(2024, 12, 15));
    }

    @Test
    void getAllReservation_ShouldReturnAllReservations() {
        List<Reservation> expectedReservations = Arrays.asList(testReservation1, testReservation2);
        when(reservationRepository.findAll()).thenReturn(expectedReservations);

        List<Reservation> actualReservations = reservationService.getAllReservation();

        assertEquals(2, actualReservations.size());
        assertEquals(LocalDate.of(2024, 12, 1), actualReservations.get(0).getReservationDate());
        assertEquals(LocalDate.of(2024, 12, 15), actualReservations.get(1).getReservationDate());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void getAllReservation_ShouldReturnEmptyList_WhenNoReservationsExist() {
        when(reservationRepository.findAll()).thenReturn(Arrays.asList());

        
        List<Reservation> actualReservations = reservationService.getAllReservation();

        
        assertTrue(actualReservations.isEmpty());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void saveReservation_ShouldReturnSavedReservation() {
        
        Reservation reservationToSave = new Reservation(null, LocalDate.of(2024, 12, 20));
        Reservation savedReservation = new Reservation(3L, LocalDate.of(2024, 12, 20));
        when(reservationRepository.save(reservationToSave)).thenReturn(savedReservation);

       
        Reservation actualReservation = reservationService.SaveReservation(reservationToSave);

        
        assertNotNull(actualReservation);
        assertEquals(3L, actualReservation.getId());
        assertEquals(LocalDate.of(2024, 12, 20), actualReservation.getReservationDate());
        verify(reservationRepository, times(1)).save(reservationToSave);
    }

    @Test
    void saveReservation_ShouldHandleNullReservation() {
       
        Reservation nullReservation = null;
        when(reservationRepository.save(nullReservation)).thenReturn(null);

        
        Reservation actualReservation = reservationService.SaveReservation(nullReservation);

        
        assertNull(actualReservation);
        verify(reservationRepository, times(1)).save(nullReservation);
    }

    @Test
    void deleteReservation_ShouldCallRepositoryDelete() {
       
        Long reservationId = 1L;

        
        reservationService.DeletReservation(reservationId);

        
        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void deleteReservation_ShouldHandleNonExistentId() {
        
        Long nonExistentId = 999L;
        doNothing().when(reservationRepository).deleteById(nonExistentId);

        
        assertDoesNotThrow(() -> reservationService.DeletReservation(nonExistentId));

        
        verify(reservationRepository, times(1)).deleteById(nonExistentId);
    }
}