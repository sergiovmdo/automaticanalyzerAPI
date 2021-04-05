package com.aaa.automaticanalyzer.api.calendar.business;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.http.ResponseEntity;

public interface CalendarService {
    public ResponseEntity<Void> addAppointment(CalendarRestInput calendarRestInput, final User user);
}
