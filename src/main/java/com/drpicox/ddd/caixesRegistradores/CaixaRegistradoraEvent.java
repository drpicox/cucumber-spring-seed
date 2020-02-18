package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.Event;

public class CaixaRegistradoraEvent extends Event {
    private int caixaNumero;

    public CaixaRegistradoraEvent(int caixaNumero) {
        this.caixaNumero = caixaNumero;
    }

    public int getCaixaNumero() {
        return caixaNumero;
    }
}
