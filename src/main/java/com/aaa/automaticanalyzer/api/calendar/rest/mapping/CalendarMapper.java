package com.aaa.automaticanalyzer.api.calendar.rest.mapping;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.utils.Utils;

public class CalendarMapper {
    public static Appointment createAppointmentFromRestInput(CalendarRestInput calendarRestInput){
        Appointment appointment = new Appointment();
        appointment.setDisease(Utils.getDiseaseFromString(calendarRestInput.getDisease()));
        appointment.setLocation(calendarRestInput.getLocation());
        appointment.setDate(Utils.getDateFromString(calendarRestInput.getDate(), calendarRestInput.getTime()));

        return  appointment;
    }
}
