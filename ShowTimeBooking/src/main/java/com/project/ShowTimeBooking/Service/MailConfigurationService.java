package com.project.ShowTimeBooking.Service;

public interface MailConfigurationService {
    public void updateSenderEmail(String senderEmail);
    public void mailSender(String senderEmail, String recipientEmail, String body, String subject);
}
