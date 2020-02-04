package com.drpicox.ddd.productes;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/productes")
public class ProducteRestCtrl {

    private Map<String, Producte> productes = new HashMap<>();

    @PostMapping
    public void addProducte(@Param("nom") String nom, @Param("preu") int preu) {
        productes.put(nom, new Producte(nom, preu));
    }

    public Producte get(String nom) {
        return productes.get(nom);
    }
}
