package com.drpicox.ddd.caixesRegistradores;

import com.drpicox.ddd.productes.ProducteRestCtrl;
import com.drpicox.queue.MessageQueue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/caixesRegistradores")
public class CaixaRegistradoraRestCtrl {

    private MessageQueue messageQueue;
    private ProducteRestCtrl producteRestCtrl;

    public CaixaRegistradoraRestCtrl(MessageQueue messageQueue, ProducteRestCtrl producteRestCtrl) {
        this.messageQueue = messageQueue;
        this.producteRestCtrl = producteRestCtrl;
    }

    private Map<Integer, CaixaRegistradora> caixes = new HashMap<>();

    @PostMapping
    public void addCaixaRegistradora(@Param("caixaNumero") int caixaNumero, @Param("calaix") int calaix) {
        caixes.put(caixaNumero, new CaixaRegistradora(caixaNumero, calaix));
    }

    @PostMapping("/{caixaNumero}/registre")
    public void registraProducte(@PathVariable("caixaNumero") int caixaNumero, @Param("nom") String nom) {
        var producte = producteRestCtrl.get(nom);
        var event = caixes.get(caixaNumero).registra(producte);
        messageQueue.send(event);
    }
}
