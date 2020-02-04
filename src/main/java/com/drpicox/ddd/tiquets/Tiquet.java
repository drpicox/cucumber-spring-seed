package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.caixesRegistradores.ProducteRegistratEvent;

public class Tiquet {
    private String tiquetId;
    private int numeroCaixa;
    private int valor;

    public Tiquet(String tiquetId, int numeroCaixa) {
        this.tiquetId = tiquetId;
        this.numeroCaixa = numeroCaixa;
        this.valor = 0;
    }

    public int getValor() {
        return valor;
    }

    public void onProducteRegistrat(ProducteRegistratEvent event) {
        valor += event.getPreu();
    }
}
