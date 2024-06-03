package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.ScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreSeatRepository extends JpaRepository<ScreenSeat, Integer> {
}
