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
			map1.put(s, "第 " + i + " 个放入的元素: " + s + "\n");
			map2.put(s, "第 " + i + " 个放入的元素: " + s + "\n");
		}
		System.out.println("未排序前HashMap: " + map1);
		System.out.println("未排序前LinkedHashMap: " + map2);

		Map sortedMap = new TreeMap(map1);
		System.out.println("排序后： " + sortedMap);
		System.out.println("排序后： " + new TreeMap(map2));
	}

}
