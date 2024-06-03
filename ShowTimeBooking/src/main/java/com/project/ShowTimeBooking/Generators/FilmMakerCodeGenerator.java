package com.project.ShowTimeBooking.Generators;

import com.project.ShowTimeBooking.Repository.FilmMakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmMakerCodeGenerator extends CodeGenerator{
    @Autowired
    FilmMakerRepository filmMakerRepository;

    @Override
    public String generate(String prefix) throws Exception {
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(String year) {
        Long latestSequenceNumber= filmMakerRepository.findLatestSequenceNumber(year);
        return (latestSequenceNumber!=null) ? latestSequenceNumber : 0;
    }
}
