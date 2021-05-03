package com.aaa.automaticanalyzer.api.calendar.rest;

import com.aaa.automaticanalyzer.api.calendar.business.CalendarService;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestOutput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public List<CalendarRestOutput> getAppointments(final User user){
        return calendarService.getAppointments(user);
    }

}
