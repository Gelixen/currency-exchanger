package exchanger.service.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;

public class CurrencyRatesCsvReader implements CurrencyRatesReader {

    private final String fileName;

    public CurrencyRatesCsvReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public HashMap<String, BigDecimal> readCurrencyRates() throws IOException {
        HashMap<String, BigDecimal> currencyRates = new HashMap<>();

        String path = getPathToResource();

        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        for (CSVRecord record : records) {
            String currencyName = record.get(0);
            BigDecimal exchangeRate = new BigDecimal(record.get(1));

            currencyRates.put(currencyName, exchangeRate);
        }

        return currencyRates;
    }

    private String getPathToResource() throws FileNotFoundException {
        URL resource = CurrencyRatesCsvReader.class.getClassLoader().getResource(fileName);

        if (resource == null) {
            throw new FileNotFoundException("Resource not found: " + fileName);
        }

        return resource.getPath();
    }

}
