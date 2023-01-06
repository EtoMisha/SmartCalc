package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import school21.smartcalc.core.Calc;
import school21.smartcalc.models.MathRequest;
import school21.smartcalc.models.MathResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathHandler {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    private static final String HISTORY_FILE_PATH = "/Users/fbeatris/SmartCalcFiles/history.txt";
    private static final String STATUS_OK = "OK";
    private static final String STATUS_SYNTAX_ERROR = "Syntax Error";
    private static final String STATUS_SYSTEM_ERROR = "System Error";
    private static final String VAR = "x";

    public MathResponse getConstant(MathRequest mathRequest) {
        String input = mathRequest.getInput();
        String calcResult;
        MathResponse mathResponse = new MathResponse();

        try {
            saveHistory(input);

            Calc calc = new Calc(input);
            calcResult = calc.calculate();

            mathResponse.setStatus(STATUS_OK);
            mathResponse.setOutput(calcResult);

            LOG.info("[getConstant] ok, input: " + input);
        } catch (ParseException ex) {
            mathResponse.setStatus(STATUS_SYNTAX_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getConstant] " + STATUS_SYNTAX_ERROR + ": " + ex.getMessage() + ", input: " + input);
        } catch (EvaluationException | IOException ex) {
            mathResponse.setStatus(STATUS_SYSTEM_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getConstant] " + STATUS_SYSTEM_ERROR + ": " + ex.getMessage() + ", input: " + input);
        }
        return mathResponse;
    }

    public MathResponse getGraph(MathRequest mathRequest) {
        MathResponse mathResponse = new MathResponse();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        String input = mathRequest.getInput();

        try {
            saveHistory(input);

            double from = mathRequest.getFrom();
            double to = mathRequest.getTo();
            double step = (to - from) / 100;
            for (double i = from; i <= to; i += step) {
                Calc calc = new Calc(input.replaceAll(VAR, String.valueOf(i)));
                xList.add((double) Math.round(i * 100) / 100);
                yList.add(Double.parseDouble(calc.calculate()));
            }
            mathResponse.setStatus(STATUS_OK);
            mathResponse.setXValues(xList);
            mathResponse.setYValues(yList);
            LOG.info("[getGraph] ok, input: " + input);
        } catch (ParseException ex) {
            mathResponse.setStatus(STATUS_SYNTAX_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getGraph] " + STATUS_SYNTAX_ERROR + ": " + ex.getMessage() + ", input: " + input);
        } catch (EvaluationException | IOException ex) {
            mathResponse.setStatus(STATUS_SYSTEM_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getGraph] " + STATUS_SYSTEM_ERROR + ": " + ex.getMessage() + ", input: " + input);
        }
        LOG.info("graph response " + mathResponse);
        return mathResponse;
    }

    public String getHistory(String inputNum) throws IOException {
        int historyNum = Integer.parseInt(inputNum);
        Scanner scanner = new Scanner(Files.newInputStream(Paths.get(HISTORY_FILE_PATH)));
        String line = "";
        for (int i = 0; i <= historyNum; i++) {
            line = scanner.hasNext() ? scanner.nextLine() : "";
        }

        LOG.info("[getHistory] ok, line: " + line);
        return line;
    }

    public void clearHistory() {
        try {
            PrintWriter pw = new PrintWriter(HISTORY_FILE_PATH);
            pw.close();
            LOG.info("[clearHistory] ok");
        } catch (IOException e) {
            LOG.info("[clearHistory] error " + e.getMessage());
        }
    }

    private void saveHistory(String input) throws IOException {
        Path path = Paths.get(HISTORY_FILE_PATH);
        File historyFile = new File(HISTORY_FILE_PATH);
        if (historyFile.exists() || historyFile.createNewFile()) {
            Scanner scanner = new Scanner(Files.newInputStream(path));
            String line = scanner.hasNextLine() ? scanner.nextLine() : "";
            scanner.close();
            if (!input.equals(line)) {
                ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
                byteArrayOutStream.write((input + "\n").getBytes());
                FileInputStream fileIS = new FileInputStream(HISTORY_FILE_PATH);
                while(fileIS.available() > 0)
                    byteArrayOutStream.write(fileIS.read());
                fileIS.close();
                FileOutputStream fileOS = new FileOutputStream(HISTORY_FILE_PATH);
                byteArrayOutStream.writeTo(fileOS);
                fileOS.close();
                byteArrayOutStream.close();
            }
        }
    }

}
