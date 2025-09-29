package com.enaa.travelflowbackend.Controller;


import com.enaa.travelflowbackend.Entity.Reservation;
import com.enaa.travelflowbackend.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    @GetMapping("/reservations")
    public List<Reservation> GetReservation(){
        return reservationService.getAllReservation();
    }

    @PostMapping("/reservations")
    public ResponseEntity <Reservation> SaveReservation(@RequestBody Reservation reservation){
        Reservation saved = reservationService.SaveReservation(reservation);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/reservations/{id}")
    public Reservation UpdatesReservation(@PathVariable long id , @RequestBody Reservation reservation){
        reservation.setId(id);
        return reservationService.SaveReservation(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public void DeletReservation(@PathVariable long id){
        reservationService.DeletReservation(id);
    }

}
