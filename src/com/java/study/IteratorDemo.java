package com.java.study;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {

	public static void main(String[] args) {
		Collection collection = new ArrayList();
		for (int i = 0; i < 5; i++) {
			collection.add(i);
		}
		Iterator iterator = collection.iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			System.out.println("iterator= " + element);
		}
		if (collection.isEmpty()) {
			System.out.println("collection is empty.");
		} else {
			System.out.println("collection is not empty! Size is: "
					+ collection.size());
		}
		Iterator iterator2 = collection.iterator();
		while (iterator2.hasNext()) {
			Object element = iterator2.next();
			System.out.println("remove之前：" + element);
			iterator2.remove();
		}
		Iterator iterator3 = collection.iterator();
		if (!iterator3.hasNext()) {
			System.out.println("已经没有元素了");
		}
		if (collection.isEmpty()) {
			System.out.println("collection is empty");
		}
	}
}
