package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import school21.smartcalc.core.MathCalc;
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

    private static final Logger LOG = LoggerFactory.getLogger(MathHandler.class);

    private static final String HISTORY_FILE_PATH = System.getProperty("user.home") + "/SmartCalcFiles/history.txt";
    private static final String STATUS_OK = "OK";
    private static final String STATUS_ERROR = "Error";
    private static final String VAR = "x";
    private static final double ROUND_VALUE = 10000000.0;

    public MathResponse getConstant(MathRequest mathRequest) {
        LOG.info("[getConstant] input: " + mathRequest.getInput());

        String input = mathRequest.getInput();
        String calcResult;
        MathResponse mathResponse = new MathResponse();

        try {
            saveHistory(input);

            MathCalc mathCalc = new MathCalc(input);
            double answer = Math.round(mathCalc.calculate() * ROUND_VALUE) / ROUND_VALUE;
            calcResult = String.valueOf(answer);
            if (calcResult.endsWith(".0")) {
                calcResult = calcResult.substring(0, calcResult.length() - 2);
            }

            mathResponse.setStatus(STATUS_OK);
            mathResponse.setOutput(calcResult);

            LOG.info("[getConstant] ok, response: " + mathResponse);
        } catch (Exception ex) {
            mathResponse.setStatus(STATUS_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getConstant] error: " + ex.getMessage());
        }

        return mathResponse;
    }

    public MathResponse getGraph(MathRequest mathRequest) {
        LOG.info("[getGraph] request: " + mathRequest);

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
                MathCalc mathCalc = new MathCalc(input.replaceAll(VAR, String.valueOf(i)));
                xList.add((double) java.lang.Math.round(i * 100) / 100);
                double answer;
                try {
                    answer = Math.round(mathCalc.calculate() * ROUND_VALUE) / ROUND_VALUE;
                    yList.add(answer);
                } catch (Exception e) {
                    yList.add(null);
                }
            }
            mathResponse.setStatus(STATUS_OK);
            mathResponse.setXValues(xList);
            mathResponse.setYValues(yList);

            LOG.info("[getGraph] ok, response xValues: " + xList.get(0) + "..." + xList.get(xList.size() - 1)
                    + " yValues: " + yList.get(0) + "..." + yList.get(yList.size() - 1));
        } catch (IOException ex) {
            mathResponse.setStatus(STATUS_ERROR);
            mathResponse.setMessage(ex.getMessage());
            LOG.error("[getGraph] error: " + ex.getMessage());
        }
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
