package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.ShowSeat;
import com.project.ShowTimeBooking.RequestDTO.ShowSeatRequest;

import java.util.List;

public interface ShowSeatService {
    public String addShowSeats(String showCode, String screenNumber, String theatreCode,  ShowSeatRequest showSeatRequest)throws Exception;
    public String deleteShowSeats(String showCode, List<String> showSeatNumnerList) throws Exception;
    public String deleteShowSeats(List<ShowSeat>showSeatList)throws Exception;
    public List<ShowSeat> findShowSeatsByShowCodeAndShowSeatNoList(String showCode, List<String>showSeatNumberList);
}
