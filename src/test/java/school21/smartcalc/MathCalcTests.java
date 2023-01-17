package school21.smartcalc;

import org.junit.jupiter.api.*;
import school21.smartcalc.core.MathCalc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        double n = ThreadLocalRandom.current().nextDouble(-89, 89);
        String expression = String.format("tan(%f)", n);
        BigDecimal checkResult = new BigDecimal(java.lang.Math.tan(java.lang.Math.toRadians(n)), context);
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

    @Test
    void SyntaxErrorTest() {
        String expression1 = "1 + a";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Syntax Error", exception.getMessage());

        String expression2 = "";
        exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Syntax Error", exception.getMessage());
    }

    @Test
    void divisionZeroTest() {
        String expression = "1 / 0";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression).calculate());
        Assertions.assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void modZeroTest() {
        String expression = "1 % 0";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression).calculate());
        Assertions.assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void negativeSqrtTest() {
        String expression = "sqrt(-1)";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression).calculate());
        Assertions.assertEquals("Sqrt from negative value", exception.getMessage());
    }

    @Test
    void negativeLogTest() {
        String expression1 = "log(-10)";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Log from negative value", exception.getMessage());

        String expression2 = "log(0)";
        exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression2).calculate());
        Assertions.assertEquals("-Infinity", exception.getMessage());

        String expression3 = "ln(-10)";
        exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression3).calculate());
        Assertions.assertEquals("Log from negative value", exception.getMessage());
    }

    @Test
    void undefinedTanTest() {
        String expression1 = "tan(270)";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Tan is undefined", exception.getMessage());
    }

    @Test
    void undefinedAsin() {
        String expression1 = "asin(2)";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Asin is undefined", exception.getMessage());

        String expression2 = "acos(2)";
        exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression2).calculate());
        Assertions.assertEquals("Acos is undefined", exception.getMessage());
    }

    @Test
    void unknownFuncTest() {
        String expression1 = "1 + abc(1)";
        Exception exception = Assertions.assertThrows(Exception.class, () -> new MathCalc(expression1).calculate());
        Assertions.assertEquals("Unknown function: abc", exception.getMessage());
    }


}
