package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import school21.smartcalc.core.CreditCalc;
import school21.smartcalc.models.CreditRequest;
import school21.smartcalc.models.CreditResponse;

public class CreditHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CreditHandler.class);

    public CreditResponse getCredit(CreditRequest request) {
        LOG.info("[getCredit] request " + request);

        CreditResponse response = new CreditResponse();

        try {
            CreditCalc creditCalc = new CreditCalc(request.getAmount(), request.getPeriod(), request.getPercent());

            String type = request.getType();
            if ("type_annuity".equals(type)) {
                creditCalc.calculateAnnuity();

                response.setStatus("OK");
                response.setMonthlyPayment(String.format("%.2f", creditCalc.getMonthlyPayment()));
                response.setOverPayment(Math.round(creditCalc.getOverPayment() * 100.0) / 100.0);
                response.setTotalPayment(Math.round(creditCalc.getTotalPayment() * 100.0) / 100.0);
            } else if ("type_differentiated".equals(type)) {
                double period = request.getPeriod();
                if (period <= 0) {
                    throw new Exception("Срок кредита должен быть больше 0");
                }

                double firstMonthlyPayment = creditCalc.getMonthlyPayment();
                double lastMonthlyPayment = firstMonthlyPayment;
                double overPayment = 0;
                double totalPayment = 0;
                for (int monthCount = 0; monthCount < period; monthCount++) {
                    creditCalc.calculateDifferentiated();
                    if (monthCount == 0) {
                        firstMonthlyPayment = creditCalc.getMonthlyPayment();
                    }
                    lastMonthlyPayment = creditCalc.getMonthlyPayment();
                    overPayment += creditCalc.getOverPayment();
                    totalPayment += creditCalc.getTotalPayment();
                }

                response.setStatus("OK");
                response.setMonthlyPayment(String.format("%.2f ... %.2f", firstMonthlyPayment, lastMonthlyPayment));
                response.setOverPayment(Math.round(overPayment * 100.0) / 100.0);
                response.setTotalPayment(Math.round(totalPayment * 100.0) / 100.0);
            }

            LOG.info("[getCredit] ok, response " + response);
        } catch (Exception ex) {
            response.setStatus("Error");
            response.setMessage(ex.getMessage());
            LOG.error("[getCredit] error: " + ex.getMessage());
        }

        return response;
    }
}
