package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper = true)
@Data
public class HypothyroidismAnalysis extends BaseAnalysis<HypothyroidismAnalysis> {
    private String TSH;
    private String maxTSH = "4.78";
    private String minTSH = "0.55";

    @SneakyThrows
    @Override
    public BaseAnalysis<HypothyroidismAnalysis> fromJson(String json) {
        return om.readValue(json, HypothyroidismAnalysis.class);
    }

    @Override
    public void parseInput(AnalysisRestInput analysisRestInput) {
        setTSH(analysisRestInput.getTSH());
    }
}
