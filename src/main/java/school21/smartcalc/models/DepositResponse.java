package school21.smartcalc.models;

import lombok.Data;

@Data
public class DepositResponse {
    private String status;
    private String message;
    private double profit;
    private double taxes;
    private double total;
}
