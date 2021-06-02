package com.aaa.automaticanalyzer.api.analysis.rest.mapping;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestOutput;
import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.utils.Utils;

public class AnalysisMapper {
    public static Analysis createAnalysisFromRestInput(AnalysisRestInput input){
        Analysis analysis = new Analysis();
        analysis.setDisease(Utils.getDiseaseFromString(input.getDisease()));
        return analysis;
    }

    public static AnalysisRestOutput mapAnalysisToOutput(Analysis analysis, Language language){
        AnalysisRestOutput analysisRestOutput = new AnalysisRestOutput();
        analysisRestOutput.setAnalysisData(analysis.getDisease().formatAnalysisData(analysis.getAnalysisData(), language));
        analysisRestOutput.setDate(analysis.getDate());
        analysisRestOutput.setDisease(Strings.getStringFromObject(analysis.getDisease()).getLanguage(language));
        analysisRestOutput.setModified(analysis.isModified());
        return analysisRestOutput;
    }
}
