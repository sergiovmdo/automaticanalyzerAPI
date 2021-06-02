package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper = true)
@Data
public class HypothyroidismAnalysis extends BaseAnalysis<HypothyroidismAnalysis> {
    private String tsh;
    private String maxTSH = "4.78";
    private String minTSH = "0.55";
    private String units = "µUI/mL";

    @SneakyThrows
    @Override
    public BaseAnalysis<HypothyroidismAnalysis> fromJson(String json) {
        return om.readValue(json, HypothyroidismAnalysis.class);
    }

    @Override
    public void parseInput(AnalysisRestInput analysisRestInput) {
        setTsh(analysisRestInput.getTsh());
    }

    @Override
    public String formatContent(String content, Language language) {
        return Strings.TSH.getLanguage(language) + ": " + tsh + " " + units +
                "  (" + minTSH + " - " + maxTSH + ")";
    }
}
