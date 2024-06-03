package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.Address;
import com.project.ShowTimeBooking.RequestDTO.AddressRequest;

public class AddressTransformer {
    public static Address AddressRequestToAddress(AddressRequest addressRequest) {
        return Address.builder()
                .plotNo(addressRequest.getPlotNo())
                .locality(addressRequest.getLocality())
                .city(addressRequest.getCity())
                .pinCode(addressRequest.getPinCode())
                .locationUrl(addressRequest.getLocationUrl())
                .build();
        //still theatre needs to be mapped
    }
}
