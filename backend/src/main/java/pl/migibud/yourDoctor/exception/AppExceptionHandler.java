package pl.migibud.yourDoctor.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.migibud.yourDoctor.exception.address.AddressError;
import pl.migibud.yourDoctor.exception.address.AddressException;
import pl.migibud.yourDoctor.exception.appointment.AppointmentError;
import pl.migibud.yourDoctor.exception.appointment.AppointmentException;
import pl.migibud.yourDoctor.exception.doctor.DoctorError;
import pl.migibud.yourDoctor.exception.doctor.DoctorException;
import pl.migibud.yourDoctor.exception.medical.specialization.SpecializationError;
import pl.migibud.yourDoctor.exception.medical.specialization.SpecializationException;
import pl.migibud.yourDoctor.exception.security.user.SecurityUserError;
import pl.migibud.yourDoctor.exception.security.user.SecurityUserException;
import pl.migibud.yourDoctor.exception.user.AppUserError;
import pl.migibud.yourDoctor.exception.user.AppUserException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = AppUserException.class)
    public ResponseEntity<ErrorInfo> handleAppUserException(AppUserException e){
        HttpStatus httpStatus = null;
        if (AppUserError.USER_NOT_FOUND.equals(e.getAppUserError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getAppUserError().getMessage())));
    }

    @ExceptionHandler(value = SecurityUserException.class)
    public ResponseEntity<ErrorInfo> handleSecurityUserException(SecurityUserException e){
        HttpStatus httpStatus = null;
        if (SecurityUserError.SECURITY_USER_NOT_FOUND.equals(e.getSecurityUserError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (SecurityUserError.SECURITY_USER_ALREADY_EXISTS.equals(e.getSecurityUserError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (SecurityUserError.SECURITY_USER_ID_DOES_NOT_MATCH.equals(e.getSecurityUserError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getSecurityUserError().getMessage())));
    }

    @ExceptionHandler(value = DoctorException.class)
    public ResponseEntity<ErrorInfo> handleDoctorException(DoctorException e){
        HttpStatus httpStatus = null;
        if (DoctorError.DOCTOR_NOT_FOUND.equals(e.getDoctorError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getDoctorError().getMessage())));
    }

    @ExceptionHandler(value = SpecializationException.class)
    public ResponseEntity<ErrorInfo> handleSpecializationException(SpecializationException e){
        HttpStatus httpStatus = null;
        if (SpecializationError.MEDICAL_SPECIALIZATION_NOT_FOUND.equals(e.getSpecializationError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getSpecializationError().getMessage())));
    }

    @ExceptionHandler(value = AddressException.class)
    public ResponseEntity<ErrorInfo> handleAddressException(AddressException e){
        HttpStatus httpStatus = null;
        if (AddressError.ADDRESS_NOT_FOUND.equals(e.getAddressError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (AddressError.ADDRESS_NOT_FOUND_FOR_GIVEN_DOCTOR.equals(e.getAddressError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getAddressError().getMessage())));
    }

    @ExceptionHandler(value = AppointmentException.class)
    public ResponseEntity<ErrorInfo> handleAppointmentException(AppointmentException e){
        HttpStatus httpStatus = null;
        if (AppointmentError.APPOINTMENT_NOT_FOUND.equals(e.getAppointmentError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (AppointmentError.DATE_APPOINTMENT_IS_INVALID.equals(e.getAppointmentError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (AppointmentError.APPOINTMENT_ALREADY_CREATED.equals(e.getAppointmentError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        if (AppointmentError.DOCTOR_MAKE_APPOINTMENT_TO_HIMSELF.equals(e.getAppointmentError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        if (AppointmentError.APPOINTMENT_ALREADY_TAKEN.equals(e.getAppointmentError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getAppointmentError().getMessage())));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error ->errors.add(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo(errors));
    }
}
