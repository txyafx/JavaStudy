package com.java.study;

public class ThreadJoinTest implements Runnable {
	public static int a = 0;

	@Override
	public void run() {
		for (int k = 0; k < 5; k++) {
			a = a + 1;
			// System.out.println("=>" + a);
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Runnable r = new ThreadJoinTest(); Thread t = new Thread(r);
		 * t.start(); // t.join(); for (int i = 0; i < 300; i++) {
		 * System.out.print(i); } System.out.println();
		 * 
		 * System.out.println(a);
		 */
		/*
		 * String threadName = Thread.currentThread().getName();
		 * System.out.println(threadName + " start."); CustomThread1 t1 = new
		 * CustomThread1(); CustomThread t = new CustomThread(t1); try {
		 * t1.start(); Thread.sleep(2000); t.start(); //t.join(); } catch
		 * (Exception e) { System.out.println("Exception from main"); }
		 * System.out.println(threadName + " end!");
		 */

		Thread tt = new Thread(new RunnableImpl());
		new ThreadTest(tt).start();
		tt.start();
		try {
			tt.join();
			System.out.println("joinFinish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class CustomThread1 extends Thread {
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(threadName + " loop at " + i);
				Thread.sleep(1000);
			}
			System.out.println(threadName + " end.");
		} catch (InterruptedException e) {
			System.out.println("Exception from " + threadName + ".run");
		}

	}
}

class CustomThread extends Thread {
	CustomThread1 t1;

	public CustomThread(CustomThread1 t) {
		this.t1 = t;
	}

	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		try {
			t1.join();
			System.out.println(threadName + " end.");
		} catch (InterruptedException e) {
			System.out.println("Exception from " + threadName + ".run");
		}

	}
}

class RunnableImpl implements Runnable {

	public void run() {
		try {
			System.out.println("Begin sleep");
			Thread.sleep(2000);
			System.out.println("End sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadTest extends Thread {

	Thread thread;

	public ThreadTest(Thread thread) {
		this.thread = thread;
	}

	@Override
	public void run() {
		synchronized (thread) {
			System.out.println("getObjectLock");
			try {
				Thread.sleep(9000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("ReleaseObjectLock");
		}
	}
}
