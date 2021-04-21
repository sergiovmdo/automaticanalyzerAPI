package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
