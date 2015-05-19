package com.java.study;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {

	public static void main(String[] args) {
		Set set1 = new HashSet();
		if (set1.add("a")) {
			System.out.println("1 add true");
		}
		if (!set1.add("a")) {
			System.out.println("1 add false");
		}
		set1.add("000");
		set1.add("111");
		set1.add("222");
		System.out.println("set1的大小: " + set1.size());
		System.out.println("set1的内容: " + set1);
		set1.remove("000");
		System.out.println("集合set1移除 000 后的内容：" + set1);
		System.out.println("集合set1中是否包含000 ：" + set1.contains("000"));
		System.out.println("集合set1中是否包含111 ：" + set1.contains("111"));

		Set set2 = new HashSet();
		set2.add("111");
		set2.addAll(set1);
		System.out.println("set2的内容: " + set1);
		set2.clear();

		System.out.println("set2是否为空: " + set2.isEmpty());

		Iterator itetator = set1.iterator();
		while (itetator.hasNext()) {
			Object element = itetator.next();
			System.out.println("iterator = " + element);
		}

		Object s[] = set1.toArray();
		for (Object obj : s) {
			System.out.println(obj);
		}
		

	}

}
