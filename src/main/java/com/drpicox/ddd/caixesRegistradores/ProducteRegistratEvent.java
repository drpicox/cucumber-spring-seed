package com.drpicox.ddd.caixesRegistradores;

import java.io.Serializable;

public class ProducteRegistratEvent extends CaixaRegistradoraEvent {
    private String nom;
    private int preu;

    public ProducteRegistratEvent(int caixaNumero, String nom, int preu) {
        super(caixaNumero);
        this.nom = nom;
        this.preu = preu;
    }

    public int getPreu() {
        return preu;
    }
}
