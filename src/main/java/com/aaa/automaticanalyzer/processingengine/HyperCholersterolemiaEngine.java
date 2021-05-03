package com.aaa.automaticanalyzer.processingengine;

import com.aaa.automaticanalyzer.model.Disease;
import com.aaa.automaticanalyzer.model.Medication;
import com.aaa.automaticanalyzer.model.Medicine;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.analysis.BaseAnalysis;
import com.aaa.automaticanalyzer.model.analysis.HypercholesterolemiaAnalysis;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HyperCholersterolemiaEngine implements ProcessingEngine {
    private MessagingService messagingService;

    public enum HyperCholersterolemiaMedicines {
        //Only avaiable in 20 or 40mg doses and used to reduce 27% and 34%
        PRAVASTATINA("Pravastatina", 20d, 40d),
        //Only avaiable in 20 or 40mg doses and used to reduce 27% and 34%
        LOVASTATINA("Lovastatina", 20d, 40d),
        //Only avaiable in 10, 20 or 40mg doses and used to reduce 27%, 34% and 41%
        SIMVASTATINA("Simvastatina", 10d, 20d, 40d),
        //Only avaiable in 40 or 80mg doses and used to reduce 48% and 55%
        ATORVASTATINA("Atrovastatina", 40d, 80d);

        private String name;
        private double[] doses;

        HyperCholersterolemiaMedicines(String name, double... doses) {
            this.name = name;
            this.doses = doses;
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
        /* There is no possible cases of decreasing patien's hypercholesterolemia medication using this system */
    }

    @Override
    public void increaseMedication(List<Medication> medication) {
        for (Medication m : medication){
            if (m.getDisease().equals(Disease.HYPERCHOLESTEROLEMIA)){
                Medicine medicine = m.getMedicines().get(0);
                double dose = getNextDose(medicine.getDose().intValue(), medicine.getName());
                if (dose > 0)
                    medicine.setDose(dose);
                else {
                    m.setNeedsRevision(true);
                }
            }
        }

    }

    @Override
    public boolean modifyMedication(User user, BaseAnalysis analysis) {
        HypercholesterolemiaAnalysis hta = (HypercholesterolemiaAnalysis) analysis;
        if (calculateDesiredCLDLDecrease(hta.getCLDL(), hta.getMaxCLDL()) < 0 && user.isMedicated(Disease.HYPERCHOLESTEROLEMIA)) {
            increaseMedication(user.getMedications());
            return true;
        }

        return false;
    }

    @Override
    public double getNextDose(int currentDose, String medicineName) {
        HyperCholersterolemiaMedicines hcm = null;
        for (int i = 0 ; i < HyperCholersterolemiaMedicines.values().length ; i++){
            if (HyperCholersterolemiaMedicines.values()[i].getName().equals(medicineName)) {
                hcm = HyperCholersterolemiaMedicines.values()[i];
                break;
            }
        }

        if (hcm != null){
            for (int i = 0; i < hcm.doses.length ; i++){
                if (hcm.doses[i] == currentDose){
                    if (i+1 < hcm.doses.length){
                        return hcm.doses[i+1];
                    } else {
                        return -1d;
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public double getPreviousDose(int currentDose, String medicineName) {
        return 0;
    }

    private int calculateDesiredCLDLDecrease(String cldl, String maxCLDL) {
        double cLDLValue = Double.parseDouble(cldl);
        double maxCLDLValue = Double.parseDouble(maxCLDL);

        double decrease = (maxCLDLValue - cLDLValue)/maxCLDLValue * 100;

        return (int) decrease;
    }
}
