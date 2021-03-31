package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.rest.mapping.AnalysisMapper;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.repository.AnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImplementation implements AnalysisService {

    private final AnalysisRepository analysisRepository;
    private static final String SERVICENAME = "AnalysisService";

    @Override
    public ResponseEntity<Void> addBloodAnalysis(AnalysisRestInput analysisRestInput, User user) {
        return null;
    }


}
