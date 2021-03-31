package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnalysisService {
    public ResponseEntity<Void> addBloodAnalysis(AnalysisRestInput analysis, final User user);

    public List<Analysis> getAnalysis(final User user);

}
