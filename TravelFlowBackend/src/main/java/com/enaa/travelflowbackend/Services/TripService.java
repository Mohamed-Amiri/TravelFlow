package com.enaa.travelflowbackend.Services;

import com.enaa.travelflowbackend.Entity.Trip;
import com.enaa.travelflowbackend.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private  TripRepository tripRepository;

    public List <Trip> getAllTrip(){
        return tripRepository.findAll();
    }

    public Trip GetTripById(long id){
        return tripRepository.findById(id).orElse(null);
    }
}
