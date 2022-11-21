package pl.migibud.yourDoctor.mail;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.migibud.yourDoctor.appointment.vo.AppointmentEvent;
import pl.migibud.yourDoctor.user.vo.AppUserEvent;

import javax.mail.MessagingException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppEventsListeners {

    private final EmailSender emailSender;

    @Async
    @EventListener
    public void handleRegistrationEvent(AppUserEvent appUserEvent) throws MessagingException {

        String email = appUserEvent.getData().getEmail();
        String title = "Welcome to yourDoctor app!";
        String content = MessageBuilderFactory.of(appUserEvent);

        emailSender.sendEmail(new EmailMessageDto(email,title,content));
    }

    @Async
    @EventListener
    public void handleAppointmentEvent(AppointmentEvent appointmentEvent) throws MessagingException{

        String email = appointmentEvent.getData().getAppointment().getAppUser().getSecurityUser().getEmail();
        String title = "YourDoctor, your appointment has been booked!";
        String content = MessageBuilderFactory.of(appointmentEvent);

        emailSender.sendEmail(new EmailMessageDto(email,title,content));
    }
}
