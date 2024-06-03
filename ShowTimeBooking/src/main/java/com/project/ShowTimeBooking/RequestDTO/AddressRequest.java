package com.project.ShowTimeBooking.RequestDTO;

import com.project.ShowTimeBooking.Enums.City;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    String plotNo;
    String locality;
    City city;
    int pinCode;
    URL locationUrl;
}
