package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.RequestDTO.ScreenSeatRequest;
import com.project.ShowTimeBooking.RequestDTO.ScreenSeatRequestAddl;

import java.util.List;

public interface ScreenSeatService {
    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequest>screenSeatRequestList, List<String> pdSeatList)throws Exception;
    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequestAddl>screenSeatList)throws Exception;
    public String deleteScreenSeat(String theatreCode, String screenNumber, String screenSeatNumber)throws Exception;
}
