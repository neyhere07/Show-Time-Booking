package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.Movie;
import com.project.ShowTimeBooking.Enums.FilmCertificationCategory;
import com.project.ShowTimeBooking.RequestDTO.MovieRequest;

public class MovieTransformer {
    public static Movie movieRequestToMovie(MovieRequest movieRequest,
                                            FilmCertificationCategory filmCertificationCategory){
        return Movie.builder()
                .name(movieRequest.getName())
                .releaseDate(movieRequest.getReleaseDate())
                .screenTime(movieRequest.getScreenTime())
                .filmCertificationCategory(filmCertificationCategory)
                .build();
    }
}
