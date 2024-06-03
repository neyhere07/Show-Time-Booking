package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.*;
import com.project.ShowTimeBooking.Enums.City;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.Exceptions.InValidTheatreCodeException;
import com.project.ShowTimeBooking.Generators.TheatreCodeGenerator;
import com.project.ShowTimeBooking.Repository.TheatreRepository;
import com.project.ShowTimeBooking.RequestDTO.AddressRequest;
import com.project.ShowTimeBooking.RequestDTO.TheatreRequest;
import com.project.ShowTimeBooking.ResponseDTO.ShowResponseTheatre;
import com.project.ShowTimeBooking.ResponseDTO.ShowSeatResponse;
import com.project.ShowTimeBooking.ResponseDTO.TheatreResponseShow;
import com.project.ShowTimeBooking.Service.AddressService;
import com.project.ShowTimeBooking.Service.ScreenService;
import com.project.ShowTimeBooking.Service.TheatreService;
import com.project.ShowTimeBooking.Transformers.ShowSeatTransformer;
import com.project.ShowTimeBooking.Transformers.ShowTransformer;
import com.project.ShowTimeBooking.Transformers.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService{
    @Autowired
    private AddressService addressService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private TheatreCodeGenerator theatreCodeGenerator;
    @Transactional
    public String addTheatre(TheatreRequest theatreRequest)throws Exception{
        Theatre theatre= TheatreTransformer.theatreRequestToTheatre(theatreRequest);

        AddressRequest addressRequest=theatreRequest.getAddressRequest();
        String addressCode=addressService.addAddress(addressRequest);
        Address address=addressService.getAddressByAddressCode(addressCode);

        String theatreCode=theatreCodeGenerator.generate("THR");

        address.setTheatre(theatre); //foreign key is set from address side
        theatre.setAddress(address); //bidirectionally mapping

        theatre.setCode(theatreCode);

        theatreRepository.save(theatre);

        screenService.addScreens(theatreCode, theatreRequest.getScreenRequestList());

        theatreRepository.save(theatre); //cascading the save effect to address and screens
        return theatre.getCode();
    }
    public String deleteTheatre(String theatreCode)throws Exception{
        Theatre theatre=getTheatreByTheatreCode(theatreCode);
        List<Screen>screenList=theatre.getScreens();
        for(Screen screen: screenList){
            screenService.deleteScreen(theatreCode, screen.getScreenNumber());
        }
        theatreRepository.deleteById(theatre.getId());
        return "Theatre "+theatre.getName()+" is removed successfully from the database. Respective shows have been " +
                "cancelled and refund initiated to the users who booked tickets";
    }
    public Theatre getTheatreByTheatreCode(String theatreCode)throws Exception{
        Optional<Theatre>optionalTheatre=theatreRepository.findByCode(theatreCode);
        if(optionalTheatre.isEmpty()){
            throw new InValidTheatreCodeException("Theatre Code is Invalid!! Try giving a valid theatre code");
        }
        return optionalTheatre.get();
    }
}
