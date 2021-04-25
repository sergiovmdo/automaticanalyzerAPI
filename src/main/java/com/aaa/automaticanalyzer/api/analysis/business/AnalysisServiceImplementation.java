package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.rest.mapping.AnalysisMapper;
import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import com.aaa.automaticanalyzer.notifications.NotificationType;
import com.aaa.automaticanalyzer.repository.AnalysisRepository;
import com.aaa.automaticanalyzer.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImplementation implements AnalysisService {
    private final AnalysisRepository analysisRepository;
    private final MedicationRepository medicationRepository;
    private final MessagingService messagingService;
    private static final String SERVICENAME = "AnalysisService";

    @Override
    public void addBloodAnalysis(AnalysisRestInput analysisRestInput, final User user) {
        Analysis analysis = AnalysisMapper.createAnalysisFromRestInput(analysisRestInput);
        analysis.getDisease().addAnalysisData(analysis, analysisRestInput);
        analysis.setDate(Calendar.getInstance().getTimeInMillis());
        analysis.setUser(user);
        analysisRepository.save(analysis);
        boolean medicationModified = analysis.getDisease().getEngine().modifyMedication(user, analysis.getDisease().getAnalisis(analysis.getAnalysisData()));
        if (medicationModified) {
            for (Medication medication : user.getMedications()) {
                medicationRepository.save(medication);
            }
        }
        messagingService.notifyUser(user, NotificationType.ANALYSIS.getNotificationTitle(), NotificationType.ANALYSIS.getNotificationBody(), NotificationType.ANALYSIS);
    }

    @Transactional
    @Override
    public List<Analysis> getAnalysis(User user) {
        return analysisRepository.getAnalysis(user.getDni()).collect(Collectors.toList());
    }
}
