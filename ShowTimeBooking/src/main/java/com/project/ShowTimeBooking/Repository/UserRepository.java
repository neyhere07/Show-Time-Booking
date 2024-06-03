package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailId(String email);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM User WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
}
