package exchanger;

public class CurrencyNotFoundException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Specified currency does not exist.";

    public CurrencyNotFoundException(String currencyName) {
        super(currencyName);
    }
}
