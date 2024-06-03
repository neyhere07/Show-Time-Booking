package com.project.ShowTimeBooking.Generators;

import com.project.ShowTimeBooking.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TheatreCodeGenerator extends CodeGenerator{
    @Autowired
    TheatreRepository theatreRepository;

    @Override
    public String generate(String prefix) throws Exception {
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(String year) {
        Long latestSequenceNumber= theatreRepository.findLatestSequenceNumber(year);
        return (latestSequenceNumber!=null) ? latestSequenceNumber : 0;
    }
}
