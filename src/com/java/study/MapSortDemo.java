package com.java.study;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MapSortDemo {

	public static void main(String[] args) {
		Map map1 = new HashMap();
		Map map2 = new LinkedHashMap();

		Random random = new Random();

		for (int i = 0; i <= 5; i++) {
			int s = random.nextInt(10) + 1;
			map1.put(s, "�� " + i + " �������Ԫ��: " + s + "\n");
			map2.put(s, "�� " + i + " �������Ԫ��: " + s + "\n");
		}
		System.out.println("δ����ǰHashMap: " + map1);
		System.out.println("δ����ǰLinkedHashMap: " + map2);

		Map sortedMap = new TreeMap(map1);
		System.out.println("����� " + sortedMap);
		System.out.println("����� " + new TreeMap(map2));
	}

}
