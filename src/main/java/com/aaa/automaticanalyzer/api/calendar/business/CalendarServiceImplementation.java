package com.aaa.automaticanalyzer.api.calendar.business;

import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestInput;
import com.aaa.automaticanalyzer.api.calendar.domain.CalendarRestOutput;
import com.aaa.automaticanalyzer.api.calendar.rest.mapping.CalendarMapper;
import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.api.medication.rest.mapping.MedicationMapper;
import com.aaa.automaticanalyzer.model.Appointment;
import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import com.aaa.automaticanalyzer.notifications.NotificationType;
import com.aaa.automaticanalyzer.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarServiceImplementation implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final MessagingService messagingService;

    @Override
    public void addAppointment(CalendarRestInput calendarRestInput, User user) {
        Appointment appointment = CalendarMapper.createAppointmentFromRestInput(calendarRestInput);
        user.getAppointments().add(appointment);
        calendarRepository.save(appointment);

        messagingService.notifyUser(user, NotificationType.CALENDAR.getNotificationTitle(), NotificationType.CALENDAR.getNotificationBody(), NotificationType.CALENDAR);
    }

    @Override
    public List<CalendarRestOutput> getAppointments(User user) {
        List<Appointment> appointments = user.getAppointments();
        List<CalendarRestOutput> output = new ArrayList<>();
        for (Appointment appointment : appointments){
            output.add(CalendarMapper.mapAppointmentToOutput(appointment, user.getLanguage()));
        }

        return output;
    }
}
