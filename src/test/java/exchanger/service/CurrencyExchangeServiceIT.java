package exchanger.service;

import exchanger.model.ExchangeRequest;
import exchanger.model.ExchangeResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("slow")
@SpringBootTest
class CurrencyExchangeServiceIT {

    @Autowired
    private CurrencyExchangeService service;

    @Test
    void exchange_eurToEur_returnSameQuantity() {
        String expectedExchangeRate = "12";
        ExchangeRequest exchangeRequest = new ExchangeRequest(new BigDecimal(expectedExchangeRate), "EUR", "EUR");

        ExchangeResponse exchangeRate = service.exchange(exchangeRequest);

        assertEquals(expectedExchangeRate, exchangeRate.quantity());
    }

    @Test
    void exchange_fkeToUsd_returnExchangeRate() {
        ExchangeRequest exchangeRequest = new ExchangeRequest(new BigDecimal("1"), "BTC", "ETH");

        ExchangeResponse exchangeRate = service.exchange(exchangeRequest);

        assertEquals("10.181155568275005676", exchangeRate.quantity());
    }

    @Test
    void exchange_nonexistentCurrencyName_throwCurrencyNotFoundException() {
        ExchangeRequest exchangeRequest = new ExchangeRequest(BigDecimal.TEN, "X", "Y");

        assertThrows(CurrencyNotFoundException.class, () -> service.exchange(exchangeRequest));
    }

}