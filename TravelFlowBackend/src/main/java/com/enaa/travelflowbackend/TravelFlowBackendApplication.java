package com.enaa.travelflowbackend;

import com.enaa.travelflowbackend.Entity.Trip;
import com.enaa.travelflowbackend.Repository.TripRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelFlowBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelFlowBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner seedTrips(TripRepository tripRepository) {
        return args -> {
            if (tripRepository.count() == 0) {
                tripRepository.save(new Trip("Marrakech", "2025-11-10", 1999));
                tripRepository.save(new Trip("Chefchaouen", "2025-12-05", 1499));
                tripRepository.save(new Trip("Merzouga", "2026-01-20", 2299));
            }
        };
    }

}
