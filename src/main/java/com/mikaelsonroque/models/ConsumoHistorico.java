package com.mikaelsonroque.models;

import java.util.ArrayList;
import java.util.List;

public class ConsumoHistorico {

    private List<ConsumoAnual> listaConsumo;

    public ConsumoHistorico(){

    }

    public ConsumoHistorico(List<ConsumoAnual> listaConsumo) {
        this.listaConsumo = listaConsumo;
    }

    public List<ConsumoAnual> getListaConsumo() {
        if (listaConsumo == null){
            listaConsumo = new ArrayList<>();
        }
        return listaConsumo;
    }

    public void setListaConsumo(List<ConsumoAnual> listaConsumo) {
        this.listaConsumo = listaConsumo;
    }
}
