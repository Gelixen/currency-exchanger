package exchanger.service;

import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import exchanger.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CurrencyExchangeService {

    @Autowired
    private HashMap<String, BigDecimal> currencies;

    public ExchangeResponse exchange(ExchangeRequest request) {
        String currency = request.initialCurrency();
        return Optional.ofNullable(currencies.get(currency))
                .map(ExchangeResponse::new)
                .orElseThrow(() -> new CurrencyNotFoundException(currency));
    }
}
