package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.rest.mapping.AnalysisMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.repository.AnalysisRepository;
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
    private static final String SERVICENAME = "AnalysisService";

    @Override
    public ResponseEntity<Void> addBloodAnalysis(AnalysisRestInput analysisRestInput, final User user) {
        Analysis analysis = AnalysisMapper.createAnalysisFromRestInput(analysisRestInput);
        analysis.getDisease().addAnalysisData(analysis, analysisRestInput);
        analysis.setDate(Calendar.getInstance().getTimeInMillis());
        analysis.setUser(user);
        analysisRepository.save(analysis);
        return null;
    }

    @Transactional
    @Override
    public List<Analysis> getAnalysis(User user) {
        return analysisRepository.getAnalysis(user.getDni()).collect(Collectors.toList());
    }
}
