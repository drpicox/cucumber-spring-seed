package com.drpicox.ddd;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class NiceRestSteps {

    @Autowired
    MockMvc mockMvc;

    @Given("you have a pleasent rest")
    public void you_have_a_nice_rest() throws Exception {
        var result = mockMvc.perform(get("/api/v1/nice")).andReturn();
        var response = result.getResponse();
        var responseStatus = response.getStatus();
        var responseBody = response.getContentAsString();
        assertThat(responseStatus).isEqualTo(200);
        assertThat(responseBody).isEqualTo("true");
    }

}
