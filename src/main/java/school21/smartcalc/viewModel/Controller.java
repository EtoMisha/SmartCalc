package school21.smartcalc.viewModel;

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import school21.smartcalc.models.*;

import java.io.*;

@RestController
public class Controller {
    private final MathHandler mathHandler = new MathHandler();
    private final CreditHandler creditHandler = new CreditHandler();
    private final DepositHandler depositHandler = new DepositHandler();

    @PostMapping(value = "/constant", produces = MediaType.APPLICATION_JSON_VALUE)
    public MathResponse getConstant(@RequestBody MathRequest mathRequest) {
        return mathHandler.getConstant(mathRequest);
    }

    @PostMapping(value = "/graph", produces = MediaType.APPLICATION_JSON_VALUE)
    public MathResponse getGraph(@RequestBody MathRequest mathRequest) {
        return mathHandler.getGraph(mathRequest);
    }

    @GetMapping(value = "/getHistory/{inputNum}")
    public String getHistory(@PathVariable String inputNum) throws IOException {
        return mathHandler.getHistory(inputNum);
    }

    @GetMapping(value = "/clearHistory")
    public void clearHistory() {
        mathHandler.clearHistory();
    }

    @PostMapping(value = "/getCredit", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditResponse getCredit(@RequestParam CreditRequest request) {
        return creditHandler.getCredit(request);
    }

    @PostMapping(value = "/getDeposit", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepositResponse getCredit(@RequestParam DepositRequest request) {
        return depositHandler.getDeposit(request);
    }

    @GetMapping(value = "/exit")
    public void exitApp() {
        LoggerFactory.getLogger(Controller.class).info("[exitApp]");
        System.exit(0);
    }

}