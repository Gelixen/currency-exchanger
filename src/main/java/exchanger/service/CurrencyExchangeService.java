package exchanger.service;

import exchanger.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CurrencyExchangeService {

    @Autowired
    private HashMap<String, BigDecimal> currencies;

    public String exchange(String request) {
        return Optional.ofNullable(currencies.get(request))
                .orElseThrow(() -> new CurrencyNotFoundException(request))
                .toString();
    }
}
