package com.drpicox.ddd;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TendaService {

    private List<Product> products = new LinkedList<>();
    private List<Product> venuts = new LinkedList<>();
    private int dinersEnCaixa = 0;

    public void clear() {
        products.clear();
        venuts.clear();
        dinersEnCaixa = 0;
    }

    public void addProduct(String name, int price) {
        products.add(new Product(name, price));
    }

    public void vendre(String name) {
        var product = products.stream().filter(p -> p.hasName(name)).findAny().get();
        venuts.add(product);
        dinersEnCaixa += product.getPrice();
    }

    public int dinersEnCaixa() {
        return dinersEnCaixa;
    }

    public int solicitarStocksDe(String type) {
        return (int) venuts.stream().filter(p -> p.hasName(type)).count();
    }

    public void afegirCaixaRegistradora(int numeroCaixaRegistradora, int i) {
    }

    public void escanejarCodideBarres(String codi, int numeroCaixaRegistradora) {
    }

    public int getCaixaRegistradoraCalaix() {
        return 1;
    }
}
