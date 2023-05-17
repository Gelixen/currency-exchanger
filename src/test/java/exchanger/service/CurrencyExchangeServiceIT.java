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
    void exchange_existentCurrencyName_returnExchangeRate() {
        BigDecimal expectedExchangeRate = BigDecimal.ONE;
        ExchangeRequest exchangeRequest = new ExchangeRequest(expectedExchangeRate, "EUR", "EUR");

        ExchangeResponse exchangeRate = service.exchange(exchangeRequest);

        assertEquals(expectedExchangeRate, exchangeRate.rate());
    }

    @Test
    void exchange_nonexistentCurrencyName_throwCurrencyNotFoundException() {
        ExchangeRequest exchangeRequest = new ExchangeRequest(BigDecimal.TEN, "X", "Y");

        assertThrows(CurrencyNotFoundException.class, () -> service.exchange(exchangeRequest));
    }

}