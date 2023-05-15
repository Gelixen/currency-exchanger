package exchanger.controller;

import exchanger.service.CurrencyExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeService service;

    @Test
    void exchange_success() throws Exception {
        when(service.exchange("{}"))
                .thenReturn("success");

        mockMvc.perform(
                        post("/api/exchange")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    void exchange_failure() throws Exception {
        when(service.exchange("{}"))
                .thenThrow(RuntimeException.class);

        mockMvc.perform(
                        post("/api/exchange")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(""));
    }
}