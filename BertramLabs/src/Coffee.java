/*
 * The coffee dilemma: Who should pay for coffee?
 * @author Maddi Tajchman
 * Date: 2/11/2024
 * 
 * Coffee Class: This class includes the main to run the program, as well as the methods needed for the 
 * program to correctly operate.
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Coffee {
	private ArrayList<Consumer> consumers = new ArrayList<Consumer>(); //holds all of the consumers and their drink specifics
	private ArrayList<String> list = new ArrayList<String>(); //holds each consumer multiple times depending on the price of their coffee
	private int numConsumers;
	private String temp;
	
	/*
	 * This method gets the user input for the number of consumers, 
	 * as well as the name, name of the drink, and price of the
	 * drink for each consumer.
	 */
	public void getUserInput() {
		Scanner scan = new Scanner(System.in);
		boolean badInput = true;
		while(badInput == true) {
			try {
				System.out.print("Enter the number of consumers: "); 
				temp = scan.nextLine();
				numConsumers = Integer.parseInt(temp);
				if(numConsumers <= 0) {
					System.out.println("Enter a number greater than 0");
					badInput = true;
				} else {
					badInput = false;
				}
				//if user enters a non-integer for the number of consumers,
				//then they have to try again
			}catch(NumberFormatException e) { 
				System.out.println("Incorrect format for the number of consumers");
				System.out.println();
			}
		}
		/*
		 * loops through the number of consumers and gets their
		 * specifics for later use. If they enter a non-number (integer or double)
		 * for the drink price, they will have to try again. This loop
		 * also adds each consumer to the consumers arrayList
		 */
		for(int i = 0; i < numConsumers; i++) {
			scan = new Scanner(System.in);
			System.out.print("Enter the consumer's name: ");
			String name = scan.nextLine();
			System.out.print("Enter " + name + "'s drink: ");
			String drinkName = scan.nextLine();
			double drinkPrice = 0;
			while(true) {
				try {
					System.out.print("Enter " + drinkName + " price: $");
					drinkPrice = Math.round(scan.nextDouble() * 100.0) / 100.0;
					if(drinkPrice < 0) {
						System.out.println("Drink price must be $0 or greater");
					}else {
						break;
					}
				}catch(InputMismatchException ex) {
					System.out.println("Incorrect format for the drink price");
					scan.nextLine();
				}	
			}
			consumers.add(new Consumer(name, drinkName, drinkPrice));
			System.out.println();
		}
	}
	
	/*
	 * This method goes through each consumer in the consumers
	 * arrayList and prints out their data using the toString()
	 * method in the Consumer class.
	 */
	public void printInfo() {
		for (Consumer consumer : consumers) {
			System.out.println(consumer);
		}
	}
	
	/*
	 * This method goes through each consumers, the price if rounded to the nearest integer 
	 * and then loaded into the list arrayList twice the number of times the drink cost.
	 * This way the list proportionally represents the price of all the drinks.
	 */
	public String randomPick() {
		for(Consumer consumer : consumers) {
			int currPrice = (int) Math.round(consumer.getDrinkPrice());
			String name = consumer.getName();
			for(int i = 0; i < (currPrice)*2; i++) {
				list.add(name);
			}
		}
		/*
		 * A random number is chosen for the index of the list which picks the
		 * person who will pay next. The range is 0 to the max size - 1 to account
		 * for all possible names in the array. This section also adds to the 
		 * consumers # times paid variable.
		 */
		if(list.size() != 0) {
			int randomNum = (int)(Math.random() * ((list.size()-1) - 0 + 1));
			for(Consumer consumer : consumers) {
				if(list.get(randomNum) == consumer.getName()) {
					consumer.setTimesPaid(consumer.getTimesPaid() + 1);
				}
			}
			//returns who should pay next
			return (list.get(randomNum));
		}
		return "none";
	}
	
	/*
	 * Loops through each consumer and prints their name and the number 
	 * of times they paid over the course of the 'calendar'
	 */
	public void timesPaid() {
		for(Consumer consumer : consumers) {
			System.out.println(consumer.getName() + " paid " + consumer.getTimesPaid() + " times");
		}
	}

	public static void main(String[] args) {
		Coffee coffee = new Coffee(); //makes a new instance of Coffee
		coffee.getUserInput();
		coffee.printInfo();
		System.out.println(); 
		Scanner input = new Scanner(System.in); //uses scanner to gather user input
		int day = 1;
		/*
		 * Continues to loop until the user chooses to quit, each loop it will
		 * run the randomPick() method and get a string of who should pay.
		 */
		while(true) {
			System.out.print("Type 'n' to see who should pay next. Type 'q' to quit.");
			String in = input.nextLine();
			if("q".equalsIgnoreCase(in)) {
				break;
			} else if ("n".equalsIgnoreCase(in)){
				String payment = coffee.randomPick();
				if(payment == "none") {
					System.out.println("No payment needed. Total is $0");
					break;
				} else {
					System.out.println("Day " + (day) + ": " + payment + " should pay");
					System.out.println();
					day++;
				}
			} else {
				System.out.println("Not a valid response. Try Again.");
			}
		}
		
		System.out.println();
		coffee.timesPaid();
	}
}