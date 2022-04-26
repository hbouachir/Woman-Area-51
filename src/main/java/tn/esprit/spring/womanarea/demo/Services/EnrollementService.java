package tn.esprit.spring.womanarea.demo.Services;

import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea.demo.Entities.Enrollement;
import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Repositories.EnrollementRepository;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Stripe.StripeService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EnrollementService implements IEnrollementService{

    @Autowired
    StripeService stripeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EnrollementRepository enrollementRepository;
    @Override
    public Enrollement addEnrollement(Enrollement e) {
        return enrollementRepository.save(e);
    }

    @Override
    public Enrollement ShowEnrollement(long id) {
        return enrollementRepository.findById(id).orElse(null);
    }

    @Override
    public Enrollement UpdateEnrollement(Enrollement u) {
        return enrollementRepository.save(u);
    }

    @Override
    public void DeleteEnrollement(long id) {
        enrollementRepository.deleteById(id);

    }



//every second



   // @Scheduled(cron = "*/1 * * * * *")


   // every day
   //@Scheduled(cron = "0 7 * * *" )
    @Scheduled(cron = "0 0 0 * * *") //Every day at midnight
    public void RenewableSubscription() throws Exception {
        List<User> users=  userRepository.findAll();
        for (User u : users){
            Enrollement x=seachUserValidEnrollement(u.getId());
            if(seachUserValidEnrollement(u.getId())==null) continue;
            if(!u.isEnabled()) continue;
            if(!seachUserValidEnrollement(u.getId()).isRenewable()) continue;

            else {
                Enrollement E=seachUserValidEnrollement(u.getId());
                if (LocalDate.now().isEqual(E.getExpire())){

                    if (u.getStripe_id()==null){
                        stripeService.createCustomer(u.getUsername(),u.getEmail());
                        String customerId =u.getStripe_id();
                        u.setStripe_id(customerId);
                        userRepository.save(u);

                        stripeService.createCard(customerId);

                    }
                    String customerId =u.getStripe_id();

                    Period period= Period.between(E.getCreated(),E.getExpire());
                    System.out.println(period);

                    Charge c= stripeService.chargeCustomerCard(customerId,  (E.getSubscription().getAmount()* period.getMonths()));
                    System.out.println(c);
                    if (c.getPaid()) {
                        u.setPointFidelite(u.getPointFidelite()+E.getSubscription().getAmount());
                        Enrollement e=new Enrollement();
                        e.setUser(u);
                        e.setRenewable(true);
                        e.setSubscription(E.getSubscription());
                        e.setCreated(LocalDate.now().plusDays(1));
                        e.setExpire(e.getCreated().plusMonths(E.getCreated().until(E.getExpire()).getMonths()).plusDays(1));
                        addEnrollement(e);


                }


            }


        }




        }}


    @Override
    public List<Enrollement> ShowAllEnrollement() {
        return enrollementRepository.findAll();
    }
    @Override
    public Enrollement seachUserValidEnrollement(long id){

        return enrollementRepository.getEnrollementValidByUser(id);
    }



}
