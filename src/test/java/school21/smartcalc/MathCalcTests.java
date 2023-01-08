package school21.smartcalc;

import org.junit.jupiter.api.*;
import school21.smartcalc.core.MathCalc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class MathCalcTests {
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
    void sum() throws Exception {
        String expression = String.format("%f + %f", x, y);
        BigDecimal checkResult = new BigDecimal(x + y, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void subtract() throws Exception {
        String expression = String.format("%f - %f", x, y);
        BigDecimal checkResult = new BigDecimal(x - y, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void multiply() throws Exception {
        String expression = String.format("%f * %f", x, y);
        BigDecimal checkResult = new BigDecimal(x * y, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void divide() throws Exception {
        String expression = String.format("%f / %f", x, y);
        BigDecimal checkResult = new BigDecimal(x / y, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void mod() throws Exception {
        String expression = String.format("%f mod %f", x, y);
        BigDecimal checkResult = new BigDecimal(x % y, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void parenthesis() throws Exception {
        String expression = String.format("(%f + %f) * %f", x, y, z);
        BigDecimal checkResult = new BigDecimal((x + y) * z, context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sqrt() throws Exception {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("sqrt(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.sqrt(n), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void pow() throws Exception {
        int n = (int) ((java.lang.Math.random() * 10) + 1);
        String expression = String.format("%f ^ %d", x, n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.pow(x, n) , context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sin() throws Exception {
        String expression = String.format("sin(%f)", x);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.sin(java.lang.Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void cos() throws Exception {
        String expression = String.format("cos(%f)", x);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.cos(java.lang.Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void tan() throws Exception {
        String expression = String.format("tan(%f)", x);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.tan(java.lang.Math.toRadians(x)), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void asin() throws Exception {
        double n = ThreadLocalRandom.current().nextDouble(-1, 1);
        String expression = String.format("asin(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.asin(n), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void acos() throws Exception {
        double n = ThreadLocalRandom.current().nextDouble(-1, 1);
        String expression = String.format("acos(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.acos(n), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void atan() throws Exception {
        String expression = String.format("atan(%f)", x);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.atan(x), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void log() throws Exception {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("log(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.log10(n), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void ln() throws Exception {
        double n = ThreadLocalRandom.current().nextDouble(0, Long.MAX_VALUE);
        String expression = String.format("ln(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.log(n), context);
        BigDecimal testResult = new BigDecimal(new MathCalc(expression).calculate(), context);
        Assertions.assertEquals(checkResult, testResult);
    }


}
