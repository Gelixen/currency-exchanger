package exchanger;

public class CurrencyNotFoundException extends RuntimeException {
    CurrencyNotFoundException(String currencyName) {
        super(currencyName);
    }
}
