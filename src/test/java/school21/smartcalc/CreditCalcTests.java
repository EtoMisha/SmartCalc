package school21.smartcalc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import school21.smartcalc.core.CreditCalc;
import school21.smartcalc.core.MathCalc;

public class CreditCalcTests {

    private double amount = 600000;
    private double period = 24;
    private double percent = 10;

    @Test
    void calculateAnnuity() throws Exception {
        double checkMonthlyPayment = 27686.96;
        double checkOverPayment = 64486.94;
        double checkTotalPayment = 664486.94;

        CreditCalc creditCalc = new CreditCalc(amount, period, percent);
        creditCalc.calculateAnnuity();
        double monthlyPayment = creditCalc.getMonthlyPayment();
        double overPayment = creditCalc.getOverPayment();
        double totalPayment = creditCalc.getTotalPayment();

        Assertions.assertEquals(checkMonthlyPayment, monthlyPayment);
        Assertions.assertEquals(checkOverPayment, overPayment);
        Assertions.assertEquals(checkTotalPayment, totalPayment);

        double period2 = -5.0;
        CreditCalc creditCalc1 = new CreditCalc(amount, period2, percent);
        Exception exception = Assertions.assertThrows(Exception.class, creditCalc1::calculateAnnuity);
        Assertions.assertEquals("Срок кредита должен быть больше 0", exception.getMessage());

        double percent2 = 0;
        CreditCalc creditCalc2 = new CreditCalc(amount, period, percent2);
        creditCalc2.calculateAnnuity();
        Assertions.assertEquals(amount / period, creditCalc2.getMonthlyPayment());
    }

    @Test
    void calculateDifferentiated() throws Exception {
        double checkFirstPayment = 30000;
        double checkLastPayment = 25208.33;
        double checkOverPayment = 62500;
        double checkTotalPayment = 662500;

        CreditCalc creditCalc = new CreditCalc(amount, period, percent);

        double firstMonthlyPayment = creditCalc.getMonthlyPayment();
        double lastMonthlyPayment = firstMonthlyPayment;
        double overPayment = 0;
        double totalPayment = 0;
        for (int monthCount = 0; monthCount < period; monthCount++) {
            creditCalc.calculateDifferentiated();
            if (monthCount == 0) {
                firstMonthlyPayment = creditCalc.getMonthlyPayment();
            }
            lastMonthlyPayment = creditCalc.getMonthlyPayment();
            overPayment += creditCalc.getOverPayment();
            totalPayment += creditCalc.getTotalPayment();
        }

        creditCalc.calculateDifferentiated();

        Assertions.assertEquals(checkOverPayment, overPayment);
        Assertions.assertEquals(checkTotalPayment, totalPayment);
        Assertions.assertEquals(checkFirstPayment, firstMonthlyPayment);
        Assertions.assertEquals(checkLastPayment, lastMonthlyPayment);

        double period2 = -5.0;
        CreditCalc creditCalc1 = new CreditCalc(amount, period2, percent);
        Exception exception = Assertions.assertThrows(Exception.class, creditCalc1::calculateDifferentiated);
        Assertions.assertEquals("Срок кредита должен быть больше 0", exception.getMessage());
    }
}
