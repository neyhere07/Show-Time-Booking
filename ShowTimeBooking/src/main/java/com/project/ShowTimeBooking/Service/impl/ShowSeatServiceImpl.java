package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.Screen;
import com.project.ShowTimeBooking.Entities.ScreenSeat;
import com.project.ShowTimeBooking.Entities.Show;
import com.project.ShowTimeBooking.Entities.ShowSeat;
import com.project.ShowTimeBooking.Enums.SeatType;
import com.project.ShowTimeBooking.Repository.ShowSeatRepository;
import com.project.ShowTimeBooking.RequestDTO.ShowSeatRequest;
import com.project.ShowTimeBooking.Service.ScreenService;
import com.project.ShowTimeBooking.Service.ShowSeatService;
import com.project.ShowTimeBooking.Service.ShowService;
import com.project.ShowTimeBooking.Service.TicketService;
import com.project.ShowTimeBooking.Transformers.ShowSeatTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.project.ShowTimeBooking.Enums.SeatType.*;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private ShowService showService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private ShowSeatRepository showSeatRepository;

//    @Transactional(rollbackFor = Exception.class)
    public String addShowSeats(String showCode, String screenNumber, String theatreCode,  ShowSeatRequest showSeatRequest)throws Exception{

        Show show=showService.getShowByShowCode(showCode);
        Screen screen=screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        List<ScreenSeat>screenSeatList=screen.getScreenSeatList();
        List<ShowSeat>showSeatList=new ArrayList<>();

        for(ScreenSeat screenSeat : screenSeatList){
            int cost=0;
            boolean isFoodAttached=false;
            SeatType seatType=screenSeat.getScreenSeatType();
            switch (seatType){
                case PHYSICALLY_HANDICAPPED -> {
                    cost=showSeatRequest.getPriceOfPhysicallyHandicappedSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForPHS();
                }
                case SILVER -> {
                    cost = showSeatRequest.getPriceOfSilverSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForSS();
                }
                case GOLD -> {
                    cost=showSeatRequest.getPriceOfGoldSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForGS();
                }
                case PLATINUM -> {
                    cost = showSeatRequest.getPriceOfPlatinumSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForPS();
                }
                case LOUNGERS -> {
                    cost = showSeatRequest.getPriceOfLoungers();
                    isFoodAttached = showSeatRequest.isFoodAttachedForLS();
                }
                case SEMI_RECLINERS -> {
                    cost= showSeatRequest.getPriceOfSemiRecliners();
                    isFoodAttached = showSeatRequest.isFoodAttachedForSRS();
                }
                case RECLINERS -> {
                    cost = showSeatRequest.getPriceOfRecliners();
                    isFoodAttached = showSeatRequest.isFoodAttachedForRS();
                }
            }

            ShowSeat showSeat= ShowSeatTransformer.showSeatRequestToShowSeat(screenSeat.getScreenSeatNumber(),screenSeat.getScreenSeatType(),
                    cost, true, isFoodAttached);

            //setting the foreign keys
            showSeat.setShow(show);
            showSeat.setScreenSeat(screenSeat);

            //mapping foreign keys bidirectionally
            show.getShowSeatList().add(showSeat);
            screenSeat.getShowSeatList().add(showSeat);

            //adding it to the showSeat list
            showSeatList.add(showSeat);
        }
        return "Show seat list"+ showSeatList+" have been added successfully";
    }
    public String deleteShowSeats(String showCode, List<String>showSeatNumberList) throws Exception{
        List<ShowSeat>showSeatList=findShowSeatsByShowCodeAndShowSeatNoList(showCode, showSeatNumberList);
        processShowSeatDeletion(showSeatList);
        return showSeatList+" has been removed successfully";
    }
    public String deleteShowSeats(List<ShowSeat>showSeatList)throws Exception{
        processShowSeatDeletion(showSeatList);
        return showSeatList+" has been removed successfully";
    }
//    @Transactional(rollbackFor = Exception.class)
    private void processShowSeatDeletion(List<ShowSeat>showSeatList)throws Exception{
        Iterator<ShowSeat> showSeatIterator= showSeatList.iterator();
        List<Integer>showSeatId=new ArrayList<>();
        while (showSeatIterator.hasNext()){
            ShowSeat showSeat=showSeatIterator.next();
            Show show = showSeat.getShow();
            ScreenSeat screenSeat = showSeat.getScreenSeat();
            //setting show seat attributes to null
            showSeat.setShow(null);
            showSeat.setScreenSeat(null);
            //bi-directionally removing
            show.getShowSeatList().remove(showSeat);
            screenSeat.getShowSeatList().remove(showSeat);

            //Handling null pointer exception as ticket cancellation
            // can lead to removal of ticket mapping from other show seats
            if (showSeat.getTicket() != null)
                ticketService.cancelTicket(showSeat.getTicket().getCode());

            //finally deleting show seat after removing all the mappings
            showSeatRepository.deleteById(showSeat.getId());
        }

    }
    public List<ShowSeat> findShowSeatsByShowCodeAndShowSeatNoList(String showCode, List<String>showSeatNumberList){
        List<ShowSeat> showSeatList=showSeatRepository.findByShowCodeAndShowSeatNoIn(showCode, showSeatNumberList);
        return showSeatList;
    }
}
