package com.aaa.automaticanalyzer.api.analysis.domain;

import lombok.Data;

//IF A PARAMETER IS NOT FILLED IN POSTMAN, NP IT WILL APPEAR AS NULL
@Data
public class AnalysisRestInput {
    private String disease;

    /* Start of hypercholesterolemia parameters */

    private String cLDL;
    private String totalCholesterol;

    /* End of hypercholesterolemia parameters */
}
