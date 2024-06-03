package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.FilmMaker;
import com.project.ShowTimeBooking.RequestDTO.FilmMakerRequest;
import com.project.ShowTimeBooking.ResponseDTO.FilmMakerResponse;
import org.springframework.web.bind.annotation.RequestBody;

public class FilmMakerTransformer {
    public static FilmMaker filmMakerRequestToFilmMaker(@RequestBody FilmMakerRequest filmMakerRequest){
        return FilmMaker.builder()
                .name(filmMakerRequest.getName())
                .dateOfBirth(filmMakerRequest.getDateOfBirth())
                .birthPlace(filmMakerRequest.getBirthPlace())
                .emailId(filmMakerRequest.getEmailId())
                .build();
    }
    public static FilmMakerResponse filmMakerToFilmMakerResponse(FilmMaker filmMaker){
        return FilmMakerResponse.builder()
                .code(filmMaker.getCode())
                .name(filmMaker.getName())
                .dateOfBirth(filmMaker.getDateOfBirth())
                .birthPlace(filmMaker.getBirthPlace())
                .build();
    }
}
