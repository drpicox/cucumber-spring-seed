package com.drpicox.ddd.tiquets;

public class TiquetRegistreAfegitEvent extends TiquetEvent {

    private int preu;

    public TiquetRegistreAfegitEvent(String tiquetId, int preu) {
        super(tiquetId);
        this.preu = preu;
    }

    public int getPreu() {
        return preu;
    }
}
