/**
 * 
 */
package com.dominicdeckys.main;

import java.util.ArrayList;

/**
 * @author domin
 *
 */
public class ObserverPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<WeatherDisplay> weatherdisplays = new ArrayList<WeatherDisplay>();
		WeatherStation station = new WeatherStation(75);
		for (int t = 0; t < 10; t++) {
			weatherdisplays.add(new WeatherDisplay(station, t));
		}
		for (WeatherDisplay d: weatherdisplays) {
			d.display();
		}
		station.setTemp(65.5f);
		for (WeatherDisplay d: weatherdisplays) {
			d.display();
		}
		
		//lets remove some
		for (WeatherDisplay d: weatherdisplays) {
			if (d.getId() % 3 == 0) {
				station.removeObserver(d);
			}
		}
		station.setTemp(96.4f);
		for (WeatherDisplay d: weatherdisplays) {
			d.display();
		}
		
	}
	
//	private static void displayAll(ArrayList<Displayer> displays) {
//		for (Displayer d: displays) {
//			d.display();
//		}
//	}

}

interface Observer {
	public void update(Object o);
}

interface Displayer {
	public void display();
}

interface Observable {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}

class WeatherStation implements Observable {
	private ArrayList<Observer> observers;
	private float temp;

	public WeatherStation(float startingtemp) {
		observers = new ArrayList<Observer>();
		this.temp = startingtemp;
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o: observers) {
			o.update(temp);
		}
	}
	
	public void setTemp(float temp) {
		this.temp = temp;
		notifyObservers();
	}
	
}

class WeatherDisplay implements Observer, Displayer {
	private float temp;
	private int id;
	
	public WeatherDisplay(Observable observable, int id) {
		temp = 0;
		this.id = id;
		observable.addObserver(this);
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public void update(Object o) {
		if (o instanceof Float) {
			temp = (Float) o;
		}
	}

	@Override
	public void display() {
		System.out.println("The temp is: " + temp);
	}
	
}