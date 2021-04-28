package com.aaa.automaticanalyzer.api.analysis.domain;

import com.aaa.automaticanalyzer.model.Disease;
import lombok.Data;

@Data
public class AnalysisRestOutput {
    private String disease;
    private Long date;

    private String analysisData;
}
