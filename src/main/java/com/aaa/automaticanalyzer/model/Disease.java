package com.aaa.automaticanalyzer.model;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypercholesterolemiaAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypothyroidismAnalysis;
import lombok.SneakyThrows;

public enum Disease {
    DIABETES(HypercholesterolemiaAnalysis.class),
    HYPOTHYROIDISM(HypothyroidismAnalysis.class),
    TRANSPLANT(HypercholesterolemiaAnalysis.class),
    HYPERCHOLESTEROLEMIA(HypercholesterolemiaAnalysis.class);

    private Class<? extends BaseAnalysis> data;

    Disease(final Class<? extends BaseAnalysis> data) {
        this.data = data;
    }

    @SneakyThrows
    public void addAnalysisData(final Analysis analysis, final AnalysisRestInput analysisRestInput) {
        BaseAnalysis baseAnalysis = data.newInstance();
        baseAnalysis.parseInput(analysisRestInput);
        analysis.setAnalysisData(baseAnalysis.toJson());
    }

    @SneakyThrows
    public BaseAnalysis getAnalisis(final String str) {
        BaseAnalysis baseAnalysis = data.newInstance();
        return baseAnalysis.fromJson(str);
    }
}
