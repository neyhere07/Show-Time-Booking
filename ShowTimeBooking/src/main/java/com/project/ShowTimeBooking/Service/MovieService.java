package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Movie;
import com.project.ShowTimeBooking.Enums.FilmCertificationCategory;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.GenreEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.RequestDTO.MovieRequest;

import java.util.List;

public interface MovieService {
    public String addMovie(MovieRequest movieRequest, FilmCertificationCategory filmCertificationCategory,
                           List<FormatEnum> formatList, List<LanguageEnum> languagesReleasedIn,
                           List<GenreEnum> movieGenreEnum)throws Exception;
    public Movie getMovieByMovieCode(String movieCode)throws Exception;
}
