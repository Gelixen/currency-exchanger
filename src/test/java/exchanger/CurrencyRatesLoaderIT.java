package exchanger;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("slow")
@SpringBootTest
class CurrencyRatesLoaderIT {

    private final HashMap<String, BigDecimal> expectedCurrencyRates = new HashMap<>() {{
        put("EUR", new BigDecimal("1"));
        put("USD", new BigDecimal("0.809552722"));
        put("GBP", new BigDecimal("1.126695"));
        put("BTC", new BigDecimal("6977.089657"));
        put("ETH", new BigDecimal("685.2944747"));
        put("FKE", new BigDecimal("0.025"));
    }};

    @Autowired
    private HashMap<String, BigDecimal> currencyRates;

    CurrencyRatesLoaderIT() {
    }

    @Test
    void testLoadCurrencyRates() {
        assertEquals(6, currencyRates.size());
        assertEquals(expectedCurrencyRates, currencyRates);
    }
}