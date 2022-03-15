package tn.esprit.spring.womanarea.demo.Entities.Chart;

public class EventC {
	private String name;
	private int amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public EventC(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	

}
