package exchanger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CurrencyRatesLoader {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRatesLoader.class);

    @Bean
    @Scope("singleton")
    public HashMap<String, BigDecimal> loadCurrencyRates() throws IOException {
        LOG.info("Currency rates loading started...");

        HashMap<String, BigDecimal> currencies = new CurrencyRatesCsvReader("data.csv").readCurrencyRates();
        printCurrencies(currencies);

        LOG.info("Currency rates loading finished.");

        return currencies;
    }

    public static void printCurrencies(HashMap<String, BigDecimal> currencies) {
        System.out.println();
        for (Map.Entry<String, BigDecimal> entry : currencies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }
}