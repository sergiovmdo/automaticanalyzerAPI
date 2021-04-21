package com.aaa.automaticanalyzer;

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
}
