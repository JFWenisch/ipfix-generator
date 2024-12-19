package tech.wenisch.ipfix.generator;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GeneratorManager {

	public static void sendIPFixPacket(String destHost, String destPort) {
		try {
			DatagramSocket socket = new DatagramSocket();
			String message = "Hello, World!";
			byte[] buffer = message.getBytes();
			InetAddress address = InetAddress.getByName(destHost);
			int port = 12345;
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
