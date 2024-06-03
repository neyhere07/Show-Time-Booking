package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.ShowSeat;
import com.project.ShowTimeBooking.Enums.SeatType;
import com.project.ShowTimeBooking.ResponseDTO.ShowSeatResponse;

public class ShowSeatTransformer {
    public static ShowSeat showSeatRequestToShowSeat(String screenSeatNo, SeatType screenSeatType, int cost, boolean isAvailable, boolean isFoodAttached){
        return ShowSeat.builder()
                .showSeatNo(screenSeatNo)
                .showSeatType(screenSeatType)
                .cost(cost)
                .isAvailable(isAvailable)
                .isFoodAttached(isFoodAttached)
                .build();
    }
    public static ShowSeatResponse showSeatToShowSeatResponse(ShowSeat showSeat){
        return ShowSeatResponse.builder()
                .showSeatNo(showSeat.getShowSeatNo())
                .showSeatType(showSeat.getShowSeatType().getDisplayName())
                .cost(showSeat.getCost())
                .isAvailable(showSeat.isAvailable())
                .isFoodAttached(showSeat.isFoodAttached())
                .build();
    }
}
