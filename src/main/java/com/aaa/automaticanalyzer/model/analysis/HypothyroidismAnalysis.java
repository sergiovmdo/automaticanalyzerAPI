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
    private String TSH;
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
        setTSH(analysisRestInput.getTSH());
    }

    @Override
    public String formatContent(String content, Language language) {
        String[] contents = content.split(",");
        String tshValue = contents[1];
        tshValue = tshValue.split(":")[1];
        tshValue = tshValue.replace("\"", "");
        return Strings.TSH.getLanguage(language) + ": " + minTSH + " > " + tshValue + " < " + maxTSH + " " + units;
    }
}
