package exchanger;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyExchangeTest {

    @Test
    public void missingArgument_printsUsageInstructions() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR"});
    }

    @Test
    public void allArgumentsALetter_tryToParseNumber_throwNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> CurrencyExchange.main(new String[]{"data.csv", "a", "EUR", "EUR"}));
    }

    @Test
    public void nonExistingFileNameAsArgument_throwFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> CurrencyExchange.main(new String[]{"test.csv", "5.555", "EUR", "EUR"}));
    }

    @Test
    public void nonExistingCurrencyFrom_throwCurrencyNotFoundException() {
        assertThrows(CurrencyNotFoundException.class, () -> CurrencyExchange.main(new String[]{"data.csv", "5.555", "AAA", "EUR"}));
    }

    @Test
    public void nonExistingCurrencyTo_throwCurrencyNotFoundException() {
        assertThrows(CurrencyNotFoundException.class, () -> CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR", "BBB"}));
    }

    @Test
    public void allArgumentsGood_printsResult() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR", "EUR"});
    }
}