package com.java.study;

import java.util.HashMap;

public class HashCodeDemo {

	public static void main(String[] args) {

		HashMap map = new HashMap();
		Person p1 = new Person("����", new Code(123));
		Person p2 = new Person("����", new Code(456));
		map.put(p1.id, p1);// ���Ǹ������֤����Ϊkeyֵ��ŵ�Map��
		map.put(p2.id, p2);
		System.out.println("HashMap �д�ŵ���Ա��Ϣ:\n" + map); // ���� ����Ϊ����ɽ ���ǻ���ͬһ���ˡ�
		Person p3 = new Person("��ɽ", new Code(123));
		map.put(p3.id, p3);
		System.out.println("���������� HashMap �д�ŵ���Ա��Ϣ:\n" + map);
		// �������֤Ϊ��123 ����Ա��Ϣ
		System.out.println("�������֤Ϊ��123 ����Ա��Ϣ:" + map.get(new Code(123)));
	}
}

// ���֤��
class Code {
	public final int id;// ���֤�����Ѿ�ȷ��,���ܸı�

	public Code(int i) {
		this.id = i;
	}

	// ��ݺź�����ͬ�������֤��ͬ
	public boolean equals(Object anObject) {
		if (anObject instanceof Code) {
			Code other = (Code) anObject;
			return this.id == other.id;
		}
		return false;
	}

	public String toString() {
		return "���֤:" + id;
	}

	// ��дhashCode��������ʹ�����֤����Ϊhashֵ
	public int hashCode() {
		return id;
	}
}

// ��Ա��Ϣ��
class Person {
	public Code id;// ���֤ public
	String name;// ����

	public Person(String name, Code id) {
		this.id = id;
		this.name = name;
	}

	// ������֤����ͬ���ͱ�ʾ��������ͬһ����
	public boolean equals(Object anObject) {
		if (anObject instanceof Person) {
			Person other = (Person) anObject;
			return this.id.equals(other.id);
		}
		return false;
	}

	public String toString() {
		return "����:" + name + " ���֤:" + id.id + "\n";
	}
}