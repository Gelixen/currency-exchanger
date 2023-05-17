package exchanger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import exchanger.model.ExchangeResponse;
import exchanger.service.CurrencyExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeService service;

    @Test
    void exchange_success() throws Exception {
        ExchangeRequest request = new ExchangeRequest("EUR");
        BigDecimal expectedRate = new BigDecimal("10.123456789");
        ExchangeResponse response = new ExchangeResponse(expectedRate);

        when(service.exchange(request)).thenReturn(response);

        mockMvc.perform(
                        post("/api/exchange")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rate", comparesEqualTo(expectedRate.doubleValue())));
    }

    @Test
    void exchange_throwException_returnServerError() throws Exception {
        when(service.exchange(any(ExchangeRequest.class)))
                .thenThrow(CurrencyNotFoundException.class);

        mockMvc.perform(
                        post("/api/exchange")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(CurrencyNotFoundException.ERROR_MESSAGE));
    }

    private String asJsonString(final Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}