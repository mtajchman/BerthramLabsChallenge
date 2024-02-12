
public class Consumer {
	private String name;
	private String drinkName;
	private double drinkPrice;
	private int timesPaid = 0;	
	
	public Consumer(String name, String drinkName, double drinkPrice) {
		super();
		this.name = name;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
	}


	public String getName() {
		return name;
	}


	public double getDrinkPrice() {
		return drinkPrice;
	}

	public int getTimesPaid() {
		return timesPaid;
	}


	public void setTimesPaid(int timesPaid) {
		this.timesPaid = timesPaid;
	}
	
	@Override
	public String toString() {
		return name + " gets the " + drinkName + " for $" + drinkPrice;
	}
	
	

}
