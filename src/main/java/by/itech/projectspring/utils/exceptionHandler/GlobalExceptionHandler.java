package by.itech.projectspring.utils.exceptionHandler;

import by.itech.projectspring.utils.exceptions.CarNotFoundException;
import by.itech.projectspring.utils.exceptions.DriverLicenseNotFoundException;
import by.itech.projectspring.utils.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> userNotFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "User is not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> carNotFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Car is not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DriverLicenseNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> driverLicenseNotDoundException() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Driver license is not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

