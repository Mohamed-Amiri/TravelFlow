package com.enaa.travelflowbackend.Repository;

import com.enaa.travelflowbackend.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository <Trip , Long> {

}
