package com.aaa.automaticanalyzer.model;

public enum Language {
    CASTELLANO("Castellano"), CATALAN("Català");

    private String name;

    Language(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Language getFromString(String language){
        for (int i = 0 ; i < Language.values().length ; i++){
            String l = Language.values()[i].getName();
            if (l.equalsIgnoreCase(language.toLowerCase()))
                return Language.values()[i];
        }

        return null;
    }
}
