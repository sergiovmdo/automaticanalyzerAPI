package com.aaa.automaticanalyzer;

import com.aaa.automaticanalyzer.model.Language;

public enum Strings {
    HYPOTHYROIDISM("Hipotiroidismo", "Hipotiroidisme"),
    HYPERCHOLESTEROLEMIA("Hipercolesterolemia", "Hipercolesterolèmia");

    private String catalan;
    private String spanish;

    Strings(String spanish, String catalan){
        this.catalan = catalan;
        this.spanish = spanish;
    }

    public String getCatalan(){
        return catalan;
    }

    public String getSpanish(){
        return spanish;
    }

    public static Strings getStringFromObject(Object o){
        for (int i = 0 ; i < Strings.values().length ; i++){
            if (Strings.values()[i].toString().equals(o.toString()))
                return Strings.values()[i];
        }
        return null;
    }

    public String getLanguage(Language language){
        switch (language){
            case CATALAN:
                return catalan;
            case CASTELLANO:
                return spanish;
            default:
                return spanish;
        }
    }
}
