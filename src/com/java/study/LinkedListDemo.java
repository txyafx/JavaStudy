package com.java.study;

import java.util.LinkedList;

public class LinkedListDemo {

	public static void main(String[] args) {
		LinkedList queue = new LinkedList();
		queue.addFirst("Head1");
		queue.addFirst("Head2");
		queue.addFirst("Head3");
		queue.addFirst("Head4");
		queue.addFirst("Head5");

		System.out.println("queue: " + queue);

		//�Ƴ����һ��
		queue.removeLast();
		System.out.println("queue: " + queue);
		
		//�Ƴ���һ��
		queue.removeFirst();
		System.out.println("queue: " + queue);
	}

}
