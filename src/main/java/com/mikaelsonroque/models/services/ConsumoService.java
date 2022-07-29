package com.mikaelsonroque.models.services;

import com.mikaelsonroque.models.ConsumoAnual;
import com.mikaelsonroque.models.ConsumoHistorico;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConsumoService {

    public LinkedHashMap<String, Double> calcularConsumoMedioMensal(ConsumoHistorico consumoHistorico){
        LinkedHashMap<String, Double> meses = new LinkedHashMap<>();
        meses.put("JAN", 0.0); meses.put("FEV", 0.0); meses.put("MAR", 0.0); meses.put("ABR", 0.0);
        meses.put("MAI", 0.0); meses.put("JUN", 0.0); meses.put("JUL", 0.0); meses.put("AGO", 0.0);
        meses.put("SET", 0.0); meses.put("OUT", 0.0); meses.put("NOV", 0.0); meses.put("DEZ", 0.0);

        for (ConsumoAnual consumoAnual : consumoHistorico.getListaConsumo()){
            for (String mes : consumoAnual.getMeses().keySet()){
                meses.replace(mes, meses.get(mes) + consumoAnual.getMeses().get(mes));
            }
        }
        for (String mes : meses.keySet()){
            meses.replace(mes, meses.get(mes)/consumoHistorico.getListaConsumo().size());
        }
        return meses;
    }

    public String calcularMaiorGastoMensal(ConsumoHistorico consumoHistorico){
        Integer anoDoMaiorGastoMensal = 0;
        String mesMaiorGasto = "";
        Double valorGasto = 0.0;

        for (ConsumoAnual consumoAnual : consumoHistorico.getListaConsumo()){
            for (String mes : consumoAnual.getMeses().keySet()){
                if (consumoAnual.getMeses().get(mes) > valorGasto){
                    valorGasto = consumoAnual.getMeses().get(mes);
                    mesMaiorGasto = mes;
                    anoDoMaiorGastoMensal = consumoAnual.getAno();
                }
            }
        }
        return "Ano: " + anoDoMaiorGastoMensal + ", mes: " + mesMaiorGasto + ", valor: R$" + valorGasto;
    }

    public String calcularMaiorGastoAnual(ConsumoHistorico consumoHistorico){
        Integer anoDoMaiorGasto = 0;
        Double valorGasto = 0.0;

        for (ConsumoAnual consumoAnual : consumoHistorico.getListaConsumo()){
            Double somaValores = 0.0;
            for (Double valorMensal : consumoAnual.getMeses().values()){
                somaValores += valorMensal;
            }
            if (somaValores > valorGasto){
                valorGasto = somaValores;
                anoDoMaiorGasto = consumoAnual.getAno();
            }
        }
        return "Ano: " + anoDoMaiorGasto + ", valor gasto: R$" + valorGasto;
    }

}
