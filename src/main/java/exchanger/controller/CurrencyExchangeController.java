package exchanger.controller;

import exchanger.CurrencyNotFoundException;
import exchanger.model.ExchangeRequest;
import exchanger.model.ExchangeResponse;
import exchanger.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @PostMapping("/exchange")
    public ResponseEntity exchange(@RequestBody ExchangeRequest request) {
        try {
            ExchangeResponse response = currencyExchangeService.exchange(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CurrencyNotFoundException.ERROR_MESSAGE);
        }
    }
}
