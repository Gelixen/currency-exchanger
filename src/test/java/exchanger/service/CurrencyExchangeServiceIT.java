package exchanger.service;

import exchanger.CurrencyNotFoundException;
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

        String exchangeRate = service.exchange("EUR");

        assertEquals(expectedExchangeRate, exchangeRate);
    }

    @Test
    void exchange_nonexistentCurrencyName_throwCurrencyNotFoundException() {
        assertThrows(CurrencyNotFoundException.class, () -> service.exchange("X"));
    }

}