package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.productes.ProducteRestCtrl;
import com.drpicox.queue.MessageQueue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/caixesRegistradores")
public class CaixaRegistradoraRestCtrl {

    private MessageQueue messageQueue;
    private ProducteRestCtrl producteRestCtrl;

    public CaixaRegistradoraRestCtrl(MessageQueue messageQueue, ProducteRestCtrl producteRestCtrl) {
        this.messageQueue = messageQueue;
        this.producteRestCtrl = producteRestCtrl;
    }

    private List<CaixaRegistradoraEvent> events = new ArrayList<>();

    @PostMapping
    public void addCaixaRegistradora(@Param("caixaNumero") int caixaNumero, @Param("calaix") int calaix) {
        var caixes = getCaixes();
        var event = caixes.addCaixaRegistradora(caixaNumero, calaix);
        saveAndSend(event);
    }

    @PostMapping("/{caixaNumero}/registre")
    public void registraProducte(@PathVariable("caixaNumero") int caixaNumero, @Param("nom") String nom) {
        var caixes = getCaixes();
        var producte = producteRestCtrl.get(nom);
        var event = caixes.registraProducte(caixaNumero, producte);
        saveAndSend(event);
    }

    private void saveAndSend(CaixaRegistradoraEvent event) {
        events.add(event);
        messageQueue.send(event);
    }

    private CaixaRegistradoraDiccionari getCaixes() {
        return new CaixaRegistradoraDiccionari(events);
    }
}
