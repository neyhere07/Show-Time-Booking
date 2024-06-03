package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.FilmMaker;
import com.project.ShowTimeBooking.Entities.Profession;
import com.project.ShowTimeBooking.Enums.ProfessionEnum;
import com.project.ShowTimeBooking.Exceptions.FilmMakerAlreadyPresentException;
import com.project.ShowTimeBooking.Generators.FilmMakerCodeGenerator;
import com.project.ShowTimeBooking.Repository.FilmMakerRepository;
import com.project.ShowTimeBooking.RequestDTO.FilmMakerRequest;
import com.project.ShowTimeBooking.ResponseDTO.FilmMakerResponse;
import com.project.ShowTimeBooking.Service.FilmMakerService;
import com.project.ShowTimeBooking.Service.GeneralService;
import com.project.ShowTimeBooking.Transformers.FilmMakerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmMakerServiceImpl implements FilmMakerService {
    @Autowired
    private FilmMakerRepository filmMakerRepository;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private FilmMakerCodeGenerator filmMakerCodeGenerator;

    public String addFilmMaker(FilmMakerRequest filmMakerRequest, List<ProfessionEnum> professionEnumList)throws Exception{
        Optional<FilmMaker> optionalFilmMaker=filmMakerRepository.findByEmailId(filmMakerRequest.getEmailId());
        if(optionalFilmMaker.isPresent()){
            throw new FilmMakerAlreadyPresentException("Film Maker is already present with this particular email Id "+filmMakerRequest.getEmailId());
        }
        FilmMaker filmMaker= FilmMakerTransformer.filmMakerRequestToFilmMaker(filmMakerRequest);

        List<Profession>professionList=generalService.getProfessionList(professionEnumList);
        String code= filmMakerCodeGenerator.generate("FMK");

        //setting the attributes and foreign keys
        filmMaker.setCode(code);
        filmMaker.setProfessionList(professionList);

        //bidirectionally mapping
        for(Profession profession: professionList){
            profession.getFilmMakerList().add(filmMaker);
        }
        FilmMaker savedFilmMaker=filmMakerRepository.save(filmMaker);
        return savedFilmMaker.getCode();
    }
    public List<FilmMaker> getFilmMaker(List<String>filmMakerCodeList){
        return filmMakerRepository.findByCodeIn(filmMakerCodeList);
    }
    public List<FilmMakerResponse> findFilmMakersByFilmMakerCodeList(List<String>filmMakerCodeList){
        List<FilmMaker>filmMakerList= filmMakerRepository.findByCodeIn(filmMakerCodeList);
        List<FilmMakerResponse>filmMakerResponseList=new ArrayList<>();
        for(FilmMaker filmMaker:filmMakerList){
            filmMakerResponseList.add(FilmMakerTransformer.filmMakerToFilmMakerResponse(filmMaker));
        }
        return filmMakerResponseList;
    }
}
