package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.EventDiscriminator;
import com.drpicox.ddd.productes.Producte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaRegistradoraDiccionari {

    private Map<Integer, CaixaRegistradora> caixes = new HashMap<>();


    public CaixaRegistradoraDiccionari(List<CaixaRegistradoraEvent> events) {
        var eventDiscriminator = new EventDiscriminator();
        eventDiscriminator.addListener(CaixaRegistradoraCreadaEvent.class, this::onCreada);
        eventDiscriminator.fallbackListener(this::onOthers);

        eventDiscriminator.processAll(events);
    }

    private void onCreada(CaixaRegistradoraCreadaEvent event) {
        var caixa = new CaixaRegistradora(event);
        caixes.put(event.getCaixaNumero(), caixa);
    }

    private void onOthers(CaixaRegistradoraEvent event) {
        var caixa = caixes.get(event.getCaixaNumero());
        caixa.process(event);
    }

    public CaixaRegistradoraDiccionari() {
        this(List.of());
    }

    public CaixaRegistradoraCreadaEvent addCaixaRegistradora(int caixaNumero, int calaix) {
        caixes.put(caixaNumero, new CaixaRegistradora(caixaNumero, calaix));
        return new CaixaRegistradoraCreadaEvent(caixaNumero, calaix);
    }

    public ProducteRegistratEvent registraProducte(int caixaNumero, Producte producte) {
        return caixes.get(caixaNumero).registra(producte);
    }
}
