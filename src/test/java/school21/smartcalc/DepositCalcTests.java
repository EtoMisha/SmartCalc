package school21.smartcalc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school21.smartcalc.core.CreditCalc;
import school21.smartcalc.core.DepositCalc;
import school21.smartcalc.core.MathCalc;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DepositCalcTests {

    private double amount = 600000;
    private double period = 24;
    private double percent = 10;
    private double tax = 5;

    DepositCalc depositCalc = new DepositCalc();

    @BeforeEach
    void setParameters() {
        depositCalc.setAmount(amount);
        depositCalc.setPeriod(period);
        depositCalc.setPercent(percent);
        depositCalc.setTax(tax);
        depositCalc.setWithdrawals(null);
        depositCalc.setDeposits(null);
    }

    @Test
    void simple() throws Exception {
        double checkProfit = 120000;
        double checkTaxes = 0;
        double checkTotal = 720000;

        depositCalc.setCapitalisation(false);
        depositCalc.calculate();

        Assertions.assertEquals(checkProfit, depositCalc.getProfit());
        Assertions.assertEquals(checkTaxes, depositCalc.getTaxes());
        Assertions.assertEquals(checkTotal, depositCalc.getTotal());
    }

    @Test
    void capitalizationMonthly() throws Exception {
        depositCalc.setCapitalisation(true);
        depositCalc.setFrequency("monthly");
        depositCalc.calculate();
        Assertions.assertEquals(132235, Math.round(depositCalc.getProfit()));
        Assertions.assertEquals(0, depositCalc.getTaxes());
        Assertions.assertEquals(732235, Math.round(depositCalc.getTotal()));
    }

    @Test
    void capitalizationQuarterly() throws Exception {
        depositCalc.setCapitalisation(true);
        depositCalc.setFrequency("quarterly");
        depositCalc.calculate();
        Assertions.assertEquals(131042, Math.round(depositCalc.getProfit()));
        Assertions.assertEquals(0, depositCalc.getTaxes());
        Assertions.assertEquals(731042, Math.round(depositCalc.getTotal()));
    }

    @Test
    void capitalizationYearly() throws Exception {
        depositCalc.setCapitalisation(true);
        depositCalc.setFrequency("yearly");
        depositCalc.calculate();
        Assertions.assertEquals(126000, Math.round(depositCalc.getProfit()));
        Assertions.assertEquals(0, depositCalc.getTaxes());
        Assertions.assertEquals(726000, Math.round(depositCalc.getTotal()));
    }

    @Test
    void taxes() throws Exception {
        depositCalc.setAmount(10000000);
        depositCalc.setCapitalisation(false);
        depositCalc.setFrequency("yearly");
        depositCalc.calculate();
        Assertions.assertEquals(2000000, Math.round(depositCalc.getProfit()));
        Assertions.assertEquals(92500, depositCalc.getTaxes());
        Assertions.assertEquals(11907500, Math.round(depositCalc.getTotal()));
    }

    @Test
    void withdrawals() throws Exception {
        depositCalc.setWithdrawals(new ArrayList<>(1000));
        depositCalc.setDeposits(new ArrayList<>(1000));
        depositCalc.setCapitalisation(false);
        depositCalc.setFrequency("yearly");
        depositCalc.calculate();
        Assertions.assertEquals(120000, Math.round(depositCalc.getProfit()));
        Assertions.assertEquals(0, depositCalc.getTaxes());
        Assertions.assertEquals(720000, Math.round(depositCalc.getTotal()));
    }

    @Test
    void errors() throws Exception {
        depositCalc.setPeriod(-5);
        depositCalc.setCapitalisation(false);
        depositCalc.setFrequency("yearly");
        Exception exception = Assertions.assertThrows(Exception.class, () -> depositCalc.calculate());
        Assertions.assertEquals("Срок кредита должен быть больше 0", exception.getMessage());

        depositCalc.setPeriod(5);
        depositCalc.setPercent(-1);
        exception = Assertions.assertThrows(Exception.class, () -> depositCalc.calculate());
        Assertions.assertEquals("Ставка должна быть больше либо равна 0", exception.getMessage());
    }



}
