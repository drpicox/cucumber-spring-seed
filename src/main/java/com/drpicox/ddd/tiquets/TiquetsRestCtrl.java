package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.caixesRegistradores.ProducteRegistratEvent;
import com.drpicox.ddd.productes.Producte;
import com.drpicox.queue.MessageQueue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tiquets")
public class TiquetsRestCtrl {

    private MessageQueue messageQueue;

    public TiquetsRestCtrl(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        messageQueue.addMessageListener(ProducteRegistratEvent.class, this::onProducteRegistrat);
    }

    private Map<String, Tiquet> tiquets = new HashMap<>();

    @PostMapping
    public String addTiquet(@Param("caixaNumero") int caixaNumero) {
        String tiquetId = UUID.randomUUID().toString();
        tiquets.put(tiquetId, new Tiquet(tiquetId, caixaNumero));
        return tiquetId;
    }

    @GetMapping("/{tiquetId}/valor")
    public int getValor(@PathVariable("tiquetId") String tiquetId) {
        return tiquets.get(tiquetId).getValor();
    }

    private void onProducteRegistrat(ProducteRegistratEvent event) {
        tiquets.values().forEach(tiquet -> tiquet.onProducteRegistrat(event));
    }
}
