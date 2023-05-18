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

    private final HashMap<String, BigDecimal> currencyRateMap;

    public CurrencyExchangeService(HashMap<String, BigDecimal> currencyRateMap) {
        this.currencyRateMap = currencyRateMap;
    }

    public ExchangeResponse exchange(ExchangeRequest request) {
        BigDecimal initialCurrencyRate = getCurrencyRate(request.initialCurrency());
        BigDecimal finalCurrencyRate = getCurrencyRate(request.finalCurrency());

        BigDecimal quantityInEur = request.quantity().multiply(initialCurrencyRate);
        BigDecimal quantityInFinalCurrency = quantityInEur.divide(finalCurrencyRate, 18, RoundingMode.DOWN);

        return new ExchangeResponse(quantityInFinalCurrency.stripTrailingZeros().toPlainString());
    }

    private BigDecimal getCurrencyRate(String currencyName) {
        return Optional.ofNullable(currencyRateMap.get(currencyName))
                .orElseThrow(() -> new CurrencyNotFoundException(currencyName));
    }
}
