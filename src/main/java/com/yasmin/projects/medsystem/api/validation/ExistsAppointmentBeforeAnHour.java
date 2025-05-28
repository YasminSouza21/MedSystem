package com.yasmin.projects.medsystem.api.validation;

import com.yasmin.projects.medsystem.api.exception.AlreadyExistsAnAppointmentBetweenThisOneHour;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExistsAppointmentBeforeAnHour  implements Validation{

    @Override
    public void isValid(LocalDateTime dateTime, List<LocalDateTime> allDateTimeExisting) {
        LocalDateTime dateTimeWithOneMinusHour = dateTime.minusHours(1);

        for (LocalDateTime dateTimeElement : allDateTimeExisting) {
            if (dateTimeElement.isAfter(dateTimeWithOneMinusHour) && dateTimeElement.isBefore(dateTime) || dateTimeElement.equals(dateTime)) {
                throw new AlreadyExistsAnAppointmentBetweenThisOneHour("Não é possivel agendar consulta neste horário, " +
                        "tente marcar antes das " + dateTimeElement.minusHours(1).toLocalTime() + " ou após das " + dateTimeElement.plusHours(1).toLocalTime());
            }
        }
    }
}
