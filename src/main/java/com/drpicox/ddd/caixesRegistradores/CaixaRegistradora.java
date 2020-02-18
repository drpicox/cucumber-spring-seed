package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.Event;
import com.drpicox.ddd.EventDiscriminator;
import com.drpicox.ddd.productes.Producte;

public class CaixaRegistradora {
    private int caixaNumero;
    private int calaix;
    private EventDiscriminator eventDiscriminator;

    public CaixaRegistradora(int caixaNumero, int calaix) {
        this.caixaNumero = caixaNumero;
        this.calaix = calaix;

        eventDiscriminator = new EventDiscriminator();
        eventDiscriminator.addListener(ProducteRegistratEvent.class, this::onRegistrat);
    }

    private void onRegistrat(ProducteRegistratEvent event) {
    }

    public CaixaRegistradora(CaixaRegistradoraCreadaEvent event) {
        this(event.getCaixaNumero(), event.getCalaix());
    }

    public ProducteRegistratEvent registra(Producte producte) {
        return new ProducteRegistratEvent(
                caixaNumero,
                producte.getNom(),
                producte.getPreu()
        );
    }

    public void process(CaixaRegistradoraEvent event) {
        eventDiscriminator.process(event);
    }
}
