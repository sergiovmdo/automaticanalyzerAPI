package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.model.Language;
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
    private String units = "mg";

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

    @Override
    public String formatContent(String content, Language language) {
        String[] contents = content.split(",");
        String cldlValue = contents[1];
        cldlValue = cldlValue.split(":")[1];
        cldlValue = cldlValue.replace("\"", "");
        String totalCholesterolValue = contents[2];
        totalCholesterolValue = totalCholesterolValue.split(":")[1];
        totalCholesterolValue = totalCholesterolValue.replace("\"", "");
        return Strings.CLDL.getLanguage(language) + ": " + cldlValue + "/" + maxCLDL + " " + units + "\n\n" + Strings.COLESTEROL.getLanguage(language) + ": " + totalCholesterolValue + "/" + maxTotalCholesterol + " " + units + "\n";
    }
}
