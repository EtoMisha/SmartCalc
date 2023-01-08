package school21.smartcalc.models;

import lombok.Data;

import java.util.List;

@Data
public class DepositRequest {
    private double amount;
    private double period;
    private double percent;
    private double tax;
    private String frequency;
    private boolean capitalisation;
    private List<Double> deposits;
    private List<Double> withdrawals;
}
