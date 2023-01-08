package school21.smartcalc.core;

public class CreditCalc {
    private final double amount;
    private final double period;
    private final double percent;

    private double monthlyPayment;
    private double overPayment;
    private double totalPayment;

    private int monthCount;

    public CreditCalc(double amount, double period, double percent) {
        this.amount = amount;
        this.period = period;
        this.percent = percent;
    }

    public void calculateDifferentiated() throws Exception {
        if (period <= 0) {
            throw new Exception("Срок кредита должен быть больше 0");
        }

        double currentAmount = amount - (amount / period) * monthCount;
        monthlyPayment = amount / period + currentAmount * percent / 1200;
        totalPayment = monthlyPayment;
        overPayment = currentAmount * percent / 1200;

        monthCount++;
    }

    public void calculateAnnuity() throws Exception {
        if (period <= 0) {
            throw new Exception("Срок кредита должен быть больше 0");
        }

        if (percent == 0) {
            monthlyPayment = amount / period;
        } else {
            monthlyPayment = (amount * (percent / 1200)) / (1 - Math.pow(1 + percent / 1200, -period));
        }

        totalPayment = monthlyPayment * period;
        overPayment = totalPayment - amount;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getOverPayment() {
        return overPayment;
    }

    public double getTotalPayment() {
        return totalPayment;
    }
}
