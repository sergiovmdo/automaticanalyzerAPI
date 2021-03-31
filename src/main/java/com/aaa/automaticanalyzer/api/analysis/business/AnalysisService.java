package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.http.ResponseEntity;

public interface AnalysisService {
    public ResponseEntity<Void> addBloodAnalysis(AnalysisRestInput analysis, final User user);

}
