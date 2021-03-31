package com.aaa.automaticanalyzer.api.analysis.domain;

import lombok.Data;

@Data
public class AnalysisRestInput {
    private String disease;

    /* Start of hypercholesterolemia parameters */

    private String cLDL;
    private String totalCholesterol;

    /* End of hypercholesterolemia parameters */
}
