package pl.migibud.yourDoctor.mail;

import javax.mail.MessagingException;

interface EmailSender {
    void sendEmail(EmailMessageDto emailMessageDto) throws MessagingException;

}
