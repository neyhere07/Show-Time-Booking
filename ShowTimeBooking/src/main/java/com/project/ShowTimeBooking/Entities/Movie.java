package com.project.ShowTimeBooking.Entities;

import com.project.ShowTimeBooking.Enums.FilmCertificationCategory;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"formatList", "languagesReleasedIn", "genreList", "showList", "filmMakersList"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    LocalDate releaseDate;
    @Column(nullable = false)
    LocalTime screenTime;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Format> formatList;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    FilmCertificationCategory filmCertificationCategory;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Language> languagesReleasedIn;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Genre> genreList;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<Show>showList;
    @ManyToMany
    List<FilmMaker>filmMakersList;
    public boolean hasLanguage(LanguageEnum languageEnum){
        for(Language language:languagesReleasedIn){
            if(language.getName()==languageEnum)return true;
        }
        return false;
    }
    public boolean hasFormat(FormatEnum formatEnum){
        for(Format format: formatList){
            if(format.getName()==formatEnum)return true;
        }
        return false;
    }
}
