package com.mikaelsonroque.models;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConsumoAnual {

    private Integer ano;
    private LinkedHashMap<String, Double> meses;

    public ConsumoAnual(Integer ano, LinkedHashMap<String, Double> listaMeses) {
        this.ano = ano;
        this.meses = listaMeses;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public HashMap<String, Double> getMeses() {
        if (meses.isEmpty()){
            meses.putAll(inicializarHashMapMeses());
        }
        return meses;
    }

    public void setListaMeses(LinkedHashMap<String, Double> meses) {
        this.meses = meses;
    }

    public HashMap<String, Double> inicializarHashMapMeses(){
        LinkedHashMap<String, Double> meses = new LinkedHashMap<>();
        meses.put("JAN", 0.0); meses.put("FEV", 0.0); meses.put("MAR", 0.0); meses.put("ABR", 0.0);
        meses.put("MAI", 0.0); meses.put("JUN", 0.0); meses.put("JUL", 0.0); meses.put("AGO", 0.0);
        meses.put("SET", 0.0); meses.put("OUT", 0.0); meses.put("NOV", 0.0); meses.put("DEZ", 0.0);
        return meses;
    }
}
