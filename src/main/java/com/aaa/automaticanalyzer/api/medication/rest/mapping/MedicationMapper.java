package com.aaa.automaticanalyzer.api.medication.rest.mapping;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.model.Medication;

public class MedicationMapper {
    public static MedicationRestOutput mapMedicationToOutput(Medication medication){
        MedicationRestOutput medicationRestOutput = new MedicationRestOutput();
        medicationRestOutput.setDisease(Strings.getStringFromObject(medication.getDisease()).getSpanish());
        medicationRestOutput.setId(medication.getId());
        medicationRestOutput.setMedicines(medication.getMedicines());

        return medicationRestOutput;
    }

}
