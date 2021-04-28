package com.aaa.automaticanalyzer.processingengine;

import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.Medicine;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;

import java.util.List;

public interface ProcessingEngine {
    public List<Medicine> generateMedication(BaseAnalysis analysis);

    public void increaseMedication(List<Medication> medication);

    public void decreaseMedication(List<Medication> medication);

    public boolean modifyMedication(User user, BaseAnalysis analysis);

    public double getNextDose(int currentDose, String medicineName);

    public double getPreviousDose(int currentDose, String medicineName);
}
