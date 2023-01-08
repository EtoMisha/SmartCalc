package school21.smartcalc.models;

import lombok.Data;

@Data
public class CreditResponse {
    private String status;
    private String message;
    private String monthlyPayment;
    private double overPayment;
    private double totalPayment;
}
