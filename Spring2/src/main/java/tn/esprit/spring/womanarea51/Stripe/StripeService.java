package tn.esprit.spring.womanarea51.Stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {


    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_51KfVItDWaDtTlXnLc80y8pPwotgND8hcVABSW6E762IiDE8SHBJD9qIKeaoFwbzfVSDq1CQgcK6gTndcr0xWRrTn00kGFMJAXn";

    public StripeService() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
    }

    public Customer createCustomer(String name, String email) throws StripeException {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);


        Customer customer = Customer.create(params);

        return customer;


    }
    public Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public Card createCard(String stripe_id) throws StripeException {


        Map<String, Object> retrieveParams =
                new HashMap<>();
        List<String> expandList = new ArrayList<>();
        expandList.add("sources");
        retrieveParams.put("expand", expandList);
        Customer customer =
                Customer.retrieve(stripe_id);

        Map<String, Object> params = new HashMap<>();
        params.put("source", "tok_visa");

        Card card =
                (Card) customer.getSources().create(params);
    return card;
    }














    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);

        return charge;
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {

        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount*100);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }


}