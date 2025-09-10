package com.yasmin.projects.medsystem.api.validation;

import java.time.LocalDateTime;
import java.util.List;

public interface Validation {
    void isValid(LocalDateTime dateTime, List<LocalDateTime> allDateTimeExisting);
}
