package school21.smartcalc.viewModel;

import java.util.List;
import java.util.Objects;

public class CalcResponse {
    private String status;
    private String message;
    private String output;
    private List<Double> xValues;
    private List<Double> yValues;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getOutput() {
        return output;
    }

    public List<Double> getxValues() {
        return xValues;
    }

    public List<Double> getyValues() {
        return yValues;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setxValues(List<Double> xValues) {
        this.xValues = xValues;
    }

    public void setyValues(List<Double> yValues) {
        this.yValues = yValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcResponse that = (CalcResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(message, that.message) && Objects.equals(output, that.output) && Objects.equals(xValues, that.xValues) && Objects.equals(yValues, that.yValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, output, xValues, yValues);
    }

    @Override
    public String toString() {
        return "CalcResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", output='" + output + '\'' +
                ", xValues=" + xValues +
                ", yValues=" + yValues +
                '}';
    }
}
