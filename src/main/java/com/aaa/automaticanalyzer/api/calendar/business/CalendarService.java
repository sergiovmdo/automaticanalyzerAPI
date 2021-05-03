package com.aaa.automaticanalyzer.api.calendar.business;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestOutput;
import com.aaa.automaticanalyzer.model.User;

import java.util.List;

public interface CalendarService {
    public void addAppointment(CalendarRestInput calendarRestInput, final User user);

    public List<CalendarRestOutput> getAppointments(final User user);
}
