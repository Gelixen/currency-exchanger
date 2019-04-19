package exchanger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyExchange {

    public static void main(String[] args) throws IOException {

        if (args.length != 4) {
            System.out.println("Usage: \"java exchanger.CurrencyExchange <currencyRatesFileName> <amount> <currencyFrom> <currencyTo>\"");
            System.out.println("i.e. \"java exchanger.CurrencyExchange data.csv 5 BTC EUR\"");
            return;
        }

        String fileName = args[0];
        BigDecimal amount = new BigDecimal(args[1]);
        String currencyFrom = args[2];
        String currencyTo = args[3];

        HashMap<String, BigDecimal> currencyRates = new CurrencyRatesFileReader(fileName).readCurrencyRatesFromFile();

        BigDecimal exchangedAmount = new Exchanger(currencyRates).exchange(amount, currencyFrom, currencyTo);
        System.out.println(exchangedAmount);
    }

}
