package school21.smartcalc.core;

public class CreditCalc {
    private final double amount;
    private final double period;
    private final double percent;

    private double monthlyPayment;
    private double overPayment;
    private double totalPayment;

    private int monthCount;

    public CreditCalc(double amount, double period, double percent) throws Exception {
        if (period <= 0) {
            throw new Exception("Срок кредита должен быть больше 0");
        }

        if (percent < 0) {
            throw new Exception("Процентная ставка должна быть больше или равна 0");
        }

        this.amount = amount;
        this.period = period;
        this.percent = percent;
    }

    public void calculateDifferentiated() {
        double currentAmount = amount - (amount / period) * monthCount;
        monthlyPayment = amount / period + currentAmount * percent / 1200;
        totalPayment = monthlyPayment;
        overPayment = currentAmount * percent / 1200;

        monthCount++;
    }

    public void calculateAnnuity() {
        if (percent == 0.0) {
            monthlyPayment = amount / period;
        } else {
            monthlyPayment = (amount * (percent / 1200)) / (1 - Math.pow(1 + percent / 1200, -period));
        }

        totalPayment = monthlyPayment * period;
        overPayment = totalPayment - amount;
    }

    public double getMonthlyPayment() {
        return Math.round(monthlyPayment * 100.0) / 100.0;
    }

    public double getOverPayment() {
        return Math.round(overPayment * 100.0) / 100.0;
    }

    public double getTotalPayment() {
        return Math.round(totalPayment * 100.0) / 100.0;
    }
}
