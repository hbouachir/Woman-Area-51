package tn.esprit.spring.womanarea.demo.Entities.Chart;

public class SujetC {
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
	public SujetC(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	

}
