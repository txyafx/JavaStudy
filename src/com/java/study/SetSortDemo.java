package com.java.study;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetSortDemo {
	public static void main(String args[]) {
		Set set1 = new HashSet();
		Set set2 = new LinkedHashSet();

		for (int i = 0; i < 5; i++) { // ����һ������������������Set��
			int s = (int) (Math.random() * 100);
			set1.add(new Integer(s));
			set2.add(new Integer(s));
			System.out.println("�� " + i + " �����������Ϊ��" + s);
		}
		System.out.println("δ����ǰHashSet��" + set1);
		System.out.println("δ����ǰLinkedHashSet��" + set2); // ʹ��TreeSet���������Set�����ع�������
		Set sortedSet = new TreeSet(set1);
		System.out.println("����� TreeSet ��" + sortedSet);
	}
}
