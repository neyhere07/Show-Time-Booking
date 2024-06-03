package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.UserEmailVerificationCodeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEmailVerificationCodeDetailsRepository extends JpaRepository<UserEmailVerificationCodeDetails, Integer> {
    Optional<UserEmailVerificationCodeDetails> findByEmailId(String email);
}
