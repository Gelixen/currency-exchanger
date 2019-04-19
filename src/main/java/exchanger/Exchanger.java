package exchanger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Exchanger {

    private final HashMap<String, BigDecimal> currencies;

    public Exchanger(HashMap<String, BigDecimal> currencies) {
        this.currencies = currencies;
    }

    public BigDecimal exchange(BigDecimal amount, String currencyNameFrom, String currencyNameTo) {
        BigDecimal currencyRateFrom = getCurrencyRateIfCurrencyExists(currencyNameFrom);
        BigDecimal currencyRateTo = getCurrencyRateIfCurrencyExists(currencyNameTo);

        BigDecimal amountInEur = amount.multiply(currencyRateFrom);
        BigDecimal amountInSelectedCurrency = amountInEur.divide(currencyRateTo, 18, RoundingMode.DOWN);

        return amountInSelectedCurrency.stripTrailingZeros();
    }

    private BigDecimal getCurrencyRateIfCurrencyExists(String currencyName) {
        BigDecimal currencyRate = currencies.get(currencyName);

        if (currencyRate == null) {
            throw new CurrencyNotFoundException(currencyName);
        }

        return currencyRate;
    }
}
