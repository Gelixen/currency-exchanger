package exchanger.controller;

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
    public ResponseEntity<Object> exchange(@RequestBody ExchangeRequest request) {
        ExchangeResponse response = currencyExchangeService.exchange(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
