package tech.wenisch.ipfix.generator.threads;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

import tech.wenisch.ipfix.generator.IPFIXGenerator;
import tech.wenisch.ipfix.generator.datastructures.L2IPDataRecord;
import tech.wenisch.ipfix.generator.datastructures.MessageHeader;
import tech.wenisch.ipfix.generator.datastructures.pojo.IPFIXGeneratorJobRequest;

public class IPFIXGeneratorJob implements Runnable {
	String destHost;
	int destPort;
	int id;
	public String getDestHost() {
		return destHost;
	}

	public void setDestHost(String destHost) {
		this.destHost = destHost;
	}

	public int getDestPort() {
		return destPort;
	}

	public void setDestPort(int destPort) {
		this.destPort = destPort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPps() {
		return pps;
	}

	public void setPps(long pps) {
		this.pps = pps;
	}

	public int getTotalPackets() {
		return totalPackets;
	}

	public void setTotalPackets(int totalPackets) {
		this.totalPackets = totalPackets;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	long pps;
	int totalPackets;
	private volatile boolean active = true;
	static int jobIDCounter = 1;


    public IPFIXGeneratorJob(IPFIXGeneratorJobRequest request) {
        this.id=jobIDCounter;
        jobIDCounter++;
        this.destHost=request.getDestHost();
        this.destPort=Integer.valueOf(request.getDestPort());
        this.pps=Long.parseLong(request.getPps());
        this.totalPackets=Integer.valueOf(request.getTotalPackets());
	}

	@Override
    public void run() {

		try (DatagramSocket socket = new DatagramSocket())
		{
		MessageHeader mh = IPFIXGenerator.createRandomL2IPIPfixMessage();
		L2IPDataRecord l2ip = (L2IPDataRecord) mh.getSetHeaders().get(mh.getSetHeaders().size()-1).getDataRecords().get(0);
		long seqNumber = 0;
		int packetsSend = 0;
		while (packetsSend < totalPackets && active) {
			// Message header updating
			mh.setSequenceNumber(seqNumber);
			mh.setExportTime(new Date());
			seqNumber++;

			// L2IP updating
			BigInteger flowStartEnd = BigInteger.valueOf(new Date().getTime());
			l2ip.setFlowStartMilliseconds(flowStartEnd);
			l2ip.setFlowEndMilliseconds(flowStartEnd);


			DatagramPacket dp = new DatagramPacket(mh.getBytes(), mh.getBytes().length, InetAddress.getByName(destHost),
					destPort);
				System.out.println("Sending: " + mh);
			socket.send(dp);


			Thread.sleep(1000/pps);
			packetsSend++;

		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
    }
    public void stop() { active = false; }
}