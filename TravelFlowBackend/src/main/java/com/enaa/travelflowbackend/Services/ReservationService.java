package com.enaa.travelflowbackend.Services;

import com.enaa.travelflowbackend.Entity.Reservation;
import com.enaa.travelflowbackend.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    public ReservationRepository reservationRepository;


    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }
    public Reservation SaveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    public void DeletReservation(long id){
        reservationRepository.deleteById(id);
    }
}
