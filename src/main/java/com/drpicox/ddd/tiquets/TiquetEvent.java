package com.drpicox.ddd.tiquets;

import com.drpicox.ddd.Event;

public class TiquetEvent extends Event {

    private String tiquetId;

    public TiquetEvent(String tiquetId) {
        this.tiquetId = tiquetId;
    }

    public String getTiquetId() {
        return tiquetId;
    }
}
