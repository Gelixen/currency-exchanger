package exchanger;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CurrencyExchangeTest {

    @Test
    public void missingArgument_printsUsageInstructions() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR"});
    }

    @Test(expected = NumberFormatException.class)
    public void allArgumentsALetter_tryToParseNumber_throwNumberFormatException() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "a", "EUR", "EUR"});
    }

    @Test(expected = FileNotFoundException.class)
    public void nonExistingFileNameAsArgument_throwFileNotFoundException() throws IOException {
        CurrencyExchange.main(new String[]{"test.csv", "5.555", "EUR", "EUR"});
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void nonExistingCurrencyFrom_throwCurrencyNotFoundException() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "AAA", "EUR"});
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void nonExistingCurrencyTo_throwCurrencyNotFoundException() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR", "BBB"});
    }

    @Test
    public void allArgumentsGood_printsResult() throws IOException {
        CurrencyExchange.main(new String[]{"data.csv", "5.555", "EUR", "EUR"});
    }
}