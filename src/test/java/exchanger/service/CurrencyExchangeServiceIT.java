package exchanger.service;

import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("slow")
@SpringBootTest
class CurrencyExchangeServiceIT {

    @Autowired
    private CurrencyExchangeService service;

    @Test
    void exchange_existentCurrencyName_returnExchangeRate() {
        String expectedExchangeRate = "1";
        ExchangeRequest exchangeRequest = new ExchangeRequest("EUR");

        String exchangeRate = service.exchange(exchangeRequest);

        assertEquals(expectedExchangeRate, exchangeRate);
    }

    @Test
    void exchange_nonexistentCurrencyName_throwCurrencyNotFoundException() {
        ExchangeRequest exchangeRequest = new ExchangeRequest("X");

        assertThrows(CurrencyNotFoundException.class, () -> service.exchange(exchangeRequest));
    }

}