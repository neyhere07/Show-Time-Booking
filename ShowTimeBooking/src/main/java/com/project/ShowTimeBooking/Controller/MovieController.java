package com.project.ShowTimeBooking.Controller;

import com.project.ShowTimeBooking.Enums.FilmCertificationCategory;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.GenreEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.RequestDTO.MovieRequest;
import com.project.ShowTimeBooking.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody MovieRequest movieRequest,
                                   @RequestParam FilmCertificationCategory filmCertificationCategory,
                                   @RequestParam List<FormatEnum> formatList,
                                   @RequestParam List<LanguageEnum> languagesReleasedIn,
                                   @RequestParam List<GenreEnum> movieGenreEnum){
        try {
            return new ResponseEntity<>(movieService.addMovie(movieRequest, filmCertificationCategory, formatList, languagesReleasedIn, movieGenreEnum), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
