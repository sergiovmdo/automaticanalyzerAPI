package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public abstract class BaseAnalysis<T> {

    ObjectMapper om = new ObjectMapper();

    @SneakyThrows
    public String toJson() {
        return om.writeValueAsString(this);
    }

    public abstract BaseAnalysis<T> fromJson(final String json);

    public abstract void parseInput(final AnalysisRestInput analysisRestInput);
}
