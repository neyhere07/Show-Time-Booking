package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.Format;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormatRepository  extends JpaRepository<Format, Integer> {
    Optional<Format> findByName(FormatEnum formatEnum);
    List<Format> findByNameIn(List<FormatEnum> formatEnumList);
}
