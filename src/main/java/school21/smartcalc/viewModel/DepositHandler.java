package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import school21.smartcalc.core.DepositCalc;
import school21.smartcalc.models.DepositRequest;
import school21.smartcalc.models.DepositResponse;

public class DepositHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DepositHandler.class);

    public DepositResponse getDeposit(DepositRequest request) {
        LOG.info("[getDeposit] request " + request);

        DepositCalc depositCalc = new DepositCalc();
        depositCalc.setAmount(request.getAmount());
        depositCalc.setPeriod(request.getPeriod());
        depositCalc.setPercent(request.getPercent());
        depositCalc.setFrequency(request.getFrequency());
        depositCalc.setCapitalisation(request.isCapitalisation());
        depositCalc.setTax(request.getTax());
        depositCalc.setDeposits(request.getDeposits());
        depositCalc.setWithdrawals(request.getWithdrawals());

        DepositResponse response = new DepositResponse();

        try {
            depositCalc.calculate();

            response.setStatus("OK");
            response.setProfit(Math.round(depositCalc.getProfit() * 100.0) / 100.0);
            response.setTaxes(Math.round(depositCalc.getTaxes() * 100.0) / 100.0);
            response.setTotal(Math.round(depositCalc.getTotal() * 100.0) / 100.0);

            LOG.info("[getDeposit] ok, response " + response);
        } catch (Exception ex) {
            response.setStatus("Error");
            response.setMessage(ex.getMessage());
            LOG.error("[getDeposit] error: " + ex.getMessage());
        }

        return response;
    }
}
