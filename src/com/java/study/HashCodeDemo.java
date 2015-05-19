package com.java.study;

import java.util.HashMap;

public class HashCodeDemo {

	public static void main(String[] args) {

		HashMap map = new HashMap();
		Person p1 = new Person("张三", new Code(123));
		Person p2 = new Person("李四", new Code(456));
		map.put(p1.id, p1);// 我们根据身份证来作为key值存放到Map中
		map.put(p2.id, p2);
		System.out.println("HashMap 中存放的人员信息:\n" + map); // 张三 改名为：张山 但是还是同一个人。
		Person p3 = new Person("张山", new Code(123));
		map.put(p3.id, p3);
		System.out.println("张三改名后 HashMap 中存放的人员信息:\n" + map);
		// 查找身份证为：123 的人员信息
		System.out.println("查找身份证为：123 的人员信息:" + map.get(new Code(123)));
	}
}

// 身份证类
class Code {
	public final int id;// 身份证号码已经确认,不能改变

	public Code(int i) {
		this.id = i;
	}

	// 身份号号码相同，则身份证相同
	public boolean equals(Object anObject) {
		if (anObject instanceof Code) {
			Code other = (Code) anObject;
			return this.id == other.id;
		}
		return false;
	}

	public String toString() {
		return "身份证:" + id;
	}

	// 覆写hashCode方法，并使用身份证号作为hash值
	public int hashCode() {
		return id;
	}
}

// 人员信息类
class Person {
	public Code id;// 身份证 public
	String name;// 姓名

	public Person(String name, Code id) {
		this.id = id;
		this.name = name;
	}

	// 如果身份证号相同，就表示两个人是同一个人
	public boolean equals(Object anObject) {
		if (anObject instanceof Person) {
			Person other = (Person) anObject;
			return this.id.equals(other.id);
		}
		return false;
	}

	public String toString() {
		return "姓名:" + name + " 身份证:" + id.id + "\n";
	}
}