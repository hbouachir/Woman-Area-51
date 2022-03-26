package tn.esprit.spring.womanarea.demo.Services;

import com.twilio.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.womanarea.demo.Entities.Subscription;
import tn.esprit.spring.womanarea.demo.Repositories.SubscriptionRepository;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
//import javax.smartcardio.CardException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionRepository sr;

    @Autowired
    UserRepository ur;

    @Override
    public Subscription addSubscription(Subscription s) {
        // TODO Auto-generated method stub
        return sr.save(s);
    }

    @Override
    public Subscription ShowSubscription(long id) {
        // TODO Auto-generated method stub
        return  sr.findById(id).orElse(null);
    }

    @Override
    public Subscription UpdateSubscription(Subscription s) {
        // TODO Auto-generated method stub
        return sr.save(s);
    }

    @Override
    public void DeleteSubscription(long id) {
        sr.deleteById(id)	;
    }

    @Override
    public List<Subscription> ShowAllSubscription() {
        // TODO Auto-generated method stub
        return (List<Subscription>) sr.findAll();
    }




/*
    @Transactional
    public void Pay(int montant, String carta, int expMonth, int expYear, String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{


        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();
        Stripe.apiKey = "sk_test_51KfVItDWaDtTlXnLc80y8pPwotgND8hcVABSW6E762IiDE8SHBJD9qIKeaoFwbzfVSDq1CQgcK6gTndcr0xWRrTn00kGFMJAXn";
        cardParams.put("number", carta);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        tokenParams.put("card", cardParams);
        Token token =Token.create(tokenParams);
         System.out.println(token.getCard().getId());
        if (token.getId()!=null){
            params.put("amount", montant);
            params.put("description", "test de stipe");
            params.put("currency", "eur");
            params.put("source", token.getId());
            Charge charge = Charge.create(params);

        }                     }

 */

}
