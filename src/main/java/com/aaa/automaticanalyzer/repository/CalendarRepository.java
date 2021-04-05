package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface CalendarRepository extends JpaRepository<Appointment, String> {
    @Query(value = "SELECT * from APPOINTMENTS WHERE user_dni = :uid" , nativeQuery = true)
    Stream<Appointment> getAppointments(final String uid);
}
