package com.aaa.automaticanalyzer.api.medication.business;

import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.api.medication.rest.mapping.MedicationMapper;
import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MedicationServiceImplementation implements MedicationService {

    @Override
    public List<MedicationRestOutput> getMedication(final User user) {
        List<Medication> medications = user.getMedications();
        List<MedicationRestOutput> output = new ArrayList<>();
        for (Medication medication : medications){
            output.add(MedicationMapper.mapMedicationToOutput(medication));
        }
        return output;
    }
}
