/*
 * 将反射用于工厂模式
 */
package com.java.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReflectFactoryDemo {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		/*
		 * fruit f = Factory.getInstance("com.java.study.Apple"); f.eat();
		 */
		Properties prpt = Init.getPrpt();
		fruit f = Factory.getInstance(prpt.getProperty("apple"));
		f.eat();
	}

}

interface fruit {
	public abstract void eat();
}

class Apple implements fruit {

	@Override
	public void eat() {
		System.out.println("Apple");
	}
}

class Orange implements fruit {

	@Override
	public void eat() {
		System.out.println("Orange");
	}

}

class Factory {
	public static fruit getInstance(String className) {
		fruit f = null;
		try {
			f = (fruit) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}

class Init {
	public static Properties getPrpt() throws FileNotFoundException,
			IOException {
		Properties prpt = new Properties();
		File f = new File("fruit.properties");
		if (f.exists()) {
			prpt.load(new FileInputStream(f));
		} else {
			prpt.setProperty("apple", "com.java.study.Apple");
			prpt.setProperty("orange", "com.java.study.Orange");
			prpt.store(new FileOutputStream(f), "FRUIT CLASS");
		}
		return prpt;
	}
}