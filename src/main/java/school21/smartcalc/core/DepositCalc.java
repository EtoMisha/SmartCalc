package school21.smartcalc.core;

import java.util.List;
import java.util.Objects;

public class DepositCalc {
    private static final double NO_TAX_AMOUNT = 75000.0;

    private double amount;
    private double percent;
    private double period;
    private double tax;
    private String frequency;
    private boolean capitalisation;
    private List<Double> deposits;
    private List<Double> withdrawals;

    private double profit;
    private double taxes;
    private double total;

    public void calculate() throws Exception {
        if (period <= 0) {
            throw new Exception("Срок кредита должен быть больше 0");
        }
        if (percent < 0 || tax < 0) {
            throw new Exception("Ставка должна быть больше либо равна 0");
        }

        amount = amount + deposits.stream().filter(Objects::nonNull).reduce(0.0, Double::sum)
                - withdrawals.stream().filter(Objects::nonNull).reduce(0.0, Double::sum);

        if (capitalisation) {
            int count = 0;
            switch (frequency) {
                case "monthly":
                    count = (int) period;
                    break;
                case "quarterly":
                    count = (int) period / 3;
                    break;
                case "yearly":
                    count = (int) period / 12;
                    break;
            }

            double newAmount = amount;
            double profitTemp;
            for (int i = 0; i < count; i++) {
                profitTemp = newAmount * (percent / 100) * ((period / count) / 12);
                newAmount += profitTemp;
                profit += profitTemp;
            }

        } else {
            profit = amount * (percent / 100) * (period / 12);
        }

        double yearProfit = profit / (period / 12);
        if (yearProfit > NO_TAX_AMOUNT) {
            taxes = ((yearProfit - NO_TAX_AMOUNT) * tax / 100) * period / 12;
        }

        total = amount + profit - taxes;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setCapitalisation(boolean capitalisation) {
        this.capitalisation = capitalisation;
    }

    public void setDeposits(List<Double> deposits) {
        this.deposits = deposits;
    }

    public void setWithdrawals(List<Double> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public double getProfit() {
        return profit;
    }

    public double getTaxes() {
        return taxes;
    }

    public double getTotal() {
        return total;
    }
}
