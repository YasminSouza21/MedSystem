package com.yasmin.projects.medsystem.api.exception;

public class AlreadyExistsAnAppointmentBetweenThisOneHour extends RuntimeException {
    public AlreadyExistsAnAppointmentBetweenThisOneHour(String message){
        super(message);
    }
}
