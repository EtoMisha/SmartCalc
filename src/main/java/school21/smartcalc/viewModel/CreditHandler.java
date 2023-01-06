package school21.smartcalc.viewModel;

import school21.smartcalc.models.CreditRequest;
import school21.smartcalc.models.CreditResponse;

public class CreditHandler {
    public CreditResponse getCredit(CreditRequest request) {
        double amount = request.getAmount();
        int period = request.getPeriod();
        double percent = request.getPercent();
        String type = request.getType();

        CreditResponse response = new CreditResponse();
        if ("type_annuity".equals(type)) {

        } else if ("type_differentiated".equals(type)) {

        }

        return response;
    }
}
