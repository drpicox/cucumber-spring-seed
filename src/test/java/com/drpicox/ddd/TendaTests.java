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
public class TendaTests {

    @Autowired
    private TendaService tendaService;

    @Before
    public void clear() {
        tendaService.clear();
    }

    @Test
    public void vendre_un_producte() {
        tendaService.addProduct("Pantalons", 30);
        tendaService.vendre("Pantalons");

        assertThat(tendaService.dinersEnCaixa()).isEqualTo(30);
    }

    @Test
    public void stockatge() {
        tendaService.addProduct("Pantalons", 30);
        tendaService.addProduct("Polars", 40);
        tendaService.vendre("Pantalons");

        assertThat(tendaService.solicitarStocksDe("Polars")).isEqualTo(0);
        assertThat(tendaService.solicitarStocksDe("Pantalons")).isEqualTo(1);
    }
}
