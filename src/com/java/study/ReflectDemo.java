package com.java.study;

import java.lang.reflect.Constructor;

/**
 * 通过一个对象获得完整的包名和类名
 * */
class Demo {

}

class Humman {

	public Humman() {

	}

	public Humman(String name) {
		this.name = name;
	}

	public Humman(int age) {
		this.age = age;
	}

	public Humman(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Humman [name=" + name + ", age=" + age + "]";
	}

	private String name;
	private int age;

}

public class ReflectDemo {

	public static void main(String[] args) {
		// 通过一个对象获得完整的包名和类名
		System.out.println("通过一个对象获得完整的包名和类名");
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());
		System.out.println();

		// 所有类的对象其实都是Class的实例
		// 实例化Class类对象
		System.out.println("通过一个实例化Class类对象获得完整的包名和类名");
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try {
			// 一般尽量采用这种形式
			demo1 = Class.forName("com.java.study.Demo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		demo2 = new Demo().getClass();
		demo3 = Demo.class;

		System.out.println("类名称   " + demo1.getName());
		System.out.println("类名称   " + demo2.getName());
		System.out.println("类名称   " + demo3.getName());
		System.out.println();

		// 通过Class实例化其他类的对象
		System.out.println("通过Class实例化其他类的对象");
		Class<?> demo4 = null;
		try {
			demo4 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Humman human = null;
		try {
			human = (Humman) demo4.newInstance(); // 使用Class实例化其他类的对象的时候，一定要自己定义无参数的构造函数

		} catch (Exception e) {
			e.printStackTrace();
		}
		human.setName("nero");
		human.setAge(27);
		System.out.println(human);
		System.out.println();

		// 通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
		System.out.println("通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）");
		Class<?> demo5 = null;
		try {
			demo5 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Humman human1 = null;
		Humman human2 = null;
		Humman human3 = null;
		Humman human4 = null;

		// 取得全部的构造函数
		Constructor<?> cons[] = demo5.getConstructors();
		System.out.println(cons[0]);
		System.out.println(cons[1]);
		System.out.println(cons[2]);
		System.out.println(cons[3]);
		try {

			human1 = (Humman) cons[0].newInstance("nero", 27);
			human2 = (Humman) cons[1].newInstance(27);
			human3 = (Humman) cons[2].newInstance("nero");
			human4 = (Humman) cons[3].newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(human1);
		System.out.println(human2);
		System.out.println(human3);
		System.out.println(human4);

		System.out.println();

	}
}
