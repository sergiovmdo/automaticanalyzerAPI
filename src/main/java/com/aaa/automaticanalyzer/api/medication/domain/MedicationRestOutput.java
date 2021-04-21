package com.aaa.automaticanalyzer.api.medication.domain;

import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.Medicine;
import lombok.Data;

import java.util.List;

@Data
public class MedicationRestOutput {
    private Long id;
    private List<Medicine> medicines;
    private String disease;
}
