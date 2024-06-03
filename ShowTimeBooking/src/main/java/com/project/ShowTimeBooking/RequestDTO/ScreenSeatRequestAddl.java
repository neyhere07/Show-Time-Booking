package com.project.ShowTimeBooking.RequestDTO;

import com.project.ShowTimeBooking.Enums.SeatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreenSeatRequestAddl {
    String seatNumber;
    SeatType seatType;
}
