package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.caixesRegistradores.ProducteRegistratEvent;
import com.drpicox.ddd.productes.Producte;
import com.drpicox.queue.MessageQueue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/tiquets")
public class TiquetsRestCtrl {

    private MessageQueue messageQueue;

    public TiquetsRestCtrl(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        messageQueue.addMessageListener(ProducteRegistratEvent.class, this::onProducteRegistrat);
    }

    private List<TiquetEvent> events = new ArrayList<>();

    @PostMapping
    public String addTiquet(@Param("caixaNumero") int caixaNumero) {
        var tiquets = getTiquets();
        var event = tiquets.createTiquet(caixaNumero);
        saveAndSend(event);
        return event.getTiquetId();
    }

    @GetMapping("/{tiquetId}/valor")
    public int getValor(@PathVariable("tiquetId") String tiquetId) {
        var tiquets = getTiquets();
        return tiquets.getValor(tiquetId);
    }

    private void onProducteRegistrat(ProducteRegistratEvent event) {
        var tiquets = getTiquets();
        var consequenceEvent = tiquets.onProducteRegistrat(event);
        saveAndSend(consequenceEvent);
    }

    private void saveAndSend(TiquetEvent event) {
        events.add(event);
        messageQueue.send(event);
    }

    private TiquetsDictionary getTiquets() {
        return new TiquetsDictionary(events);
    }
}
