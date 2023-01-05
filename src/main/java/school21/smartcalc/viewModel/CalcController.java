package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import school21.smartcalc.core.Calc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class CalcController {
    private final Logger LOG = LoggerFactory.getLogger(CalcController.class);

    private static final String HISTORY_FILE_PATH = "/Users/fbeatris/SmartCalcFiles/history.txt";
    private static final String STATUS_OK = "OK";
    private static final String STATUS_SYNTAX_ERROR = "Syntax Error";
    private static final String STATUS_SYSTEM_ERROR = "System Error";
    private static final String VAR = "x";

    @PostMapping(value = "/constant", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalcResponse getConstant(@RequestBody CalcRequest calcRequest) {
        String calcResult;
        CalcResponse calcResponse = new CalcResponse();
        String input = calcRequest.getInput();

        try {
            saveHistory(input);

            Calc calc = new Calc(input);
            calcResult = calc.calculate();

            calcResponse.setStatus(STATUS_OK);
            calcResponse.setOutput(calcResult);

            LOG.info("[getConstant] ok, input: " + input);
        } catch (ParseException ex) {
            calcResponse.setStatus(STATUS_SYNTAX_ERROR);
            calcResponse.setMessage(ex.getMessage());
            LOG.error("[getConstant] " + STATUS_SYNTAX_ERROR + ": " + ex.getMessage() + ", input: " + input);
        } catch (EvaluationException | IOException ex) {
            calcResponse.setStatus(STATUS_SYSTEM_ERROR);
            calcResponse.setMessage(ex.getMessage());
            LOG.error("[getConstant] " + STATUS_SYSTEM_ERROR + ": " + ex.getMessage() + ", input: " + input);
        }
        return calcResponse;
    }

    @PostMapping(value = "/graph", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalcResponse getGraph(@RequestBody CalcRequest calcRequest) {

        CalcResponse calcResponse = new CalcResponse();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        String input = calcRequest.getInput();

        try {
            saveHistory(input);

            double from = calcRequest.getFrom();
            double to = calcRequest.getTo();
            double step = (to - from) / 100;
            for (double i = from; i <= to; i += step) {
                Calc calc = new Calc(input.replaceAll(VAR, String.valueOf(i)));
                xList.add((double) Math.round(i * 100) / 100);
                yList.add(Double.parseDouble(calc.calculate()));
            }
            calcResponse.setStatus(STATUS_OK);
            calcResponse.setxValues(xList);
            calcResponse.setyValues(yList);
            LOG.info("[getGraph] ok, input: " + input);
        } catch (ParseException ex) {
            calcResponse.setStatus(STATUS_SYNTAX_ERROR);
            calcResponse.setMessage(ex.getMessage());
            LOG.error("[getGraph] " + STATUS_SYNTAX_ERROR + ": " + ex.getMessage() + ", input: " + input);
        } catch (EvaluationException | IOException ex) {
            calcResponse.setStatus(STATUS_SYSTEM_ERROR);
            calcResponse.setMessage(ex.getMessage());
            LOG.error("[getGraph] " + STATUS_SYSTEM_ERROR + ": " + ex.getMessage() + ", input: " + input);
        }
        return calcResponse;
    }

    @GetMapping(value = "/getHistory/{inputNum}")
    public String getHistory(@PathVariable String inputNum) throws IOException {
        int historyNum = Integer.parseInt(inputNum);
        Scanner scanner = new Scanner(Files.newInputStream(Paths.get(HISTORY_FILE_PATH)));
        String line = "";
        for (int i = 0; i <= historyNum; i++) {
            line = scanner.hasNext() ? scanner.nextLine() : "";
        }

        LOG.info("[getHistory] ok, line: " + line);
        return line;
    }

    @GetMapping(value = "/clearHistory")
    public void clearHistory() {
        try {
            PrintWriter pw = new PrintWriter(HISTORY_FILE_PATH);
            pw.close();
            LOG.info("[clearHistory] ok");
        } catch (IOException e) {
            LOG.info("[clearHistory] error " + e.getMessage());
        }
    }

    @GetMapping(value = "/exit")
    public void exitApp() {
        LOG.info("[exitApp]");
        System.exit(0);
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