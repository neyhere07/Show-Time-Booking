package com.project.ShowTimeBooking.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MovieResponse {
    String movieName;
    LocalDate releaseDate;
    LocalTime screenTime;
    String format;
    String filmCertificationCategory;
    List<LanguageResponse> languagesReleasedIn;
    List<GenreResponse> genreResponseList;
    List<FilmMakerResponse> filmMakerResponseList;
}
