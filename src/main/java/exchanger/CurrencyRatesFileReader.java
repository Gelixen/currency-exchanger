package exchanger;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRatesFileReader implements CurrencyRatesReader {

    private final String fileName;
    private final String COMMA_DELIMITER = ",";

    public CurrencyRatesFileReader(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<String, BigDecimal> readCurrencyRates() throws IOException {
        HashMap<String, BigDecimal> currencies = new HashMap<>();

        String path = getPathToFile();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String currencyName = values[0];
                BigDecimal currencyRate = new BigDecimal(values[1]);
                currencies.put(currencyName, currencyRate);
            }
        }

        printCurrencies(currencies);

        return currencies;
    }

    private String getPathToFile() {
        URL dataFile = ClassLoader.getSystemClassLoader().getResource(fileName);

        if (dataFile != null) {
            return dataFile.getPath();
        } else {
            return getPathWithPackage();
        }
    }

    private String getPathWithPackage() {
        return this.getClass().getPackage().getName() + "/" + fileName;
    }

    private void printCurrencies(HashMap<String, BigDecimal> currencies) {
        System.out.println();
        for (Map.Entry<String, BigDecimal> entry: currencies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

}
