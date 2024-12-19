package tech.wenisch.ipfix.generator.networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerRunnable implements Runnable {
    private int port;

    public UDPServerRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                
                System.out.println("Received: " + received);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
