package com.enaa.travelflowbackend.Controller;

import com.enaa.travelflowbackend.Entity.Trip;
import com.enaa.travelflowbackend.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
    @RestController
    @RequestMapping("/api") // Base URL
    public class TripsController {
        @Autowired
        private final TripService tripService;

        public TripsController(TripService tripService) {
            this.tripService = tripService;
        }

        @GetMapping("/trips")
        public List<Trip> getAllTripp(){
            return tripService.getAllTrip();
        }

        @GetMapping("/trips/{id}")
        public Trip GetTrip(@PathVariable long id){
            return tripService.GetTripById(id);
        }
    }





