package com.java.study;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetSortDemo {
	public static void main(String args[]) {
		Set set1 = new HashSet();
		Set set2 = new LinkedHashSet();

		for (int i = 0; i < 5; i++) { // 产生一个随机数，并将其放入Set中
			int s = (int) (Math.random() * 100);
			set1.add(new Integer(s));
			set2.add(new Integer(s));
			System.out.println("第 " + i + " 次随机数产生为：" + s);
		}
		System.out.println("未排序前HashSet：" + set1);
		System.out.println("未排序前LinkedHashSet：" + set2); // 使用TreeSet来对另外的Set进行重构和排序
		Set sortedSet = new TreeSet(set1);
		System.out.println("排序后 TreeSet ：" + sortedSet);
	}
}
