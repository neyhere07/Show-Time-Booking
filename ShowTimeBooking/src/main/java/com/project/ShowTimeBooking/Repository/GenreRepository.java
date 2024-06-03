package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.Genre;
import com.project.ShowTimeBooking.Enums.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(GenreEnum genreEnum);
    List<Genre> findByNameIn(List<GenreEnum>genreEnumList);
}
