package com.java.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientDemo1 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 2015);
			socket.setSoTimeout(60000);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),
					true);
			String result = "";
			while (result.indexOf("bye") == -1) {
				BufferedReader sysBuff = new BufferedReader(
						new InputStreamReader(System.in));
				printWriter.println(sysBuff.readLine());
				printWriter.flush();

				result = bufferedReader.readLine();
				System.out.println("Server say : " + result);
			}
			printWriter.close();
			bufferedReader.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
