package exchanger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public interface CurrencyRatesReader {
    HashMap<String, BigDecimal> readCurrencyRates() throws IOException;
}
