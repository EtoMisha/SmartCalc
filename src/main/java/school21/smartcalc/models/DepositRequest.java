package school21.smartcalc.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DepositRequest {
    private double amount;
    private int period;
    private double tax;
    private String frequency;
    private boolean capitalisation;
    private Map<LocalDate, Double> deposits;
    private Map<LocalDate, Double> withdraws;
}
