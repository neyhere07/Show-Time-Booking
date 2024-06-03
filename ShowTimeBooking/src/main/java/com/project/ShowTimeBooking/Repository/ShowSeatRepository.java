package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat>findByShowCodeAndShowSeatNoIn(String showCode, List<String> showSeatList);
}
