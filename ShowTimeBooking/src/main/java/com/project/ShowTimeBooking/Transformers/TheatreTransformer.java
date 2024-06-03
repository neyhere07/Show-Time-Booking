package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.Address;
import com.project.ShowTimeBooking.Entities.Theatre;
import com.project.ShowTimeBooking.RequestDTO.TheatreRequest;
import com.project.ShowTimeBooking.ResponseDTO.ShowResponseTheatre;
import com.project.ShowTimeBooking.ResponseDTO.TheatreResponseShow;
import com.project.ShowTimeBooking.Service.impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TheatreTransformer {
    @Autowired
    private TheatreServiceImpl theatreServiceImpl;
    public static Theatre theatreRequestToTheatre(TheatreRequest theatreRequest){
        return Theatre.builder()
                .name(theatreRequest.getTheatreName())
                .numberOfScreens(0)
                .screens(new ArrayList<>())
                .build();
        //need to add screen's list and vice versa as part of the bidirectional mapping
    }
    public static TheatreResponseShow theatreAndAddressToTheatreResponseShow(String theatreName, Address address, List<ShowResponseTheatre> showResponseTheatreList){
        return TheatreResponseShow.builder()
                .theatreName(theatreName)
                .locality(address.getLocality())
                .city(address.getCity().toString())
                .theatreLocationUrl(address.getLocationUrl())
                .showResponseTheatreList(showResponseTheatreList)
                .build();
    }
}
