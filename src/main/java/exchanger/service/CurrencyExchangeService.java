package exchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class CurrencyExchangeService {

    @Autowired
    private HashMap<String, BigDecimal> currencies;

    public String exchange(String request) {
        return currencies.toString();
    }
}
