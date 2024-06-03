package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Show;
import com.project.ShowTimeBooking.Enums.City;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.RequestDTO.ShowRequest;
import com.project.ShowTimeBooking.ResponseDTO.TheatreResponseShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowService {
    public String addShow(ShowRequest showRequest)throws Exception;
    public List<TheatreResponseShow> getFilteredTheatreShowResponseList(City city, String movieCode, LanguageEnum languageEnum, FormatEnum formatEnum, LocalDate showDate, LocalTime startTimeRange, LocalTime endTimeRange , String theatreCode);
    public String deleteShow(String showCode)throws Exception;
    public Show getShowByShowCode(String showCode)throws Exception;
}
