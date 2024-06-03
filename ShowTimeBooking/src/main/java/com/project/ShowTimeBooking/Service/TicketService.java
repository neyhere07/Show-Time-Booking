package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.RequestDTO.TicketRequest;
import com.project.ShowTimeBooking.ResponseDTO.TicketResponse;

import java.time.LocalDate;

public interface TicketService {
    public TicketResponse bookTicket(TicketRequest ticketRequest)throws Exception;
    public String cancelTicket(String ticketCode)throws Exception;
    public long getMovieBookingRevenueByMovieCodeAndDateRange(String movieCode, LocalDate startDateOfRange, LocalDate endDateOfRange);

}
