package school21.smartcalc.core;

public class MathCalc {
    private final String input;
    private int pos = -1;
    private int ch;

    public MathCalc(String input) {
        this.input = input.replaceAll("mod", "%")
                .replaceAll("√", "sqrt");
    }

    public double calculate() throws Exception {
        nextChar();
        double x = parseExpression();
//        if (pos < input.length()) {
//            throw new Exception("Syntax Error");
//        }
        return x;
    }

    private void nextChar() {
        ch = (++pos < input.length()) ? input.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parseExpression() throws Exception{
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() throws Exception {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor();
            else if (eat('/')) {
                double parseF = parseFactor();
                if (parseF == 0.0) {
                    throw new Exception("Division by zero");
                } else {
                    x /= parseF;
                }
            }
            else if (eat('%')) {
                double parseF = parseFactor();
                if (parseF == 0.0) {
                    throw new Exception("Division by zero");
                }
                x %= parseF;
            }
            else if (eat('E')) x = x * java.lang.Math.pow(10, parseFactor());
            else if (eat('^')) x = java.lang.Math.pow(x, parseFactor());
            else return x;
        }
    }

    private double parseFactor() throws Exception {
        if (eat('+')) return +parseFactor();    // unary plus
        if (eat('-')) return -parseFactor();    // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(input.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = input.substring(startPos, this.pos);
            if (eat('(')) {
                x = parseExpression();
                if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
            } else {
                x = parseFactor();
            }
            switch (func) {
                case "sin":
                    x = java.lang.Math.sin(java.lang.Math.toRadians(x));
                    break;
                case "cos":
                    x = java.lang.Math.cos(java.lang.Math.toRadians(x));
                    break;
                case "tan":
                    if ((x + 90) % 180 == 0) {
                        throw new Exception("Tan is undefined");
                    }
                    x = java.lang.Math.tan(java.lang.Math.toRadians(x));
                    break;
                case "log":
                    if (x < 0) {
                        throw new Exception("Log from negative value");
                    } else if (x == 0) {
                        throw new Exception("-Infinity");
                    }
                    x = java.lang.Math.log10(x);
                    break;
                case "ln":
                    if (x < 0) {
                        throw new Exception("Log from negative value");
                    }
                    x = java.lang.Math.log(x);
                    break;
                case "asin":
                    if (x < -1 || x > 1) {
                        throw new Exception("Asin is undefined");
                    }
                    x = java.lang.Math.asin(x);
                    break;
                case "acos":
                    if (x < -1 || x > 1) {
                        throw new Exception("Acos is undefined");
                    }
                    x = java.lang.Math.acos(x);
                    break;
                case "atan":
                    x = java.lang.Math.atan(x);
                    break;
                case "sqrt":
                    if (x < 0) {
                        throw new Exception("Sqrt from negative value");
                    }
                    x = java.lang.Math.sqrt(x);
                    break;
                default:
                    throw new RuntimeException("Unknown function: " + func);
            }
        } else {
            throw new RuntimeException("Syntax Error");
        }

        return x;
    }

}
