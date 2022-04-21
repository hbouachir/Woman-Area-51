package tn.esprit.spring.womanarea51.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;
import tn.esprit.spring.womanarea51.repositories.EventRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.repositories.feedbackRepository;

@Service
public class feedbackServiceImp implements IfeedbackService {

	@Autowired
	feedbackRepository FeedRepository;

	@Autowired
	EventRepository ERepository;

	@Autowired
	UserRepository URepository;

	// feedback instance created to indicate that a user is participating in an
	// event, feedback attributes are initiated as null
	public void Participate(feedback e) {
		FeedRepository.save(e);
	}

	public feedback EditFeedback(feedback e) {
		feedback updated = FeedRepository.save(e);
		return updated;

	}

	public void DeleteFeedback(feedback e) {
		FeedRepository.delete(e);
	}

	public feedback FindFeedback(Long id) {
		feedback e = new feedback();
		e = FeedRepository.findById(id).get();
		return e;
	}

	public List<feedback> ListFeedbacks() {
		List<feedback> list = new ArrayList<feedback>();
		FeedRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public List<feedback> FindFeedbacksByEvent(Long id) {

		List<feedback> list = new ArrayList<feedback>();
		event Event = ERepository.findById(id).get();
		FeedRepository.findAll().forEach(e -> {
			if (e.getEvent_feedback() == Event)
				list.add(e);
		});
		return list;
	}

	public List<feedback> FindFeedbacksByParticipant(Long id) {

		List<feedback> list = new ArrayList<feedback>();
		User participant = URepository.findById(id).get();
		FeedRepository.findAll().forEach(e -> {
			if (e.getParticipant() == participant)
				list.add(e);
		});
		return list;
	}

	public List<feedback> FindFeedbackByUserAndEvent(Long userID, Long eventId) {

		List<feedback> list = new ArrayList<feedback>();
		User participant = URepository.findById(userID).get();
		event Event = ERepository.findById(eventId).get();
		FeedRepository.findAll().forEach(e -> {
			if (e.getParticipant() == participant & e.getEvent_feedback() == Event)
				list.add(e);
		});
		return list;
	}

	// calculate avg rating per event
	public double AVGEventRating(List<feedback> list) {

		double avg = 0;
		DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
		List<Double> ratings = new LinkedList<>();
		list.forEach(f -> {
			ratings.add((double) f.getRating());
			System.out.println(f.getRating());
		});
		Iterator<Double> iterator = ratings.listIterator();
		while (iterator.hasNext()) {

			doubleSummaryStatistics.accept(iterator.next());

		}
		avg = doubleSummaryStatistics.getAverage();

		System.out.println(avg);
		/*
		 * DoubleSummaryStatistics AvgEventsRatingStatistics
		 * =list.stream().mapToDouble(feedback::getRating).summaryStatistics();
		 * avg=AvgEventsRatingStatistics.getAverage();
		 */
		return avg;
	}

	public double MinEventRating(List<feedback> list) {

		double min = 0;

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getRating)
				.summaryStatistics();
		min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventRating(List<feedback> list) {

		double max = 0;

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getRating)
				.summaryStatistics();
		max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	public feedback calculRating(feedback f) {

		double sum = f.getFutureEvents() + f.getLocation() + f.getObject() + f.getAddedValue() + f.getOrganizers()
				+ f.getRecommend() + f.getService();
		double avg = sum / 7;
		float rounded = (float) Math.round(avg * 100) / 100;
		f.setRating(rounded);
		return (f);

	}

	public List<event> FilterEventBycurrentDate() throws ParseException {

		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;
		int y = cal.get(Calendar.YEAR);
		YearMonth yearMonthObject = YearMonth.of(1999, 2);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		System.out.println(y);
		String infDate = "00-" + String.valueOf(m) + "-" + String.valueOf(y) + " 00:00:00";
		String SupDate = String.valueOf(daysInMonth) + "-" + String.valueOf(m) + "-" + String.valueOf(y) + " 59:59:59";
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:s");
		Date MonthBeg = sdformat.parse(infDate);
		Date MonthEnd = sdformat.parse(SupDate);
		List<event> list = new ArrayList<event>();
		List<event> filtered = new ArrayList<event>();
		list = ERepository.findAll();
		list.forEach(e -> {
			if (e.getEventDateStart().before(MonthEnd) && e.getEventDateStart().after(MonthBeg))
				filtered.add(e);
		});

		return filtered;
	}

	public double MaxRating() throws ParseException {
		double max = 0;
		List<event> list = new LinkedList<event>();
		list = FilterEventBycurrentDate();
		Iterator<event> iterator = list.listIterator();
		while (iterator.hasNext()) {
			List<feedback> feed = new ArrayList<feedback>();
			iterator.next().getFeedbacks().forEach(f -> feed.add(f));
			if (AVGEventRating(feed) >= max)
				max = AVGEventRating(feed);

		}

		return max;
	}

	public List<event> BestOfThisMonth(List<event> filtered) {
		List<event> bestEvents = new ArrayList<event>();
		filtered.forEach(e -> {
			List<feedback> feed = new ArrayList<feedback>();
			e.getFeedbacks().forEach(f -> feed.add(f));
			try {
				if (AVGEventRating(feed) == MaxRating())
					bestEvents.add(e);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		});

		return bestEvents;

	}

	public List<event> Upcomingevents(User u) {
		List<event> list = new ArrayList<event>();
		Date d = new Date();
		FeedRepository.findAll().forEach(f -> {
			if ((f.getEvent_feedback().getEventDateStart().compareTo(d)) >= 0)
				list.add(f.getEvent_feedback());
		});

		return list;
	}

	public double AVGEventObjectRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getObject)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventObjectRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getObject)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventObjectRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getObject)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	public double AVGEventOrganizersRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getOrganizers)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventOrganizersRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getOrganizers)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventOrganizersRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getOrganizers)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}
	
	public double AVGEventLocationRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getLocation)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventLocationRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getLocation)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventLocationRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getLocation)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	public double AVGEventAddedValueRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getAddedValue)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventAddedValueRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getAddedValue)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventAddedValueRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getAddedValue)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	public double AVGEventRecommendRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getRecommend)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventRecommendRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getRecommend)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventRecommendRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getRecommend)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	public double AVGEventServiceRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getService)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventServiceRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getService)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventServiceRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getService)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

	
	public double AVGEventFutureEventsRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getFutureEvents)
				.summaryStatistics();
		Double avg = AvgEventsRatingStatistics.getAverage();

		return avg;
	}

	public double MinEventFutureEventsRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getFutureEvents)
				.summaryStatistics();
		double min = AvgEventsRatingStatistics.getMin();

		return min;
	}

	public double MaxEventFutureEventsRating(List<feedback> list) {

		DoubleSummaryStatistics AvgEventsRatingStatistics = list.stream().mapToDouble(feedback::getFutureEvents)
				.summaryStatistics();
		double max = AvgEventsRatingStatistics.getMax();

		return max;
	}

}
