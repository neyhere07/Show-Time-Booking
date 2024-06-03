package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {
    Optional<Screen> findByTheatreCodeAndScreenNumber(String theatreCode, String screenNumber);
}
