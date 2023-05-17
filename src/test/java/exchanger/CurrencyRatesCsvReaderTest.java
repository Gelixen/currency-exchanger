package exchanger;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyRatesCsvReaderTest {

    private CurrencyRatesReader currencyRatesReader;

    @Test
    public void readCurrencyRatesFromDataFile_sameCurrencyRatesExpected() throws IOException {
        currencyRatesReader = new CurrencyRatesCsvReader("data.csv");

        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("BTC", new BigDecimal("6977.089657"));
        expected.put("FKE", new BigDecimal("0.025"));
        expected.put("EUR", new BigDecimal("1"));
        expected.put("GBP", new BigDecimal("1.126695"));
        expected.put("USD", new BigDecimal("0.809552722"));
        expected.put("ETH", new BigDecimal("685.2944747"));

        HashMap<String, BigDecimal> currencyRates = currencyRatesReader.readCurrencyRates();

        assertEquals(currencyRates.size(), 6);
        assertEquals(currencyRates, expected);
    }

    @Test
    public void tryToReadCurrencyRatesFromNonExistingFile_throwFileNotFoundException() {
        currencyRatesReader = new CurrencyRatesCsvReader("not-data.csv");

        assertThrows(FileNotFoundException.class, () -> currencyRatesReader.readCurrencyRates());
    }
}