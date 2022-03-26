package tn.esprit.spring.womanarea51.Services;

import tn.esprit.spring.womanarea51.Entities.Subscription;

import java.util.List;

public interface ISubscriptionService {

	
	Subscription addSubscription(Subscription s);
	Subscription ShowSubscription(long id);
	Subscription UpdateSubscription(Subscription s);
	void DeleteSubscription(long id);
	List<Subscription> ShowAllSubscription();

	
}
