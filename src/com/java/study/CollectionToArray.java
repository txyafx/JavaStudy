package com.java.study;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionToArray {

	public static void main(String[] args) {
		Collection collection1 = new ArrayList();
		collection1.add("000");
		collection1.add("111");
		collection1.add("222");

		System.out.println("collection1的大小: " + collection1.size());
		System.out.println("collection1的内容: " + collection1);

		collection1.remove("000");
		System.out.println("collection1的内容: " + collection1);
		System.out.println("collection1中是否包含000: "
				+ collection1.contains("000"));
		System.out.println("collection1中是否包含111: "
				+ collection1.contains("111"));
		System.out.println("collection1中是否包含222: "
				+ collection1.contains("222"));

		Collection collection2 = new ArrayList();
		collection2.addAll(collection1);
		System.out.println("collection2的内容: " + collection2);
		collection2.clear();
		System.out.println("collection2是否为空: " + collection2.isEmpty());

		Object s[] = collection1.toArray();
		for (Object obj : s) {
			System.out.print(obj + " ");
		}

	}

}
