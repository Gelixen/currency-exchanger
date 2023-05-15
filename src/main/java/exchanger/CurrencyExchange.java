package exchanger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyExchange {

    public static void main(String[] args) throws IOException {

        if (args.length != 4) {
            System.out.println("Usage: \"java exchanger.CurrencyExchange <currencyRatesFileName> <amount> <currencyFrom> <currencyTo>\"");
            System.out.println("E.g. \"java exchanger.CurrencyExchange data.csv 5 BTC EUR\"");
            return;
        } else {
            System.out.println("Input args: " + String.join(" ", args));
        }

        String fileName = args[0];
        BigDecimal amount = new BigDecimal(args[1]);
        String currencyFrom = args[2];
        String currencyTo = args[3];

        HashMap<String, BigDecimal> currencyRates = new CurrencyRatesCsvReader(fileName).readCurrencyRates();
        printCurrencies(currencyRates);

        BigDecimal exchangedAmount = new Exchanger(currencyRates).exchange(amount, currencyFrom, currencyTo);
        System.out.println("Result: " + exchangedAmount);
    }

    private static void printCurrencies(HashMap<String, BigDecimal> currencies) {
        System.out.println();
        for (Map.Entry<String, BigDecimal> entry : currencies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

}
