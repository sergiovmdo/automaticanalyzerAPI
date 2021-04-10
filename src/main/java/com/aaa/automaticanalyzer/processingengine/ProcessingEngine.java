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

    public void increaseMedication(List<Medication> medication);

    public void decreaseMedication(List<Medication> medication);

    public void modifyMedication(User user, BaseAnalysis analysis);

    public double getNextDose(int currentDose);

    public double getPreviousDose(int currentDose);
}
