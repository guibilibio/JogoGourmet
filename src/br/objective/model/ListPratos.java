package br.objective.model;

import java.util.ArrayList;
import java.util.List;

public class ListPratos {
    
    private List<Prato> pratos = new ArrayList<>();

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
    
}
