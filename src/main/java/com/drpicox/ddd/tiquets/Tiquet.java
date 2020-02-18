package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.Event;
import com.drpicox.ddd.EventDiscriminator;
import com.drpicox.ddd.caixesRegistradores.ProducteRegistratEvent;

public class Tiquet {
    private String tiquetId;
    private int numeroCaixa;
    private int valor;
    private EventDiscriminator discriminator;

    public Tiquet(String tiquetId, int numeroCaixa) {
        this.tiquetId = tiquetId;
        this.numeroCaixa = numeroCaixa;
        this.valor = 0;

        discriminator = new EventDiscriminator();
        discriminator.addListener(TiquetRegistreAfegitEvent.class, this::onRegistreAfegit);
    }

    public int getValor() {
        return valor;
    }

    public TiquetRegistreAfegitEvent onProducteRegistrat(ProducteRegistratEvent event) {
        return new TiquetRegistreAfegitEvent(tiquetId, event.getPreu());
    }

    public void process(TiquetEvent ev) {
        discriminator.process(ev);
    }

    private void onRegistreAfegit(TiquetRegistreAfegitEvent ev) {
        valor += ev.getPreu();
    }
}
