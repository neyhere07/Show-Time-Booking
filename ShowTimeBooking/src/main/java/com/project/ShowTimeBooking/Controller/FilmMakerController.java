package com.project.ShowTimeBooking.Controller;

import com.project.ShowTimeBooking.Enums.ProfessionEnum;
import com.project.ShowTimeBooking.RequestDTO.FilmMakerRequest;
import com.project.ShowTimeBooking.Service.FilmMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/film-makers")
public class FilmMakerController {
    @Autowired
    private FilmMakerService filmMakerService;

    @PostMapping("/add-film-maker")
    public ResponseEntity addFilmMaker(@RequestBody FilmMakerRequest filmMakerRequest,
                                       @RequestParam List<ProfessionEnum>professionEnumList){
        try {
            return new ResponseEntity(filmMakerService.addFilmMaker(filmMakerRequest, professionEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-film-maker-list")
    public ResponseEntity getFilmMakersByFilmMakerCodeList(@RequestParam List<String> filmMakerCodeList){
        return new ResponseEntity<>(filmMakerService.findFilmMakersByFilmMakerCodeList(filmMakerCodeList), HttpStatus.ACCEPTED);
    }
}
