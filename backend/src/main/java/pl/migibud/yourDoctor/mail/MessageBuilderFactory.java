package pl.migibud.yourDoctor.mail;

import pl.migibud.yourDoctor.appointment.vo.AppointmentEvent;
import pl.migibud.yourDoctor.user.vo.AppUserEvent;

import java.util.stream.Collectors;

class MessageBuilderFactory {
    static String of(AppUserEvent appUserEvent){
        StringBuilder body = new StringBuilder();
        body.append(String.format("Welcome %s %s!",appUserEvent.getData().getFirstName(),appUserEvent.getData().getLastName()));
        body.append("\n");
        body.append("\n");
        body.append("You have successfully registered to yourDoctor app :)");
        body.append("\n");
        body.append("\n");
        body.append("Thank you for your trust!");
        return body.toString();
    }

    static String of(AppointmentEvent appointmentEvent){
        StringBuilder body = new StringBuilder();

        String userFirstName = appointmentEvent.getData().getAppointment().getAppUser().getFirstName();
        String userLastName = appointmentEvent.getData().getAppointment().getAppUser().getLastName();
        String docFirstName = appointmentEvent.getData().getAppointment().getDoctor().getAppUser().getFirstName();
        String docLastName = appointmentEvent.getData().getAppointment().getDoctor().getAppUser().getLastName();
        String docSpec = appointmentEvent.getData().getAppointment().getDoctor().getSpecializations().stream()
                .map(s->s.getSpecialization().toString())
                .collect(Collectors.joining(", "));

        String date = appointmentEvent.getData().getAppointment().getDateOfAppointment().toString();
        String time = appointmentEvent.getData().getAppointment().getTimeOfAppointment().toString();
        String street = appointmentEvent.getData().getAppointment().getAddress().getStreet();
        String number = appointmentEvent.getData().getAppointment().getAddress().getNumber();
        String city = appointmentEvent.getData().getAppointment().getAddress().getCity();
        String zipCode = appointmentEvent.getData().getAppointment().getAddress().getZipCode();

        body.append(String.format("Welcome %s %s!",userFirstName,userLastName));
        body.append("\n");
        body.append("\n");
        body.append(String.format("Below you have information about your appointment:"));
        body.append("\n");
        body.append("\n");
        body.append(String.format("Doctor %s %s, %s",docFirstName,docLastName,docSpec));
        body.append("\n");
        body.append(String.format("Date and time of appointment: %s, %s \n",date,time));
        body.append("\n");
        body.append(String.format("Doctor's address: %s %s, %s %s\n",street,number,city,zipCode));
        body.append("\n");
        body.append("Please remember to bring all important research results.");
        body.append("\n");
        body.append("\n");
        body.append("Best regards,");
        body.append("\n");
        body.append("YourDoctor team!");

        return body.toString();
    }
}
