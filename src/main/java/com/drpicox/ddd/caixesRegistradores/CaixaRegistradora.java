package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.productes.Producte;

public class CaixaRegistradora {
    private int caixaNumero;
    private int calaix;

    public CaixaRegistradora(int caixaNumero, int calaix) {
        this.caixaNumero = caixaNumero;
        this.calaix = calaix;
    }

    public ProducteRegistratEvent registra(Producte producte) {
        return new ProducteRegistratEvent(
                caixaNumero,
                producte.getNom(),
                producte.getPreu()
        );
    }
}
