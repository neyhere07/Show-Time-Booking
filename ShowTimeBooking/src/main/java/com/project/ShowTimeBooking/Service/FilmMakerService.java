package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.FilmMaker;
import com.project.ShowTimeBooking.Enums.ProfessionEnum;
import com.project.ShowTimeBooking.RequestDTO.FilmMakerRequest;
import com.project.ShowTimeBooking.ResponseDTO.FilmMakerResponse;

import java.util.List;

public interface FilmMakerService {
    public String addFilmMaker(FilmMakerRequest filmMakerRequest, List<ProfessionEnum> professionEnumList)throws Exception;
    public List<FilmMaker> getFilmMaker(List<String>filmMakerCodeList);
    public List<FilmMakerResponse> findFilmMakersByFilmMakerCodeList(List<String>filmMakerCodeList);
}
