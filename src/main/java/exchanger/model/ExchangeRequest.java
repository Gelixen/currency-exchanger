package exchanger.model;

import java.math.BigDecimal;

public record ExchangeRequest(BigDecimal quantity, String initialCurrency, String finalCurrency) {
}
