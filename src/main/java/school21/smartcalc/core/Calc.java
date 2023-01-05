package school21.smartcalc.core;

public class Calc {
    private final String input;
    private int pos = -1;
    private int ch;

    public Calc(String input) {
        this.input = input.replaceAll("mod", "%")
                .replaceAll("√", "sqrt");
    }

    public String calculate() {
        String result;
        try {
            nextChar();
            double x = parseExpression();
            if (pos < input.length()) {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }
            result = String.valueOf(x);
        } catch (RuntimeException ex) {
            result = ex.getMessage();
        }
        return result;
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

    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor();                  // multiplication
            else if (eat('/')) x /= parseFactor();                  // division
            else if (eat('%')) x %= parseFactor();                  // mod
            else if (eat('E')) x = x * Math.pow(10, parseFactor()); // exp form
            else if (eat('^')) x = Math.pow(x, parseFactor());      // exponentiation
            else return x;
        }
    }

    private double parseFactor() throws RuntimeException {
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
                    x = Math.sin(Math.toRadians(x));
                    break;
                case "cos":
                    x = Math.cos(Math.toRadians(x));
                    break;
                case "tan":
                    x = Math.tan(Math.toRadians(x));
                    break;
                case "log":
                    x = Math.log10(x);
                    break;
                case "ln":
                    x = Math.log(x);
                    break;
                case "asin":
                    x = Math.asin(x);
                    break;
                case "acos":
                    x = Math.acos(x);
                    break;
                case "atan":
                    x = Math.atan(x);
                    break;
                case "sqrt":
                    x = Math.sqrt(x);
                    break;
                default:
                    throw new RuntimeException("Unknown function: " + func);
            }
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        return x;
    }

}
