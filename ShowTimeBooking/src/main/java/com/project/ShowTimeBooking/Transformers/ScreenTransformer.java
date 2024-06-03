package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.Screen;
import com.project.ShowTimeBooking.RequestDTO.ScreenRequest;

import java.util.ArrayList;

public class ScreenTransformer {
    public static Screen screenRequesToScreen(ScreenRequest screenRequest){
        return Screen.builder()
                .screenNumber(screenRequest.getScreenNumber())
                .noOfSeats(0)
                .screenSeatList(new ArrayList<>())
                .showList(new ArrayList<>())
                .build();
    }
}
