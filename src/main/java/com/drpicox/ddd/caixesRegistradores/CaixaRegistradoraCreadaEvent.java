package com.drpicox.ddd.caixesRegistradores;

public class CaixaRegistradoraCreadaEvent extends CaixaRegistradoraEvent {
    private int calaix;
    public CaixaRegistradoraCreadaEvent(int caixaNumero, int calaix) {
        super(caixaNumero);
        this.calaix = calaix;
    }

    public int getCalaix() {
        return calaix;
    }
}
