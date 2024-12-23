package tech.wenisch.ipfix.generator.datastructures;

public class IPFIXGeneratorJobHistoryEntry {
	String date;
	String action;
	String sourceIP;
	int sourcePort;
	String destIP;
	int destPort;
	String details;
	int packetsSend;
	public int getPacketsSend() {
		return packetsSend;
	}
	public void setPacketsSend(int packetsSend) {
		this.packetsSend = packetsSend;
	}
	public IPFIXGeneratorJobHistoryEntry(int packetsSend, String date, String action, String sourceIP, int sourcePort, String destIP, int destPort, String details)
	{
		this.packetsSend=packetsSend;
		this.date=date;
		this.action=action;
		this.sourceIP=sourceIP;
		this.sourcePort=sourcePort;
		this.destIP=destIP;
		this.destPort=destPort;
		this.details=details;

	}
	public String getSourceIP() {
		return sourceIP;
	}
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}
	public int getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getDestIP() {
		return destIP;
	}
	public void setDestIP(String destIP) {
		this.destIP = destIP;
	}
	public int getDestPort() {
		return destPort;
	}
	public void setDestPort(int destPort) {
		this.destPort = destPort;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

}
