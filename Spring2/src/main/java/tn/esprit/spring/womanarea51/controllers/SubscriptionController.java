package tn.esprit.spring.womanarea51.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.womanarea51.entities.Subscription;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.ISubscriptionService;
import tn.esprit.spring.womanarea51.services.SubscriptionService;
import tn.esprit.spring.womanarea51.stripe.StripeService;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    ISubscriptionService ss;

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("addSubscription")
    public Subscription addSubscription(@RequestBody Subscription s){
        return ss.addSubscription(s);


    }

    @DeleteMapping("deleteSubscription/{SubscriptionId}")
    public void deleteSubscription(@PathVariable("SubscriptionId") long subscriptionId){
        ss.DeleteSubscription(subscriptionId);

    }

    @PutMapping("updateSubscription")
    public Subscription updateSubscription(@RequestBody Subscription s){

        return ss.UpdateSubscription(s);
    }





    @GetMapping("subscription/{Subscriptionid}")
    public Subscription showSubscription(@PathVariable("SubscitionId") long Subscriptionid ){

        return ss.ShowSubscription(Subscriptionid);

    }


    @GetMapping("subscriptions")
    public List<Subscription> showAllSubscription(){

        return ss.ShowAllSubscription();

    }

/*
    @PostMapping("pay")
    public  ResponseEntity<?> payment(@RequestParam int montant,
                                      @RequestParam String carta,
                                      @RequestParam int expMonth,
                                      @RequestParam int expYear,
                                      @RequestParam String cvc ) throws StripeException {


        subscriptionService.Pay(montant, carta, expMonth, expYear, cvc);

        return ResponseEntity.ok("payment successfully!");

    }*/

    @Autowired
    StripeService stripeService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("createcustomer")
    public  ResponseEntity<?> createCustomer(Authentication authentication) throws StripeException {

        UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = userRepository.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

        if (U.getStripe_id()==null){
        Customer customer= stripeService.createCustomer(U.getUsername(),U.getEmail());
        U.setStripe_id(customer.getId());
        System.out.println(customer);
        userRepository.save(U);
        return ResponseEntity.ok("customer creaated successfully!");}

        else {return  ResponseEntity.ok("you have already a customer account!");}
    }



    @PostMapping("createcard")
    public ResponseEntity<?> createCard(Authentication authentication) throws StripeException {

        UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = userRepository.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

      Card card= stripeService.createCard(U.getStripe_id());
        System.out.println(card);
     return ResponseEntity.ok("done");

    }


    /*

    @PostMapping("pay")
    public String pay(@RequestParam int amount, Authentication authentication) throws Exception {

        UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = userRepository.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

        String customerId =U.getStripe_id();
            Charge c= stripeService.chargeCustomerCard(customerId,amount);
            if (c.getPaid()) {
        return "redirect:"+new RedirectView(c.getReceiptUrl()).getUrl();}
            else
                return "transaction refused";

    }
*/





}
