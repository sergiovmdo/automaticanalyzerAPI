package com.aaa.automaticanalyzer.processingengine;

import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.Medicine;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.Analysis;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HyperCholesterolemiaAnalysis;

import java.util.List;

public interface ProcessingEngine {
    public List<Medicine> generateMedication(BaseAnalysis analysis);

    public List<Medicine> increaseMedication(List<Medication> medication);

    public List<Medicine> decreaseMedication(List<Medication> medication);

    public List<Medicine> modifyMedication(User user, BaseAnalysis analysis);
}
