package tn.esprit.spring.womanarea.demo.Controllers;

import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import tn.esprit.spring.womanarea.demo.Entities.Enrollement;
import tn.esprit.spring.womanarea.demo.Entities.Role;
import tn.esprit.spring.womanarea.demo.Entities.Subscription;
import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Payload.response.MessageResponse;
import tn.esprit.spring.womanarea.demo.Repositories.SubscriptionRepository;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Services.IEnrollementService;
import tn.esprit.spring.womanarea.demo.Services.IRoleService;
import tn.esprit.spring.womanarea.demo.Stripe.StripeService;
import tn.esprit.spring.womanarea.demo.security.services.UserDetailsImpl;

import java.time.LocalDate;
import java.util.List;


@RestController

public class EnrollementController {

    @Autowired
    IEnrollementService enrollementService;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StripeService stripeService;

    @PostMapping("addEnrollement")
    public ResponseEntity<?> addUser(Authentication authentication, @RequestParam int months, @RequestParam long idSubscription) throws Exception {
        Subscription S=subscriptionRepository.findById(idSubscription).orElse(null);
        UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = userRepository.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
        if(S==null) return  ResponseEntity.ok("verifier l'abonnement,cet abonnement n'existe pas");
        if (enrollementService.seachUserValidEnrollement(U.getId())!=null){return  ResponseEntity.ok("vous avez un abonnement encore valid");}
        String customerId =U.getStripe_id();
        Charge c= stripeService.chargeCustomerCard(customerId,S.getAmount()*months);
        if (c.getPaid()) {
            U.setPointFidelite(U.getPointFidelite()+S.getAmount());
            Enrollement e=new Enrollement();
            e.setUser(U);
            e.setSubscription(S);
            e.setCreated(LocalDate.now());
            e.setExpire(e.getCreated().plusMonths(months));
            enrollementService.addEnrollement(e);




            return ResponseEntity.ok("redirect:"+new RedirectView(c.getReceiptUrl()).getUrl());}
        else
            return ResponseEntity.ok("transaction refused");





    }

    @DeleteMapping("deleteEnrollement")
    public void deleteRole(@RequestParam("enrollementId") long enrollementId){
        enrollementService.DeleteEnrollement(enrollementId);

    }

    @PutMapping("updateRole")
    public Enrollement updateRole(@RequestBody Enrollement e)
    {
        return enrollementService.UpdateEnrollement(e);
    }





    @GetMapping("{enrollementId}")
    public Enrollement showEnrollement(@PathVariable("enrollementId") long enrollementId ){

        return enrollementService.ShowEnrollement(enrollementId);

    }


    @GetMapping("/enrollements")
    public List<Enrollement> showAllEnrollement(){

        return enrollementService.ShowAllEnrollement();

    }

    @GetMapping("/enrollements/valid")
    public String showEnrollementValid(Authentication authentication){

        UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = userRepository.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

        Enrollement e=enrollementService.seachUserValidEnrollement(U.getId());

        if (e==null) return U.getFirstName()+" this user doesn't have a valid subscription,you can subscribe";
        return  U.getFirstName()+"  you can't make a subscription you have a valid one" ;

    }
}
