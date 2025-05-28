package com.yasmin.projects.medsystem.api.validation;

import com.yasmin.projects.medsystem.api.exception.AlreadyExistsAnAppointmentBetweenThisOneHour;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExistsAppointmentAfterAnHour implements Validation {

    @Override
    public void isValid(LocalDateTime dateTime, List<LocalDateTime> allDateTimeExisting) {
        LocalDateTime dateTimeWithOnePlusHour = dateTime.plusHours(1);

        for (LocalDateTime dateTimeElement : allDateTimeExisting) {
            if (dateTimeElement.isBefore(dateTimeWithOnePlusHour) && dateTimeElement.isAfter(dateTime) || dateTimeElement.equals(dateTime)) {
                throw new AlreadyExistsAnAppointmentBetweenThisOneHour("Não é possivel agendar consulta neste horário, " +
                        "tente marcar antes das " + dateTimeElement.minusHours(1).toLocalTime() + " ou após das " + dateTimeElement.plusHours(1).toLocalTime());
            }
        }
    }


}

