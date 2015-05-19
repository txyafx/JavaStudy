package com.java.study;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CarMarketDemo {

	public static void main(String[] args) {
		Market market = new Market();
		People bob = new People(new Certificate(100), "bob", 20000.0);
		Car bobcar = new Car("123", 10000.0, "red");
		bobcar.setOwner(bob);
		bob.addCar(bobcar);

		People tom = new People(new Certificate(101), "tom", 50000.0);
		Car tomcar1 = new Car("125", 12000.0, "green");
		tomcar1.setOwner(tom);
		Car tomcar2 = new Car("126", 13000.0, "blue");
		tomcar2.setOwner(tom);

		tom.addCar(tomcar1);
		tom.addCar(tomcar2);

		bob.printCars();
		tom.printCars();
	}

}

class Market {
	private Set cars;// ������Ϣ
	private Map people; // ������Ա��Ϣ

	public Market() {
		cars = new HashSet();
		people = new HashMap();
	}

	public static boolean sellCar(People p1, Car car, People p2) {
		p2.buyCarFromPeople(car, p1);
		return false;
	}

	public void addPeople(People people) {
		this.people.put(people.getId(), people);
	}

	public boolean registCar(Car car) {
		cars.add(car);
		return true;
	}

	public boolean registCars(Set cars) {
		if (cars instanceof HashSet) {
			this.cars.addAll(cars);
		}
		return false;
	}
}

class People {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public Collection getCars() {
		return cars;
	}

	public void setCars(List cars) {
		this.cars = cars;
	}

	public Certificate getId() {
		return id;
	}

	private final Certificate id; // ���֤
	private String name;
	private double cash;
	private Collection cars;
	private String tradeMsg;

	public People(Certificate id, String name, double cash) {
		this.id = id;
		this.name = name;
		this.cash = cash;
		this.tradeMsg = new String();
		this.cars = new ArrayList();
	}

	public boolean buyCar(Car car) {
		if (car.getPrice() > this.cash) {
			tradeMsg = "[" + car.toString() + "]" + "'s price is out of "
					+ name + "'s cash.";
			return false;
		}
		if (cars.contains(car)) {
			tradeMsg = "[" + car.toString() + "]" + " is " + name
					+ "'s owner car.";
			return false;
		}
		this.addCar(car);
		car.setOwner(this);
		this.cash -= car.getPrice();
		return true;
	}

	public boolean sellCar(Car car) {
		if (cars.contains(car)) {
			this.cash += car.getPrice();
			this.removeCar(car);
			return true;
		}
		tradeMsg = "[" + car.toString() + "]" + " is not " + name
				+ "'s owner car.";
		return false;
	}

	public boolean buyCarFromPeople(Car car, People people) {
		if (people.sellCar(car)) {
			return this.buyCar(car);
		}

		return false;
	}

	void addCar(Object obj) {
		if (obj instanceof Car) {
			Car car = (Car) obj;
			cars.add(car);
		}
	}

	void removeCar(Object obj) {
		if (obj instanceof Car) {
			Car car = (Car) obj;
			cars.remove(car);
		}
	}

	public void printCars() {
		Object s[] = cars.toArray();
		for (Object obj : s) {
			System.out.println(obj.toString());
		}
	}

	public String toString() {
		return this.name + " " + this.id.toString() + " ";
	}
}

class Certificate {
	private final int id;

	public Certificate(int id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Certificate) {
			Certificate cert = (Certificate) obj;
			return this.id == cert.id;
		}
		return false;
	}

	public String toString() {
		return Integer.toString(this.id);
	}

	public int hashCode() {
		return this.id;
	}
}

class Car {
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public People getOwner() {
		return owner;
	}

	public void setOwner(People owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	private final String id;
	private double price;
	private final String color;
	private People owner;

	public Car(String id, double price, String color /* ,People owner */) {
		this.id = id;
		this.price = price;
		this.color = color;
		// this.owner = owner;
		// this.owner.addCar(this);
	}

	public String toString() {
		return owner.toString() + "�ĳ������ļ۸�Ϊ" + price + ",��ɫΪ" + color + "���ƺ�Ϊ"
				+ id;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			Car car = (Car) obj;
			return car.id.equals(car.id);
		}
		return false;
	}
}
