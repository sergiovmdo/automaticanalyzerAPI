package com.aaa.automaticanalyzer.api.medication.business;

import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.model.User;

import java.util.List;

public interface MedicationService {
    public List<MedicationRestOutput> getMedication(final User user);
}
