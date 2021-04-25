package com.aaa.automaticanalyzer.model;

import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypercholesterolemiaAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypothyroidismAnalysis;
import com.aaa.automaticanalyzer.processingengine.HyperCholersterolemiaEngine;
import com.aaa.automaticanalyzer.processingengine.HypothyroidismEngine;
import com.aaa.automaticanalyzer.processingengine.ProcessingEngine;
import lombok.SneakyThrows;

public enum Disease {
    HYPOTHYROIDISM(HypothyroidismAnalysis.class, new HypothyroidismEngine()),
    HYPERCHOLESTEROLEMIA(HypercholesterolemiaAnalysis.class, new HyperCholersterolemiaEngine());

    private Class<? extends BaseAnalysis> data;
    private ProcessingEngine engine;

    Disease(final Class<? extends BaseAnalysis> data, ProcessingEngine engine) {
        this.data = data;
        this.engine = engine;
    }

    public ProcessingEngine getEngine() {
        return engine;
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
