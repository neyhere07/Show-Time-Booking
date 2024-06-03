package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Theatre;
import com.project.ShowTimeBooking.RequestDTO.TheatreRequest;

public interface TheatreService {
    public String addTheatre(TheatreRequest theatreRequest)throws Exception;
    public String deleteTheatre(String theatreCode)throws Exception;
    public Theatre getTheatreByTheatreCode(String theatreCode)throws Exception;
}
