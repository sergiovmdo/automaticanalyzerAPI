package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper = true)
@Data
public class HyperCholesterolemiaAnalysis extends BaseAnalysis<HyperCholesterolemiaAnalysis> {

    private String cLDL;
    private String totalCholesterol;

    @SneakyThrows
    @Override
    public HyperCholesterolemiaAnalysis fromJson(String json) {
        return om.readValue(json, HyperCholesterolemiaAnalysis.class);
    }

    @Override
    public void parseInput(AnalysisRestInput analysisRestInput) {
        setCLDL(analysisRestInput.getCLDL());
        setTotalCholesterol(analysisRestInput.getTotalCholesterol());
    }
}
