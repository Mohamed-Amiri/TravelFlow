package com.enaa.travelflowbackend.Repository;
import com.enaa.travelflowbackend.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation , Long> {

}
