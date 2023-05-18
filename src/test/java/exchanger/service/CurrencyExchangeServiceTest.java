package exchanger.service;

import exchanger.model.ExchangeRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyExchangeServiceTest {

    private static CurrencyExchangeService service;

    @BeforeAll
    static void setUp() {
        HashMap<String, BigDecimal> currencyRateMap = generateCurrencyRateMap();
        service = new CurrencyExchangeService(currencyRateMap);
    }

    private static HashMap<String, BigDecimal> generateCurrencyRateMap() {
        HashMap<String, BigDecimal> currencyRateMap = new HashMap<>();

        currencyRateMap.put("EUR", new BigDecimal("1"));
        currencyRateMap.put("GBP", new BigDecimal("1.126695"));
        currencyRateMap.put("FKE", new BigDecimal("0.025"));
        currencyRateMap.put("USD", new BigDecimal("0.809552722"));
        currencyRateMap.put("BTC", new BigDecimal("6977.089657"));
        currencyRateMap.put("ETH", new BigDecimal("685.2944747"));

        return currencyRateMap;
    }

    private static Stream<Arguments> exchange_successful() {
        return Stream.of(
                Arguments.of("EUR", "EUR", "66", "66"),
                Arguments.of("EUR", "USD", "100", "123.525000018466987502"),
                Arguments.of("EUR", "ETH", "2000", "2.918453415045460602"),
                Arguments.of("ETH", "FKE", "1", "27411.778988"),
                Arguments.of("BTC", "BTC", "13", "13"),
                Arguments.of("BTC", "EUR", "10", "69770.89657"),
                Arguments.of("BTC", "ETH", "2", "20.362311136550011352"),
                Arguments.of("FKE", "BTC", "6977.089657", "0.025"),
                Arguments.of("GBP", "BTC", "7000", "1.130394675677879138"),
                Arguments.of("USD", "GBP", "14", "10.059277895082520114")
        );
    }

    @MethodSource
    @ParameterizedTest
    void exchange_successful(String initialCurrency, String finalCurrency, String quantity, String expectedQuantity) {
        ExchangeRequest request = new ExchangeRequest(new BigDecimal(quantity), initialCurrency, finalCurrency);

        String exchangedQuantity = service.exchange(request).quantity();

        assertEquals(expectedQuantity, exchangedQuantity);
    }


    private static Stream<Arguments> exchange_invalidCurrency_throwException() {
        return Stream.of(
                Arguments.of("EUR", "Z"),
                Arguments.of("EUR", ""),
                Arguments.of("BTC", null),
                Arguments.of("BTCX", null),
                Arguments.of("Y", "USD"),
                Arguments.of("   ", "USD"),
                Arguments.of(null, "FKE"),
                Arguments.of(null, null)
        );
    }

    @MethodSource
    @ParameterizedTest
    void exchange_invalidCurrency_throwException(String initialCurrency, String finalCurrency) {
        ExchangeRequest request = new ExchangeRequest(BigDecimal.TEN, initialCurrency, finalCurrency);

        assertThrows(CurrencyNotFoundException.class, () -> service.exchange(request));
    }

}