package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.Event;
import com.drpicox.ddd.EventDiscriminator;
import com.drpicox.ddd.caixesRegistradores.ProducteRegistratEvent;

import java.util.*;

public class TiquetsDictionary {

    private Map<String, Tiquet> tiquets = new HashMap<>();
    private Map<Integer, Tiquet> tiquetsByCaixaNumero = new HashMap<>();

    public TiquetsDictionary(List<TiquetEvent> events) {
        var discriminator = new EventDiscriminator();
        discriminator.addListener(TiquetCreatEvent.class, this::onCreat);
        discriminator.fallbackListener(this::onOther);
        discriminator.processAll(events);
    }

    private void onCreat(TiquetCreatEvent ev) {
        var tiquetId = ev.getTiquetId();
        var caixaNumero = ev.getNumeroCaixa();
        var tiquet = new Tiquet(tiquetId, caixaNumero);
        tiquets.put(tiquetId, tiquet);
        tiquetsByCaixaNumero.put(caixaNumero, tiquet);
    }

    private void onOther(TiquetEvent ev) {
        var tiquetId = ev.getTiquetId();
        var tiquet = tiquets.get(tiquetId);
        tiquet.process(ev);
    }

    public TiquetsDictionary() {
        this(new ArrayList<>());
    }


    public TiquetCreatEvent createTiquet(int caixaNumero) {
        String tiquetId = UUID.randomUUID().toString();
        return new TiquetCreatEvent(tiquetId, caixaNumero);
    }

    public int getValor(String tiquetId) {
        return tiquets.get(tiquetId).getValor();
    }

    public TiquetRegistreAfegitEvent onProducteRegistrat(ProducteRegistratEvent event) {
        var tiquet = tiquetsByCaixaNumero.get(event.getCaixaNumero());
        return tiquet.onProducteRegistrat(event);
    }
}
