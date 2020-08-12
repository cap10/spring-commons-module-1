package zw.co.invenico.springcommonsmodule.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.invenico.springcommonsmodule.dto.RestResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
@RestController

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public final ResponseEntity<ErrorMessage> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex, WebRequest request) {
//        ErrorMessage errorDetails = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getName());
//        ex.printStackTrace();
//        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
//    }


    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<RestResponse> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<RestResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<RestResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public final ResponseEntity<RestResponse> handleIllegalAccessException(IllegalAccessException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.FORBIDDEN, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public final ResponseEntity<RestResponse> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(NullPointerException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getMessage());

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDataIntegrityViolationException(WebRequest request, Exception ex) {
        log.error(ex.getMessage());
        String message = getRootException(ex).getLocalizedMessage();
        if (message.contains("Column")) {
            message = message.replace("Column", "Field");
        }
        if (message.contains("Duplicate")) {
            message = message.replace(message.substring(message.indexOf("for"), message.length()), ". This value must be unique.");
        }
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    private static Throwable getRootException(Throwable exception) {
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }

}
