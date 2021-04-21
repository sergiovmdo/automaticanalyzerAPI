package com.aaa.automaticanalyzer.api.medication.rest;

import com.aaa.automaticanalyzer.api.medication.business.MedicationService;
import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medication")
@AllArgsConstructor
@AuthAwareRestController
public class MedicationController {
    final MedicationService medicationService;

    @GetMapping
    List<MedicationRestOutput> getMedication(final User user){
        return medicationService.getMedication(user);
    }
}
