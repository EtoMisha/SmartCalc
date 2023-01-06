package school21.smartcalc.models;

import lombok.Data;

import java.util.List;

@Data
public class MathResponse {
    private String status;
    private String message;
    private String output;
    private List<Double> xValues;
    private List<Double> yValues;
}
