package exchanger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    @PostMapping("/exchange")
    public ResponseEntity<String> exchange(@RequestBody String request) {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
