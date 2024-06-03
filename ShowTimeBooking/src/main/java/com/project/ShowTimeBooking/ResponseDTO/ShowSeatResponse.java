package com.project.ShowTimeBooking.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ShowSeatResponse {
    String showSeatNo;
    String showSeatType;
    int cost;
    boolean isAvailable;
    boolean isFoodAttached;
}
