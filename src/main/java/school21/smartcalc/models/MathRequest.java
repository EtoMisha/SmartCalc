package school21.smartcalc.models;

import lombok.Data;

@Data
public class MathRequest {
    private String input;
    private Double from;
    private Double to;
}
