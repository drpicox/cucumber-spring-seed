package com.drpicox.ddd;

import com.drpicox.queue.MessageQueue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TendaStepdefs {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private MessageQueue messageQueue;

    private String currentTiquetId;

    @Given("la caixa registradora {int} amb {int} euros al calaix")
    public void laCaixaRegistradoraAmbEurosAlCalaix(int caixaNumero, int calaix) throws Exception {

        mockMvc.perform(post("/api/v1/caixesRegistradores")
                .param("caixaNumero", "" + caixaNumero)
                .param("calaix", "" + calaix))
                .andExpect(status().isOk())
        ;
    }

    @And("el producte amb nom (\\w+) amb preu (\\d+) euros")
    public void elProducteAmbPreuEuros(String nom, int preu) throws Exception {
        mockMvc.perform(post("/api/v1/productes")
                .param("nom", nom)
                .param("preu", "" + preu))
                .andExpect(status().isOk())
        ;
    }

    @When("s'obre un tiquet caixa registradora {int}")
    public void sObreUnTiquetCaixaRegistradora(int caixaNumero) throws Exception {
        currentTiquetId = mockMvc.perform(post("/api/v1/tiquets/")
                .param("caixaNumero", "" + caixaNumero))
                .andReturn().getResponse().getContentAsString();
    }

    @Then("es registre el producte amb nom (\\w+) a la caixa registradora (\\d+)")
    public void esRegistreElProducte(String nom, int caixaNumero) throws Exception {
        mockMvc.perform(post("/api/v1/caixesRegistradores/" + caixaNumero + "/registre")
                .param("nom", nom))
                .andExpect(status().isOk())
                ;
        messageQueue.deliverMessages();
    }

    @And("es tanca el tiquet")
    public void esTancaElTiquet() {
    }


    @Then("hi ha {int} euros al calaix de la caixa registradora {int}")
    public void hiHaEurosAlCalaixDeLaCaixaRegistradora(int arg0, int arg1) {

    }

    @Then("el tiquet te un valor de {int} euros")
    public void elTiquetTeUnValorDeEuros(int valor) throws Exception {
        var actual = Integer.parseInt(
                mockMvc.perform(get("/api/v1/tiquets/" + currentTiquetId + "/valor"))
                .andReturn().getResponse().getContentAsString()
        );

        assertThat(actual).isEqualTo(valor);
    }
}
