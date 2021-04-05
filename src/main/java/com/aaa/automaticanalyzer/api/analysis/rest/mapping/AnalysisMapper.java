package com.aaa.automaticanalyzer.api.analysis.rest.mapping;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.Disease;
import com.aaa.automaticanalyzer.utils.Utils;

public class AnalysisMapper {
    public static Analysis createAnalysisFromRestInput(AnalysisRestInput input){
        Analysis analysis = new Analysis();
        analysis.setDisease(Utils.getDiseaseFromString(input.getDisease()));
        return analysis;
    }

}
