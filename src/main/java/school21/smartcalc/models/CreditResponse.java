package school21.smartcalc.models;

import lombok.Data;

@Data
public class CreditResponse {
    private double monthlyPayment;
    private double overPayment;
    private double totalPayment;
}
