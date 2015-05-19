package com.java.study;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map map1 = new HashMap();
		Map map2 = new HashMap();

		map1.put("1", "a1");
		map1.put("2", "b1");

		map2.put("10", "a2");
		map2.put("11", "b2");

		System.out.println("map1.get(\"1\")=" + map1.get("1"));
		System.out.println("map1.get(\"2\")=" + map1.get("2"));

		System.out.println("map1.remove(\"1\")=" + map1.remove("1"));
		System.out.println("map1.get(\"1\")=" + map1.get("1"));

		map1.putAll(map2);
		map2.clear();

		System.out.println("map1 is empty: " + map1.isEmpty());
		System.out.println("map2 is empty: " + map2.isEmpty());

		System.out.println("map1键值对的个数: " + map1.size());

		System.out.println("KeySet= " + map1.keySet());
		System.out.println("values= " + map1.values());
		System.out.println("entrySet= " + map1.entrySet());// [2=b1, 10=a2,
															// 11=b2]
		System.out.println("map1是否包含键： 11 =" + map1.containsKey("11"));
		System.out.println("map1是否包含值： a1 =" + map1.containsValue("a1"));

	}
}
