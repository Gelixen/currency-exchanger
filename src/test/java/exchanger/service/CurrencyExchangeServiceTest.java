package exchanger.service;

import exchanger.model.ExchangeRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyExchangeServiceTest {

    private static CurrencyExchangeService service;

    @BeforeAll
    static void setUp() {
        HashMap<String, BigDecimal> currenciesMap = generateCurrenciesMap();
        service = new CurrencyExchangeService(currenciesMap);
    }

    private static HashMap<String, BigDecimal> generateCurrenciesMap() {
        HashMap<String, BigDecimal> currenciesMap = new HashMap<>();

        currenciesMap.put("EUR", new BigDecimal("1"));
        currenciesMap.put("GBP", new BigDecimal("1.126695"));
        currenciesMap.put("FKE", new BigDecimal("0.025"));
        currenciesMap.put("USD", new BigDecimal("0.809552722"));
        currenciesMap.put("BTC", new BigDecimal("6977.089657"));
        currenciesMap.put("ETH", new BigDecimal("685.2944747"));

        return currenciesMap;
    }

    @Test
    void EUR_to_EUR() {
        assertEquals("66", getExchange("66", "EUR", "EUR"));
    }

    @Test
    void BTC_to_BTC() {
        assertEquals("15.2145678", getExchange("15.2145678", "BTC", "BTC"));
    }

    @Test
    void BTC_to_EUR() {
        assertEquals("69770.89657", getExchange("10", "BTC", "EUR"));
    }

    @Test
    void EUR_to_ETH() {
        assertEquals("2.918453415045460602", getExchange("2000", "EUR", "ETH"));
    }

    @Test
    void BTC_to_ETH() {
        assertEquals("20.362311136550011352", getExchange("2", "BTC", "ETH"));
    }

    @Test
    void GBP_to_BTC() {
        assertEquals("1.130394675677879138", getExchange("7000", "GBP", "BTC"));
    }

    @Test
    void ETH_to_FKE() {
        assertEquals("27411.778988", getExchange("1", "ETH", "FKE"));
    }

    @Test
    void badCurrencyFrom_throwException() {
        assertThrows(CurrencyNotFoundException.class, () -> getExchange("5", "YYY", "EUR"));
    }

    @Test
    void badCurrencyTo_throwException() {
        assertThrows(CurrencyNotFoundException.class, () -> getExchange("5", "EUR", "ZZZ"));
    }

    private String getExchange(String quantity, String initialCurrency, String finalCurrency) {
        ExchangeRequest request = new ExchangeRequest(new BigDecimal(quantity), initialCurrency, finalCurrency);
        return service.exchange(request).quantity().toString();
    }
}