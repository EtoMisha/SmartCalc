package school21.smartcalc;

import org.junit.jupiter.api.*;
import school21.smartcalc.core.Calc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class CalcTests {
    private double x;
    private double y;
    private double z;
    private final MathContext context = new MathContext(4, RoundingMode.HALF_UP);

    @BeforeEach
    void getRandomInput() {
        x = ThreadLocalRandom.current().nextDouble(Long.MIN_VALUE, Long.MAX_VALUE);
        y = ThreadLocalRandom.current().nextDouble(Long.MIN_VALUE, Long.MAX_VALUE);
        z = ThreadLocalRandom.current().nextDouble(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    @RepeatedTest(10)
    void sum() {
        String expression = String.format("%f + %f", x, y);
        BigDecimal checkResult = new BigDecimal(x + y, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void subtract() {
        String expression = String.format("%f - %f", x, y);
        BigDecimal checkResult = new BigDecimal(x - y, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void multiply() {
        String expression = String.format("%f * %f", x, y);
        BigDecimal checkResult = new BigDecimal(x * y, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void divide() {
        String expression = String.format("%f / %f", x, y);
        BigDecimal checkResult = new BigDecimal(x / y, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void mod() {
        String expression = String.format("%f mod %f", x, y);
        BigDecimal checkResult = new BigDecimal(x % y, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void parenthesis() {
        String expression = String.format("(%f + %f) * %f", x, y, z);
        BigDecimal checkResult = new BigDecimal((x + y) * z, context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sqrt() {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("sqrt(%f)", n);
        BigDecimal checkResult = new BigDecimal(Math.sqrt(n), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void pow() {
        int n = (int) ((Math.random() * 10) + 1);
        String expression = String.format("%f ^ %d", x, n);
        BigDecimal checkResult = new BigDecimal(Math.pow(x, n) , context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sin() {
        String expression = String.format("sin(%f)", x);
        BigDecimal checkResult = new BigDecimal(Math.sin(Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void cos() {
        String expression = String.format("cos(%f)", x);
        BigDecimal checkResult = new BigDecimal(Math.cos(Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void tan() {
        String expression = String.format("tan(%f)", x);
        BigDecimal checkResult = new BigDecimal(Math.tan(Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void asin() {
        double n = ThreadLocalRandom.current().nextDouble(-1, 1);
        String expression = String.format("asin(%f)", n);
        BigDecimal checkResult = new BigDecimal(Math.asin(n), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void acos() {
        double n = ThreadLocalRandom.current().nextDouble(-1, 1);
        String expression = String.format("acos(%f)", n);
        BigDecimal checkResult = new BigDecimal(Math.acos(n), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void atan() {
        String expression = String.format("atan(%f)", x);
        BigDecimal checkResult = new BigDecimal(Math.atan(x), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void log() {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("log(%f)", n);
        BigDecimal checkResult = new BigDecimal(Math.log10(n), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void ln() {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("ln(%f)", n);
        BigDecimal checkResult = new BigDecimal(Math.log(n), context);
        BigDecimal testResult = new BigDecimal(new Calc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }


}
