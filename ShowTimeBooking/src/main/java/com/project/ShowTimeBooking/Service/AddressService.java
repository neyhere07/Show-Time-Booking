package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Address;
import com.project.ShowTimeBooking.RequestDTO.AddressRequest;

public interface AddressService {
    public String addAddress(AddressRequest addressRequest)throws Exception;
    public Address getAddressByAddressCode(String addressCode)throws Exception;
}
