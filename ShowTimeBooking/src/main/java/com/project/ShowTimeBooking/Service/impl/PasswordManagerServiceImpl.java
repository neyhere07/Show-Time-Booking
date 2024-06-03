package com.project.ShowTimeBooking.Service.impl;

public class PasswordManagerServiceImpl extends BCryptPasswordEncoder {
    public PasswordManagerServiceImpl() {
        super();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword, encodedPassword);
    }
}
