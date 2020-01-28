package com.drpicox.ddd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Counter {

    @Id
    @GeneratedValue
    private Long id;

    private int count = 0;

    public Counter() {}

    public void increment() {
        this.count += 1;
    }

    public void dump() {
        System.out.println("counter " + id + " - " + count);
    }

}
