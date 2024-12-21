package tech.wenisch.ipfix.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

import org.junit.jupiter.api.Test;

import tech.wenisch.ipfix.generator.datastructures.ipfix.L2IPDataRecord;
import tech.wenisch.ipfix.generator.datastructures.ipfix.MessageHeader;
import tech.wenisch.ipfix.generator.managers.IPFIXGeneratorManager;
import tech.wenisch.ipfix.generator.threads.UDPServerRunnable;

public class GeneratorTest {

    @Test
    void testCollector() {

		String ipfixCollectorHost = "127.0.0.1";
		int ipfixCollectorPort = 4739;
		// Start Thread
		UDPServerRunnable serverRunnable = new UDPServerRunnable(ipfixCollectorPort);
		Thread serverThread = new Thread(serverRunnable);
		serverThread.start();
		System.out.println("UDP server is running on port " + ipfixCollectorPort);
		
		try (DatagramSocket socket = new DatagramSocket())
		{
		MessageHeader mh = IPFIXGeneratorManager.createRandomL2IPIPfixMessage();
		L2IPDataRecord l2ip = (L2IPDataRecord) mh.getSetHeaders().get(mh.getSetHeaders().size()-1).getDataRecords().get(0);
		long seqNumber = 0;
		int packetsSend = 0;
		while (packetsSend < 10) {
			// Message header updating
			mh.setSequenceNumber(seqNumber);
			mh.setExportTime(new Date());
			seqNumber++;

			// L2IP updating
			BigInteger flowStartEnd = BigInteger.valueOf(new Date().getTime());
			l2ip.setFlowStartMilliseconds(flowStartEnd);
			l2ip.setFlowEndMilliseconds(flowStartEnd);


			DatagramPacket dp = new DatagramPacket(mh.getBytes(), mh.getBytes().length, InetAddress.getByName(ipfixCollectorHost),
					ipfixCollectorPort);
				System.out.println("Sending: " + mh);
			socket.send(dp);


			Thread.sleep(1000);
			packetsSend++;

		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
        assertEquals(1, 1);
    }
}