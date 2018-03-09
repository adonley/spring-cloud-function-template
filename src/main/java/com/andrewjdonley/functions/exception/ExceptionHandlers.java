package com.andrewjdonley.functions.exception;

import com.andrewjdonley.functions.domain.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GenericResponse<Void> handleUserNotFoundException(final NotFoundException ex) {
        return new GenericResponse<>(ex.getMessage(), true, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public GenericResponse<Void> handleThrowable(final BadRequestException ex) {
        return new GenericResponse<>(ex.getMessage(), true, null);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public GenericResponse<Void> handleThrowable(final ConflictException ex) {
        return new GenericResponse<>(ex.getMessage(), true, null);
    }

    // TODO: Generic case isn't actually handled here lol. Throwable -> 500
}
