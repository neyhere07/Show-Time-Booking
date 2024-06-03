package com.project.ShowTimeBooking.Generators;

import com.project.ShowTimeBooking.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowCodeGenerator extends CodeGenerator{
    @Autowired
    private ShowRepository showRepository;
    @Override
    public String generate(String prefix) throws Exception {
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(String year) {
        Long latestSequenceNumber= showRepository.findLatestSequenceNumber(year);
        return (latestSequenceNumber!=null) ? latestSequenceNumber : 0;
    }
}
