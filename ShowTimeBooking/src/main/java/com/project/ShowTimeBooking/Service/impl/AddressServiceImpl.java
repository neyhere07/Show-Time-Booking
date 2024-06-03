package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.Address;
import com.project.ShowTimeBooking.Exceptions.AddressAlreadyPresentException;
import com.project.ShowTimeBooking.Generators.AddressCodeGenerator;
import com.project.ShowTimeBooking.Repository.AddressRepository;
import com.project.ShowTimeBooking.RequestDTO.AddressRequest;
import com.project.ShowTimeBooking.Service.AddressService;
import com.project.ShowTimeBooking.Transformers.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressCodeGenerator addressCodeGenerator;

    public String addAddress(AddressRequest addressRequest)throws Exception{
        Optional<Address> optionalAddress=addressRepository.findByLocationUrl(addressRequest.getLocationUrl());
        if(optionalAddress.isPresent()){
            throw new AddressAlreadyPresentException("Address is already present in the database with particular location with address id: "+optionalAddress.get().getCode());
        }
        Address address= AddressTransformer.AddressRequestToAddress(addressRequest);
        String code= addressCodeGenerator.generate("ADR_"+address.getCity().getCityCode());
        address.setCode(code);
        Address savedAddress=addressRepository.save(address);
        return savedAddress.getCode();
    }
    public Address getAddressByAddressCode(String addressCode)throws Exception{
        Optional<Address>optionalAddress=addressRepository.findByCode(addressCode);
        if(optionalAddress.isEmpty()){
            throw new InvalidAddressCodeException("Provided address code is invalid. Check again once");
        }
        return optionalAddress.get();
    }
}
