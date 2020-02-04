package com.drpicox.ddd.caixesRegistradores;

import java.io.Serializable;

public class ProducteRegistratEvent implements Serializable {
    private int caixaNumero;
    private String nom;
    private int preu;

    public ProducteRegistratEvent(int caixaNumero, String nom, int preu) {
        this.caixaNumero = caixaNumero;
        this.nom = nom;
        this.preu = preu;
    }

    public int getPreu() {
        return preu;
    }
}
