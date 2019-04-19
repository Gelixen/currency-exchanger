package exchanger;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ExchangerTest {

    private Exchanger exchanger;

    @Before
    public void setUp() {
        HashMap<String, BigDecimal> currencies = generateCurrenciesMap();
        exchanger = new Exchanger(currencies);
    }

    private HashMap<String, BigDecimal> generateCurrenciesMap() {
        HashMap<String, BigDecimal> currenciesMap = new HashMap<>();

        currenciesMap.put("EUR", new BigDecimal("1"));
        currenciesMap.put("GBP", new BigDecimal("1.126695"));
        currenciesMap.put("USD", new BigDecimal("0.809552722"));
        currenciesMap.put("ETH", new BigDecimal("685.2944747"));
        currenciesMap.put("BTC", new BigDecimal("6977.089657"));
        currenciesMap.put("FKE", new BigDecimal("0.025"));

        return currenciesMap;
    }

    @Test
    public void EUR_to_EUR() {
        assertEquals(new BigDecimal("66"), getExchange("66", "EUR", "EUR"));
    }

    @Test
    public void BTC_to_EUR() {
        assertEquals(new BigDecimal("69770.89657"), getExchange("10", "BTC", "EUR"));
    }

    @Test
    public void EUR_to_ETH() {
        assertEquals(new BigDecimal("2.918453415045460602"), getExchange("2000", "EUR", "ETH"));
    }

    @Test
    public void BTC_to_ETH() {
        assertEquals(new BigDecimal("20.362311136550011352"), getExchange("2", "BTC", "ETH"));
    }

    @Test
    public void GBP_to_BTC() {
        assertEquals(new BigDecimal("1.130394675677879138"), getExchange("7000", "GBP", "BTC"));
    }

    @Test
    public void ETH_to_FKE() {
        assertEquals(new BigDecimal("27411.778988"), getExchange("1", "ETH", "FKE"));
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void badCurrencyFrom_throwException() {
        getExchange("5", "YYY", "EUR");
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void badCurrencyTo_throwException() {
        getExchange("5", "EUR", "ZZZ");
    }

    private BigDecimal getExchange(String ammount, String currencyFrom, String currencyTo) {
        return exchanger.exchange(new BigDecimal(ammount), currencyFrom, currencyTo);
    }

    @Test
    public void roundingTest() {
        BigDecimal three = new BigDecimal("3");
        BigDecimal two = new BigDecimal("2");

        assertEquals(new BigDecimal("0.666666666666666666"), two.divide(three, 18, RoundingMode.DOWN));
    }
}