package com.aaa.automaticanalyzer.api.calendar.business;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CalendarService {
    public void addAppointment(CalendarRestInput calendarRestInput, final User user);

    public List<Appointment> getAppointments(final User user);
}
