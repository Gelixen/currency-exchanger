package exchanger.service;

import exchanger.model.ExchangeRequest;
import exchanger.model.ExchangeResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CurrencyExchangeService {

    private final HashMap<String, BigDecimal> currencies;

    public CurrencyExchangeService(HashMap<String, BigDecimal> currencies) {
        this.currencies = currencies;
    }

    public ExchangeResponse exchange(ExchangeRequest request) {
        BigDecimal initialCurrencyRate = getCurrencyRate(request.initialCurrency());
        BigDecimal finalCurrencyRate = getCurrencyRate(request.finalCurrency());

        BigDecimal quantityInEur = request.quantity().multiply(initialCurrencyRate);
        BigDecimal quantityInFinalCurrency = quantityInEur.divide(finalCurrencyRate, 18, RoundingMode.DOWN);

        return new ExchangeResponse(quantityInFinalCurrency.stripTrailingZeros());
    }

    private BigDecimal getCurrencyRate(String currencyName) {
        return Optional.ofNullable(currencies.get(currencyName))
                .orElseThrow(() -> new CurrencyNotFoundException(currencyName));
    }
}
