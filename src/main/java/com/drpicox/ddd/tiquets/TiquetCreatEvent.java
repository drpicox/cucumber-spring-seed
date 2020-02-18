package com.drpicox.ddd.tiquets;

public class TiquetCreatEvent extends TiquetEvent {

    private int numeroCaixa;

    public TiquetCreatEvent(String tiquetId, int numeroCaixa) {
        super(tiquetId);
        this.numeroCaixa = numeroCaixa;
    }

    public int getNumeroCaixa() {
        return numeroCaixa;
    }
}
