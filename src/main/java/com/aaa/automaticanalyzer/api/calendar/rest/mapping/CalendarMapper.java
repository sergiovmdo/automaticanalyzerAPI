package com.aaa.automaticanalyzer.api.calendar.rest.mapping;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestOutput;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.utils.Utils;

public class CalendarMapper {
    public static Appointment createAppointmentFromRestInput(CalendarRestInput calendarRestInput){
        Appointment appointment = new Appointment();
        appointment.setDisease(Utils.getDiseaseFromString(calendarRestInput.getDisease()));
        appointment.setLocation(calendarRestInput.getLocation());
        appointment.setDate(Utils.getDateFromString(calendarRestInput.getDate(), calendarRestInput.getTime()));

        return  appointment;
    }

    public static CalendarRestOutput mapAppointmentToOutput(Appointment appointment, Language language){
        CalendarRestOutput calendarRestOutput = new CalendarRestOutput();
        calendarRestOutput.setDate(appointment.getDate());
        calendarRestOutput.setDisease(Strings.getStringFromObject(appointment.getDisease()).getLanguage(language));
        calendarRestOutput.setLocation(appointment.getLocation());

        return calendarRestOutput;
    }
}
