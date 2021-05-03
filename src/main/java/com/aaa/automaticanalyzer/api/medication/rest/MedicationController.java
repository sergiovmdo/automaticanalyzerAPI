package com.aaa.automaticanalyzer.api.medication.rest;

import com.aaa.automaticanalyzer.api.medication.business.MedicationService;
import com.aaa.automaticanalyzer.api.medication.domain.MedicationRestOutput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medication")
@AllArgsConstructor
@AuthAwareRestController
public class MedicationController {
    final MedicationService medicationService;

    @GetMapping
    public List<MedicationRestOutput> getMedication(final User user){
        return medicationService.getMedication(user);
    }
}
