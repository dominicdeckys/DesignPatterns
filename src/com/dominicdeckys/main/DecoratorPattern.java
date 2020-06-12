/**
 * 
 */
package com.dominicdeckys.main;

import java.util.Scanner;

/**
 * @author domin
 *
 */
public class DecoratorPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n\nWelcome to Dominic's Pizza shop, select a pizza type:\n1: Flatbread\n2: Regular\n3: Deep Dish\n");
		Pizza pizza;
		switch (s.nextInt()) {
		case 1:
			pizza = new Flatbread();
			break;
		case 2:
			pizza = new Regular();
			break;
		case 3:
			pizza = new DeepDish();
			break;
		default:
			System.out.println("Bad input");
			return;
		}
		while (true) {
			System.out.println("\n\n\n\n\n\n\nSelect Toppings:\n1: Pepperoni\n2: Olives\n3: Mushrooms\n4: Finished\n");
			System.out.println("Current Pizza:\n" + pizza.getDescription() + "\nCurrent Cost: $" + pizza.cost());
			switch (s.nextInt()) {
			case 1:
				pizza = new Pepperoni(pizza);
				break;
			case 2:
				pizza = new Olives(pizza);
				break;
			case 3:
				pizza = new Mushrooms(pizza);
				break;
			case 4:
				System.out.println("Final Pizza:\n" + pizza.getDescription() + "\nCurrent Cost: $" + pizza.cost());
				return;
			default:
				System.out.println("Bad input");
			}
		}
	}

}

abstract class Pizza {
	String description = "Unknown";
	
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}

abstract class PizzaDecorator extends Pizza {
	public abstract String getDescription();
}

class Flatbread extends Pizza {
	
	public Flatbread() {
		description = "Flatbread";
	}

	@Override
	public double cost() {
		return 5.5;
	}
	
}

class DeepDish extends Pizza {
	
	public DeepDish() {
		description = "Deep Dish";
	}

	@Override
	public double cost() {
		return 9.99;
	}
	
}

class Regular extends Pizza {
	
	public Regular() {
		description = "Regular";
	}

	@Override
	public double cost() {
		return 6.3;
	}
	
}

class Pepperoni extends PizzaDecorator {
	Pizza pizza;
	
	public Pepperoni(Pizza p) {
		this.pizza = p;
	}

	@Override
	public String getDescription() {
		return this.pizza.getDescription() + ", pepperoni";
	}

	@Override
	public double cost() {
		return this.pizza.cost() + 2.4;
	}
	
}

class Olives extends PizzaDecorator {
	Pizza pizza;
	
	public Olives(Pizza p) {
		this.pizza = p;
	}

	@Override
	public String getDescription() {
		return this.pizza.getDescription() + ", olives";
	}

	@Override
	public double cost() {
		return this.pizza.cost() + 0.95;
	}
	
}

class Mushrooms extends PizzaDecorator {
	Pizza pizza;
	
	public Mushrooms(Pizza p) {
		this.pizza = p;
	}

	@Override
	public String getDescription() {
		return this.pizza.getDescription() + ", mushrooms";
	}

	@Override
	public double cost() {
		return this.pizza.cost() + 1.25;
	}
	
}