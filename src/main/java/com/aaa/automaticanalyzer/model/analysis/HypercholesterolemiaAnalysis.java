package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper = true)
@Data
public class HypercholesterolemiaAnalysis extends BaseAnalysis<HypercholesterolemiaAnalysis> {

    private String cLDL;
    private String totalCholesterol;
    private String maxCLDL = "100";
    private String maxTotalCholesterol = "200";

    @SneakyThrows
    @Override
    public HypercholesterolemiaAnalysis fromJson(String json) {
        return om.readValue(json, HypercholesterolemiaAnalysis.class);
    }

    @Override
    public void parseInput(AnalysisRestInput analysisRestInput) {
        setCLDL(analysisRestInput.getCLDL());
        setTotalCholesterol(analysisRestInput.getTotalCholesterol());
    }
}
