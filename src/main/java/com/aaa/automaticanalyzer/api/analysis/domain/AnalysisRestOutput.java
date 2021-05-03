package com.aaa.automaticanalyzer.api.analysis.domain;

import lombok.Data;

@Data
public class AnalysisRestOutput {
    private String disease;
    private Long date;

    private String analysisData;
}
