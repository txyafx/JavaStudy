package com.java.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(2015);
			while (true) {
				Socket socket = serverSocket.accept();
				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream())); // ×Ö·ûÁ÷
				String result = bufferReader.readLine();
				System.out.println("Client say : " + result);

				PrintWriter printWriter = new PrintWriter(
						socket.getOutputStream());
				printWriter.println("hello Client, I am Server!");
				printWriter.flush();
				printWriter.close();
				bufferReader.close();
				socket.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}

	}

}
