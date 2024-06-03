package com.project.ShowTimeBooking.ResponseDTO;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TicketResponse {
    String movieName;
    String filmCertificationCategory;
    String format;
    String language;
    String theatreName;
    String locality;
    URL locationUrl;
    String screenNumber;
    Map<String, List<String>> seatInfo;
    LocalDate showDate;
    LocalTime showTime;
    int totalCost;
}
