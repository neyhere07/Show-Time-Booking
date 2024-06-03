package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Screen;
import com.project.ShowTimeBooking.RequestDTO.ScreenRequest;

import java.util.List;

public interface ScreenService {
    public String addScreens(String theatreCode, List<ScreenRequest> screenRequestList)throws Exception;
    public String deleteScreen(String theatreCode, String screenNumber)throws Exception;
    public Screen getScreenByTheatreCodeAndScreenNumber(String theatreCode, String screenNumber)throws Exception;
}
