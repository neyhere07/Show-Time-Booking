package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.Screen;
import com.project.ShowTimeBooking.Entities.ScreenSeat;
import com.project.ShowTimeBooking.Enums.SeatType;

public class ScreenSeatTransformer {
    public static ScreenSeat toTheatreSeat(String seatNo, SeatType seatType, Screen screen){
    return ScreenSeat.builder()
            .screenSeatNumber(seatNo)
            .screenSeatType(seatType)
            .screen(screen)
            .build();
}
}
