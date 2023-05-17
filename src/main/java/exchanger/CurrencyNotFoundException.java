package exchanger;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String currencyName) {
        super(currencyName);
    }
}
