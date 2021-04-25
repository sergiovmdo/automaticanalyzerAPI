package com.aaa.automaticanalyzer.api.calendar.rest;

import com.aaa.automaticanalyzer.api.calendar.business.CalendarService;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Response;
import java.util.List;

@Controller
@RequestMapping("/calendar")
@AllArgsConstructor
@AuthAwareRestController
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public void addCalendarAppointment(@RequestBody final CalendarRestInput calendarRestInput, final User user){
        calendarService.addAppointment(calendarRestInput, user);
    }

    @GetMapping
    List<Appointment> getAppointments(final User user){
        return calendarService.getAppointments(user);
    }

}
