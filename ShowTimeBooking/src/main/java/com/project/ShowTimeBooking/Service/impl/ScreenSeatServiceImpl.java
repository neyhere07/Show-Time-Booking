package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.Screen;
import com.project.ShowTimeBooking.Entities.ScreenSeat;
import com.project.ShowTimeBooking.Entities.ShowSeat;
import com.project.ShowTimeBooking.Enums.SeatType;
import com.project.ShowTimeBooking.Exceptions.InValidCodeException;
import com.project.ShowTimeBooking.Exceptions.ScreenSeatAlreadyPresentException;
import com.project.ShowTimeBooking.Repository.ScreenSeatRepository;
import com.project.ShowTimeBooking.Repository.ShowSeatRepository;
import com.project.ShowTimeBooking.RequestDTO.ScreenSeatRequest;
import com.project.ShowTimeBooking.RequestDTO.ScreenSeatRequestAddl;
import com.project.ShowTimeBooking.Service.ScreenSeatService;
import com.project.ShowTimeBooking.Service.ScreenService;
import com.project.ShowTimeBooking.Service.ShowSeatService;
import com.project.ShowTimeBooking.Transformers.ScreenSeatTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

    @Autowired
    private ScreenService screenService;
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private ScreenSeatRepository screenSeatRepository;

//    @Transactional(rollbackFor = Exception.class)
    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequest> screenSeatRequestList, List<String> pdSeatList) throws Exception {
        Screen screen = screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        int overallSeatCount = 0;
        for (ScreenSeatRequest screenSeatRequest : screenSeatRequestList) {
            int seatCount = screenSeatRequest.getNumberOfSeats();
            overallSeatCount += seatCount;
            String seatNumberPrefix = screenSeatRequest.getSeatPrefixName();
            for (int i = 1; i <= seatCount; i++) {
                String seatNo = seatNumberPrefix + "" + i;
                SeatType seatType = pdSeatList.contains(seatNo) ? SeatType.PHYSICALLY_HANDICAPPED : screenSeatRequest.getSeatType();
                ScreenSeat screenSeat = ScreenSeatTransformer.toTheatreSeat(seatNo, seatType, screen);
                screen.getScreenSeatList().add(screenSeat);
                screenSeatRepository.save(screenSeat);
            }
            screen.setNoOfSeats(overallSeatCount);
        }
        return "Screen seats " + screenSeatRequestList + "have been added successfully to the screen: " + screenNumber + " of theatre with code: " + theatreCode;
    }

    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequestAddl> screenSeatList) throws Exception {
        Screen screen = screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        List<String> alreadyPresentScreenSeatList = new ArrayList<>();
        List<String> addedScreenSeatList = new ArrayList<>();
        for (ScreenSeatRequestAddl screenSeatRequest : screenSeatList) {
            SeatType screenSeatType = screenSeatRequest.getSeatType();
            String screenSeatNumber = screenSeatRequest.getSeatNumber();

            List<ScreenSeat> screenSeatListDb = screenSeatRepository.findFilteredTheatreShowResponseList(theatreCode, screenNumber, screenSeatNumber);
            if (screenSeatListDb.size() != 0) {
                alreadyPresentScreenSeatList.add(screenSeatRequest.getSeatNumber());
            } else {
                ScreenSeat screenSeat = ScreenSeatTransformer.toTheatreSeat(screenSeatNumber, screenSeatType, screen);
                screen.getScreenSeatList().add(screenSeat);
                screen.setNoOfSeats(screen.getNoOfSeats() + 1);
                screenSeatRepository.save(screenSeat);
                addedScreenSeatList.add(screenSeatRequest.getSeatNumber());
            }
        }
        if (alreadyPresentScreenSeatList.size() != 0) {
            throw new ScreenSeatAlreadyPresentException("Screen seats " + alreadyPresentScreenSeatList + " cannot be added as " +
                    "seat(s) is/are already present and the remaining seat(s) " + addedScreenSeatList + " added to the database successfully");
        } else {
            return "Seat(s) " + addedScreenSeatList + "is/are added to the database successfully";
        }
    }

//    @Transactional(rollbackFor = Exception.class)
    public String deleteScreenSeat(String theatreCode, String screenNumber, String screenSeatNumber) throws Exception {
        List<ScreenSeat> screenSeatList = screenSeatRepository.findFilteredTheatreShowResponseList(theatreCode, screenNumber, screenSeatNumber);
        if (screenSeatList.size() == 0) {
            throw new InValidCodeException("Invalid theatreCode or screenNumber or screenSeat number! try passing the details again");
        }
        LocalDateTime presentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        ScreenSeat screenSeat = screenSeatList.get(0);

        //finding the shows seats that need to be cancelled due to the deletion of screen seat
        List<ShowSeat> showSeatList = screenSeat.getShowSeatList();
        List<ShowSeat> toBeDeletedShowSeatList = new ArrayList<>();
        Iterator<ShowSeat> showSeatIterator = showSeatList.iterator();
        while (showSeatIterator.hasNext()) {
            ShowSeat showSeat = showSeatIterator.next();
            LocalDateTime showStartTime = showSeat.getShow().getStartTime();
            if (showStartTime.equals(presentDateTime) || showStartTime.isAfter(presentDateTime)) {
                toBeDeletedShowSeatList.add(showSeat);
            }
        }
        showSeatService.deleteShowSeats(toBeDeletedShowSeatList);
        Screen screen = screenSeat.getScreen();
        screen.setNoOfSeats(screen.getNoOfSeats() - 1);
        screenSeatRepository.deleteById(screenSeat.getId());

        return "screen seat " + screenSeatNumber + " from screen " + screenNumber + " of theatre " + screenSeat.getScreen().getTheatre().getName() + " removed successfully";
    }
}
