package exchanger;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CurrencyRatesFileReaderTest {

    private CurrencyRatesFileReader currencyRatesFileReader;

    @Test
    public void readCurrencyRatesFromDataFile_sameCurrencyRatesExpected() throws IOException {
        currencyRatesFileReader = new CurrencyRatesFileReader("data.csv");

        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("BTC", new BigDecimal("6977.089657"));
        expected.put("FKE", new BigDecimal("0.025"));
        expected.put("EUR", new BigDecimal("1"));
        expected.put("GBP", new BigDecimal("1.126695"));
        expected.put("USD", new BigDecimal("0.809552722"));
        expected.put("ETH", new BigDecimal("685.2944747"));

        HashMap<String, BigDecimal> currencyRates = currencyRatesFileReader.readCurrencyRatesFromFile();

        assertThat(currencyRates.size(), is(6));
        assertThat(currencyRates, is(expected));
    }

    @Test(expected = FileNotFoundException.class)
    public void tryToReadCurrencyRatesFromNonExistingFile_throwFileNotFoundException() throws IOException {
        currencyRatesFileReader = new CurrencyRatesFileReader("not-data.csv");

        currencyRatesFileReader.readCurrencyRatesFromFile();
    }
}