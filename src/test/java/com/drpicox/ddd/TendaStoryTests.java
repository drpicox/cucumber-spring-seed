package com.drpicox.ddd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.truth.Truth.assertThat;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TendaStoryTests {

    @Autowired
    private TendaService tendaService;

    @Before
    public void clear() {
        tendaService.clear();
    }

    @Test
    public void vendre_un_producte() {
        /* given */
        caixa_registradora_numero_X_amb_Y_al_calaix(1, 0);
        hi_ha_producte_X_amb_preu_Y_i_codi_de_barres_Z("texans", 30, "73XNS");
        hi_ha_caixer_X("pere");
        el_caixer_X_s_identifica_a_la_caixa_registradora_numero_Y("pere", 1);

        /* when */
        escaneja_el_codi_de_barres_X_a_la_caixa_registradora_Y("73XNS", 1);
        finalitza_enregistrament_caixa_registradora_X(1);
        anota_a_caixa_registradora_numero_X_entregat_Y(1, 30);
        finalitza_transaccio_caixa_registradora_numero_X(1);

        /* then */
        la_caixa_registradora_numero_X_te_Y_al_calaix(1, 30);
        hi_ha_un_tiquet_nou();
        el_tiquet_te_una_linea_de_producte_X_per_preu_Y("texans", 30);
        el_tiquet_te_un_total_de_X(30);
        el_tiquet_te_en_X_per_caixer("pere");
    }

    private void el_tiquet_te_en_X_per_caixer(String pere) {

    }

    private void el_tiquet_te_un_total_de_X(int i) {

    }

    private void el_tiquet_te_una_linea_de_producte_X_per_preu_Y(String texans, int i) {

    }

    private void hi_ha_un_tiquet_nou() {

    }

    private void finalitza_transaccio_caixa_registradora_numero_X(int i) {

    }

    private void anota_a_caixa_registradora_numero_X_entregat_Y(int i, int i1) {

    }

    private void finalitza_enregistrament_caixa_registradora_X(int i) {

    }

    private void el_caixer_X_s_identifica_a_la_caixa_registradora_numero_Y(String pere, int i) {

    }

    private void hi_ha_caixer_X(String pere) {

    }

    private void hi_ha_producte_X_amb_preu_Y_i_codi_de_barres_Z(String texans, int i, String s) {

    }

    private void caixa_registradora_numero_X_amb_Y_al_calaix(int numeroCaixaRegistradora, int euros) {
        tendaService.afegirCaixaRegistradora(numeroCaixaRegistradora, 1);
    }

    private void escaneja_el_codi_de_barres_X_a_la_caixa_registradora_Y(String codi, int numeroCaixaRegistradora) {
        tendaService.escanejarCodideBarres(codi, numeroCaixaRegistradora);
    }

    private void la_caixa_registradora_numero_X_te_Y_al_calaix(int numeroCaixaRegistradora, int dinersEsperats) {
        var dinersExistents = tendaService.getCaixaRegistradoraCalaix();
        assertThat(dinersExistents).isEqualTo(dinersEsperats);
    }


}
