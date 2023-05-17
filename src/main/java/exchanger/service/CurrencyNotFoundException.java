package exchanger.service;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currencyName) {
        super(String.format("Currency '%s' does not exist.", currencyName));
    }
}
