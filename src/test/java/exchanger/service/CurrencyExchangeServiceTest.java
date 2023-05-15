package exchanger.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceTest {

    @Mock
    private HashMap<String, BigDecimal> currencies;

    @InjectMocks
    private CurrencyExchangeService service;

    @Test
    void exchange() {
        when(currencies.toString()).thenReturn("{}");

        String response = service.exchange(null);

        assertEquals("{}", response);
    }
}