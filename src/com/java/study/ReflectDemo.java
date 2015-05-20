package com.java.study;

import java.lang.reflect.Constructor;

/**
 * ͨ��һ�������������İ���������
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
		// ͨ��һ�������������İ���������
		System.out.println("ͨ��һ�������������İ���������");
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());
		System.out.println();

		// ������Ķ�����ʵ����Class��ʵ��
		// ʵ����Class�����
		System.out.println("ͨ��һ��ʵ����Class������������İ���������");
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try {
			// һ�㾡������������ʽ
			demo1 = Class.forName("com.java.study.Demo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		demo2 = new Demo().getClass();
		demo3 = Demo.class;

		System.out.println("������   " + demo1.getName());
		System.out.println("������   " + demo2.getName());
		System.out.println("������   " + demo3.getName());
		System.out.println();

		// ͨ��Classʵ����������Ķ���
		System.out.println("ͨ��Classʵ����������Ķ���");
		Class<?> demo4 = null;
		try {
			demo4 = Class.forName("com.java.study.Humman");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Humman human = null;
		try {
			human = (Humman) demo4.newInstance(); // ʹ��Classʵ����������Ķ����ʱ��һ��Ҫ�Լ������޲����Ĺ��캯��

		} catch (Exception e) {
			e.printStackTrace();
		}
		human.setName("nero");
		human.setAge(27);
		System.out.println(human);
		System.out.println();

		// ͨ��Class�����������еĹ��캯�� ��Ҳ����ͨ�����ַ�ʽͨ��Class����������Ķ���
		System.out.println("ͨ��Class�����������еĹ��캯�� ��Ҳ����ͨ�����ַ�ʽͨ��Class����������Ķ���");
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

		// ȡ��ȫ���Ĺ��캯��
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
