package school21.smartcalc.viewModel;

import java.util.Objects;

public class CalcRequest {
    private String input;
    private Double from;
    private Double to;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcRequest that = (CalcRequest) o;
        return Objects.equals(input, that.input) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, from, to);
    }

    @Override
    public String toString() {
        return "CalcRequest{" +
                "input='" + input + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
