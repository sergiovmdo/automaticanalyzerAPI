package com.aaa.automaticanalyzer.api.analysis.rest.mapping;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.Disease;

public class AnalysisMapper {
    public static Analysis createAnalysisFromRestInput(AnalysisRestInput input){
        Analysis analysis = new Analysis();
        analysis.setDisease(getDisease(input.getDisease()));
        return analysis;
    }

    public static Disease getDisease(String disease){
        for (int i = 0 ; i < Disease.values().length ; i++){
            if (Disease.values()[i].toString().toLowerCase().equals(disease.toLowerCase())){
                return Disease.values()[i];
            }
        }
        return  null;
    }
}
