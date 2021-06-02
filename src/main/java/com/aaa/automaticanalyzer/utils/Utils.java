package com.aaa.automaticanalyzer.utils;

import com.aaa.automaticanalyzer.model.Disease;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Disease getDiseaseFromString(String disease){
        for (int i = 0 ; i < Disease.values().length ; i++){
            if (Disease.values()[i].toString().toLowerCase().equals(disease.toLowerCase())){
                return Disease.values()[i];
            }
        }
        return  null;
    }

    public static Long getDateFromString(String date, String time){
        //dd_MM_yyyy-hh:mm:sss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        String completeDate = date + '-' + time;
        try {
            Date d = simpleDateFormat.parse(completeDate);
            return  d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}