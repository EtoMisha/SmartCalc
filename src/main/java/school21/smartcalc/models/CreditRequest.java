package school21.smartcalc.models;

import lombok.Data;

@Data
public class CreditRequest {
    private final double amount;
    private final int period;
    private final double percent;
    private final String type;
}
