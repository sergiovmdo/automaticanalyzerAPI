package com.aaa.automaticanalyzer.processingengine;

import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.Medicine;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypercholesterolemiaAnalysis;

import java.util.ArrayList;
import java.util.List;

public class HyperCholersterolemiaEngine implements ProcessingEngine {

    public enum HyperCholersterolemiaMedicines {
        //Only avaiable in 20 or 40mg doses and used to reduce 27% and 34%
        PRAVASTATINA("Pravastatina"),
        //Only avaiable in 20 or 40mg doses and used to reduce 27% and 34%
        LOVASTATINA("Lovastatina"),
        //Only avaiable in 10, 20 or 40mg doses and used to reduce 27%, 34% and 41%
        SIMVASTATINA("Simvastatina"),
        //Only avaiable in 40 or 80mg doses and used to reduce 48% and 55%
        ATORVASTATINA("Atrovastatina");

        private String name;

        HyperCholersterolemiaMedicines(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    public List<Medicine> generateMedication(BaseAnalysis analysis) {
        HypercholesterolemiaAnalysis hcAnalysis = (HypercholesterolemiaAnalysis) analysis;
        int desiredDecrease = calculateDesiredCLDLDecrease(hcAnalysis.getCLDL(), hcAnalysis.getMaxCLDL());
        ArrayList<Medicine> medicines = new ArrayList<>();
        if (desiredDecrease >= 55) {
            Medicine medicine = new Medicine();
            medicine.setName(HyperCholersterolemiaMedicines.ATORVASTATINA.name());
            medicine.setDose(80d);
        } else if (desiredDecrease >= 48) {
            Medicine medicine = new Medicine();
            medicine.setName(HyperCholersterolemiaMedicines.ATORVASTATINA.name());
            medicine.setDose(40d);
        } else if (desiredDecrease >= 41) {
            Medicine medicine = new Medicine();
            medicine.setName(HyperCholersterolemiaMedicines.SIMVASTATINA.name());
            medicine.setDose(40d);
        }

        return medicines;
    }

    @Override
    public void decreaseMedication(List<Medication> medication) {
    }

    @Override
    public void increaseMedication(List<Medication> medication) {

    }

    @Override
    public boolean modifyMedication(User user, BaseAnalysis analysis) {
        return false;
    }

    @Override
    public double getNextDose(int currentDose) {
        return 0;
    }

    @Override
    public double getPreviousDose(int currentDose) {
        return 0;
    }

    private int calculateDesiredCLDLDecrease(String cldl, String maxCLDL) {
        double cLDLValue = Double.valueOf(cldl);
        double maxCLDLValue = Double.valueOf(maxCLDL);

        double decrease = cLDLValue / maxCLDLValue * 10;

        return (int) decrease;
    }
}
