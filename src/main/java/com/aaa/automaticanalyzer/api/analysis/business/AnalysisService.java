package com.aaa.automaticanalyzer.api.analysis.business;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestOutput;
import com.aaa.automaticanalyzer.model.User;

import java.util.List;

public interface AnalysisService {
    public void addBloodAnalysis(AnalysisRestInput analysis, final User user);

    public List<AnalysisRestOutput> getAnalysis(final User user);

}
