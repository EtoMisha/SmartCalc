package school21.smartcalc.models;

import lombok.Data;

@Data
public class DepositResponse {
    private double percents;
    private double taxes;
    private double total;
}
