package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.Profession;
import com.project.ShowTimeBooking.Enums.ProfessionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
    Optional<Profession> findByName(ProfessionEnum professionEnum);
    List<Profession> findByNameIn(List<ProfessionEnum> professionEnumList);
}
