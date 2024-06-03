package com.project.ShowTimeBooking.Generators;

import com.project.ShowTimeBooking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCodeGenerator extends CodeGenerator{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String generate(String prefix) throws Exception {
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(String year) {
        Long latestSequenceNumber= userRepository.findLatestSequenceNumber(year);
        return (latestSequenceNumber!=null) ? latestSequenceNumber : 0;
    }
}
