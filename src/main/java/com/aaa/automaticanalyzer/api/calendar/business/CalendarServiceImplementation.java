package com.aaa.automaticanalyzer.api.calendar.business;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.api.calendar.rest.mapping.CalendarMapper;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import com.aaa.automaticanalyzer.notifications.NotificationType;
import com.aaa.automaticanalyzer.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarServiceImplementation implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final MessagingService messagingService;

    @Override
    public ResponseEntity<Void> addAppointment(CalendarRestInput calendarRestInput, User user) {
        Appointment appointment = CalendarMapper.createAppointmentFromRestInput(calendarRestInput);
        appointment.setUser(user);
        calendarRepository.save(appointment);

        messagingService.notifyUser(user, NotificationType.CALENDAR.getNotificationTitle(), NotificationType.CALENDAR.getNotificationBody(), NotificationType.CALENDAR);

        return ResponseEntity.ok().build();
    }

    @Override
    public List<Appointment> getAppointments(User user) {
        return calendarRepository.getAppointments(user.getDni()).collect(Collectors.toList());
    }
}
