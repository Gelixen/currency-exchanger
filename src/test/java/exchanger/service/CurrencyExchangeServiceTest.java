package exchanger.service;

import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceTest {

    @Mock
    private HashMap<String, BigDecimal> currencies;

    @InjectMocks
    private CurrencyExchangeService service;

    @Test
    void exchange_mapContainsCurrency_returnExchangeRate() {
        BigDecimal expectedValue = BigDecimal.ONE;
        String currency = "BTC";
        ExchangeRequest exchangeRequest = new ExchangeRequest(currency);
        when(currencies.get(currency)).thenReturn(expectedValue);

        String response = service.exchange(exchangeRequest);

        assertEquals(expectedValue.toString(), response);
    }

    @Test
    void exchange_mapDoesNotContainCurrency_throwCurrencyNotFoundException() {
        String currency = "ANY";
        ExchangeRequest exchangeRequest = new ExchangeRequest(currency);
        when(currencies.get(anyString())).thenReturn(null);

        assertThrows(CurrencyNotFoundException.class, () -> service.exchange(exchangeRequest));
    }
}