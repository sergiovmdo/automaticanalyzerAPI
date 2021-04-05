package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Appointment, String> {
}
