package com.project.ShowTimeBooking.Repository;

import com.project.ShowTimeBooking.Entities.Language;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByName(LanguageEnum languageEnum);
    List<Language> findByNameIn(List<LanguageEnum> languageEnumList);
}
