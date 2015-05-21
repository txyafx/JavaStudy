package com.java.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientDemo2 extends Socket {

	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 2015;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public SocketClientDemo2() throws Exception {
		super(SERVER_IP, SERVER_PORT);
		client = this;
		in = new BufferedReader(new InputStreamReader(this.getInputStream()));
		out = new PrintWriter(this.getOutputStream(), true);

		new readLineThread();

		while (true) {
			/*
			 * stdin ==> socket
			 */
			in = new BufferedReader(new InputStreamReader(System.in));
			String line = in.readLine();
			out.println(line);
		}
	}

	class readLineThread extends Thread {
		private BufferedReader buff;

		public readLineThread() {
			try {
				this.buff = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				System.out.println(buff);
				start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					String line = this.buff.readLine();
					if ("byeClient".equals(line)) {
						break;
					} else {
						System.out.println(line);
					}
				}
				in.close();
				out.close();
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("exit client read line thread.");
			}
		}
	}

	public static void main(String[] args) {
		try {
			new SocketClientDemo2();// Æô¶¯¿Í»§¶Ë
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
