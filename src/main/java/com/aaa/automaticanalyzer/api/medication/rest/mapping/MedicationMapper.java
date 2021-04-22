package com.aaa.automaticanalyzer.api.medication.rest.mapping;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.model.Medication;

public class MedicationMapper {
    public static MedicationRestOutput mapMedicationToOutput(Medication medication, Language language){
        MedicationRestOutput medicationRestOutput = new MedicationRestOutput();
        medicationRestOutput.setDisease(Strings.getStringFromObject(medication.getDisease()).getLanguage(language));
        medicationRestOutput.setId(medication.getId());
        medicationRestOutput.setMedicines(medication.getMedicines());

        return medicationRestOutput;
    }

}
