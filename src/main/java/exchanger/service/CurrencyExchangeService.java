package exchanger.service;

import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CurrencyExchangeService {

    @Autowired
    private HashMap<String, BigDecimal> currencies;

    public String exchange(ExchangeRequest request) {
        String currency = request.currency();
        return Optional.ofNullable(currencies.get(currency))
                .orElseThrow(() -> new CurrencyNotFoundException(currency))
                .toString();
    }
}
