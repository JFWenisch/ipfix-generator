package tech.wenisch.ipfix.generator.managers;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

import tech.wenisch.ipfix.generator.datastructures.ipfix.InformationElement;
import tech.wenisch.ipfix.generator.datastructures.ipfix.L2IPDataRecord;
import tech.wenisch.ipfix.generator.datastructures.ipfix.MessageHeader;
import tech.wenisch.ipfix.generator.datastructures.ipfix.OptionTemplateRecord;
import tech.wenisch.ipfix.generator.datastructures.ipfix.SamplingDataRecord;
import tech.wenisch.ipfix.generator.datastructures.ipfix.SetHeader;
import tech.wenisch.ipfix.generator.datastructures.ipfix.TemplateRecord;
import tech.wenisch.ipfix.generator.datastructures.networking.MacAddress;
import tech.wenisch.ipfix.generator.exceptions.UtilityException;

public class IPFIXGeneratorManager {

	static boolean debug;

	static MessageHeader createMessageHeader(long observationDomainID) {
		MessageHeader mh = new MessageHeader();
		mh.setExportTime(new Date());
		mh.setVersionNumber(10);
		mh.setObservationDomainID(observationDomainID);
		return mh;
	}

	static TemplateRecord createDefaultTemplateRecord() {

		TemplateRecord tr = new TemplateRecord();

		tr.setTemplateID(306);
		tr.setFieldCount(26);

		InformationElement iESrcMAC = new InformationElement();
		iESrcMAC.setFieldLength(6);
		iESrcMAC.setInformationElementID(56);
		tr.getInformationElements().add(iESrcMAC);

		InformationElement iEDestMAC = new InformationElement();
		iEDestMAC.setFieldLength(6);
		iEDestMAC.setInformationElementID(80);
		tr.getInformationElements().add(iEDestMAC);

		InformationElement iEingressPhysicalID = new InformationElement();
		iEingressPhysicalID.setFieldLength(4);
		iEingressPhysicalID.setInformationElementID(252);
		tr.getInformationElements().add(iEingressPhysicalID);

		InformationElement iEegressPhysicalID = new InformationElement();
		iEegressPhysicalID.setFieldLength(4);
		iEegressPhysicalID.setInformationElementID(253);
		tr.getInformationElements().add(iEegressPhysicalID);

		InformationElement iEdot1qVlandId = new InformationElement();
		iEdot1qVlandId.setFieldLength(2);
		iEdot1qVlandId.setInformationElementID(243);
		tr.getInformationElements().add(iEdot1qVlandId);

		InformationElement iEdot1qCVlandId = new InformationElement();
		iEdot1qCVlandId.setFieldLength(2);
		iEdot1qCVlandId.setInformationElementID(245);
		tr.getInformationElements().add(iEdot1qCVlandId);

		InformationElement iEPostDot1qVlandId = new InformationElement();
		iEPostDot1qVlandId.setFieldLength(2);
		iEPostDot1qVlandId.setInformationElementID(255);
		tr.getInformationElements().add(iEPostDot1qVlandId);

		InformationElement iEPostDot1qCVlandId = new InformationElement();
		iEPostDot1qCVlandId.setFieldLength(2);
		iEPostDot1qCVlandId.setInformationElementID(254);
		tr.getInformationElements().add(iEPostDot1qCVlandId);

		InformationElement iEIPv4Src = new InformationElement();
		iEIPv4Src.setFieldLength(4);
		iEIPv4Src.setInformationElementID(8);
		tr.getInformationElements().add(iEIPv4Src);

		InformationElement iEIPv4Dest = new InformationElement();
		iEIPv4Dest.setFieldLength(4);
		iEIPv4Dest.setInformationElementID(12);
		tr.getInformationElements().add(iEIPv4Dest);

		InformationElement iEIPv6Src = new InformationElement();
		iEIPv6Src.setFieldLength(16);
		iEIPv6Src.setInformationElementID(27);
		tr.getInformationElements().add(iEIPv6Src);

		InformationElement iEIPv6Dest = new InformationElement();
		iEIPv6Dest.setFieldLength(16);
		iEIPv6Dest.setInformationElementID(28);
		tr.getInformationElements().add(iEIPv6Dest);

		InformationElement iEPkts = new InformationElement();
		iEPkts.setFieldLength(4);
		iEPkts.setInformationElementID(2);
		tr.getInformationElements().add(iEPkts);

		InformationElement iEBytes = new InformationElement();
		iEBytes.setFieldLength(4);
		iEBytes.setInformationElementID(1);
		tr.getInformationElements().add(iEBytes);

		InformationElement iEFlowStart = new InformationElement();
		iEFlowStart.setFieldLength(8);
		iEFlowStart.setInformationElementID(152);
		tr.getInformationElements().add(iEFlowStart);

		InformationElement iEFlowEnd = new InformationElement();
		iEFlowEnd.setFieldLength(8);
		iEFlowEnd.setInformationElementID(153);
		tr.getInformationElements().add(iEFlowEnd);

		InformationElement iEPortSrc = new InformationElement();
		iEPortSrc.setFieldLength(2);
		iEPortSrc.setInformationElementID(7);
		tr.getInformationElements().add(iEPortSrc);

		InformationElement iEPortDest = new InformationElement();
		iEPortDest.setFieldLength(2);
		iEPortDest.setInformationElementID(7);
		tr.getInformationElements().add(iEPortDest);

		InformationElement iETCPFlags = new InformationElement();
		iETCPFlags.setFieldLength(1);
		iETCPFlags.setInformationElementID(6);
		tr.getInformationElements().add(iETCPFlags);

		InformationElement iEProtocol = new InformationElement();
		iEProtocol.setFieldLength(1);
		iEProtocol.setInformationElementID(4);
		tr.getInformationElements().add(iEProtocol);

		InformationElement iEIPv6OptionHeaders = new InformationElement();
		iEIPv6OptionHeaders.setFieldLength(4);
		iEIPv6OptionHeaders.setInformationElementID(64);
		tr.getInformationElements().add(iEIPv6OptionHeaders);

		InformationElement iENextHeaderIPv6 = new InformationElement();
		iENextHeaderIPv6.setFieldLength(1);
		iENextHeaderIPv6.setInformationElementID(193);
		tr.getInformationElements().add(iENextHeaderIPv6);

		InformationElement iEFlowLabel = new InformationElement();
		iEFlowLabel.setFieldLength(4);
		iEFlowLabel.setInformationElementID(31);
		tr.getInformationElements().add(iEFlowLabel);

		InformationElement iEIPTOS = new InformationElement();
		iEIPTOS.setFieldLength(1);
		iEIPTOS.setInformationElementID(5);
		tr.getInformationElements().add(iEIPTOS);

		InformationElement iEIPVersion = new InformationElement();
		iEIPVersion.setFieldLength(1);
		iEIPVersion.setInformationElementID(60);
		tr.getInformationElements().add(iEIPVersion);

		InformationElement iEICMPType = new InformationElement();
		iEICMPType.setFieldLength(2);
		iEICMPType.setInformationElementID(32);
		tr.getInformationElements().add(iEICMPType);
		return tr;
	}

	static OptionTemplateRecord createDefaultOptionTemplate() {
		OptionTemplateRecord otr = new OptionTemplateRecord();

		otr.setTemplateID(256);
		otr.setScopeFieldCount(1);
		otr.setFieldCount(4);

		InformationElement iEObservationDomainID = new InformationElement();
		iEObservationDomainID.setFieldLength(4);
		iEObservationDomainID.setInformationElementID(149);
		otr.getInformationElements().add(iEObservationDomainID);

		InformationElement iESelectorAlgorithm = new InformationElement();
		iESelectorAlgorithm.setFieldLength(2);
		iESelectorAlgorithm.setInformationElementID(304);
		otr.getInformationElements().add(iESelectorAlgorithm);

		InformationElement iESamplingPacketInterval = new InformationElement();
		iESamplingPacketInterval.setFieldLength(4);
		iESamplingPacketInterval.setInformationElementID(305);
		otr.getInformationElements().add(iESamplingPacketInterval);

		InformationElement iESamplingPacketSpace = new InformationElement();
		iESamplingPacketSpace.setFieldLength(4);
		iESamplingPacketSpace.setInformationElementID(306);
		otr.getInformationElements().add(iESamplingPacketSpace);
		return otr;
	}

	static L2IPDataRecord createDataRecord(long ingressPhysicalInterfaceIDValue, long egressPhysicalInterfaceIDValue, int outerVLANValue, int innerVLANValue, MacAddress srcMACValue, MacAddress destMACValue, short ipVersionValue, short transportProtocolValue, Inet4Address srcIPv4Value, Inet4Address destIPv4Value, Inet6Address srcIPv6Value, Inet6Address destIPv6Value, int srcPortValue, int destPortValue, int icmpTypeValue, long packetsValue, long octetsValue) throws UnknownHostException {
		L2IPDataRecord l2ip = new L2IPDataRecord();


		l2ip.setIngressPhysicalInterface(ingressPhysicalInterfaceIDValue);
		l2ip.setEgressPhysicalInterface(egressPhysicalInterfaceIDValue);
		l2ip.setDot1qVlanId(outerVLANValue);
		l2ip.setDot1qCustomerVlanId(innerVLANValue);
		l2ip.setSourceMacAddress(srcMACValue);
		l2ip.setDestinationMacAddress(destMACValue);
		l2ip.setIpVersion(ipVersionValue);
		l2ip.setProtocolIdentifier(transportProtocolValue);
		if (ipVersionValue == 4) {
			l2ip.setSourceIPv4Address(srcIPv4Value);
			l2ip.setDestinationIPv4Address(destIPv4Value);
		}
		if (ipVersionValue == 6) {
			l2ip.setSourceIPv6Address(srcIPv6Value);
			l2ip.setDestinationIPv6Address(destIPv6Value);
		}
		l2ip.setSourceTransportPort(srcPortValue);
		l2ip.setDestinationTransportPort(destPortValue);
		l2ip.setIcmpTypeCodeIPv4(icmpTypeValue);
		l2ip.setPacketDeltaCount(packetsValue);
		l2ip.setOctetDeltaCount(octetsValue);

		return l2ip;
	}
	static L2IPDataRecord createRandomDataRecord() throws UnknownHostException, UtilityException {
		L2IPDataRecord l2ip = new L2IPDataRecord();

		Random random = new Random();
		l2ip.setIngressPhysicalInterface( random.nextInt());
		l2ip.setEgressPhysicalInterface(random.nextInt());
		l2ip.setDot1qVlanId( random.nextInt(4096));
		l2ip.setDot1qCustomerVlanId( random.nextInt(4096));
		byte[] mac = new byte[6];
		random.nextBytes(mac);
		l2ip.setSourceMacAddress( new MacAddress(mac));
		random.nextBytes(mac);
		l2ip.setDestinationMacAddress(new MacAddress(mac));
		l2ip.setIpVersion(Short.parseShort("4"));
		l2ip.setProtocolIdentifier((short) (random.nextBoolean() ? 6 : 17));
		byte[] addrIPv4 = new byte[4];
		random.nextBytes(addrIPv4);
		l2ip.setSourceIPv4Address((Inet4Address) Inet4Address.getByAddress(addrIPv4));
		random.nextBytes(addrIPv4);
		l2ip.setDestinationIPv4Address((Inet4Address) Inet4Address.getByAddress(addrIPv4));


		l2ip.setSourceTransportPort(1234);
		l2ip.setDestinationTransportPort(2601);
		l2ip.setIcmpTypeCodeIPv4(1);
		l2ip.setPacketDeltaCount(2);
		l2ip.setOctetDeltaCount(3);

		return l2ip;
	}
	public static void main(String args[]) {
		
		String ipfixCollectorHost = "127.0.0.1";
		int ipfixCollectorPort = 7865;
		
		try (DatagramSocket socket = new DatagramSocket())
		{
		MessageHeader mh = createRandomL2IPIPfixMessage();
		L2IPDataRecord l2ip = (L2IPDataRecord) mh.getSetHeaders().get(mh.getSetHeaders().size()-1).getDataRecords().get(0);
		long seqNumber = 0;
		while (true) {
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
			if (debug)
				System.out.println("Sending: " + mh);
			socket.send(dp);


			Thread.sleep(10000);

		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static MessageHeader createRandomL2IPIPfixMessage() throws UnknownHostException, UtilityException {

		// create IPFIX data
		//
		// Message header
		MessageHeader mh = createMessageHeader(1337);

		// Set header for the template
		SetHeader shTemplate = new SetHeader();
		mh.getSetHeaders().add(shTemplate);
		shTemplate.setSetID(2);

		TemplateRecord tr = createDefaultTemplateRecord();
		shTemplate.getTemplateRecords().add(tr);

		// Set header for the template options
		SetHeader shTemplateOptions = new SetHeader();
		mh.getSetHeaders().add(shTemplateOptions);
		shTemplateOptions.setSetID(3);

		OptionTemplateRecord otr = createDefaultOptionTemplate();
		shTemplateOptions.getOptionTemplateRecords().add(otr);

		// Set header for the sampling
		SetHeader shSampling = new SetHeader();
		shSampling.setSetID(256);
		mh.getSetHeaders().add(shSampling);

		SamplingDataRecord sdr = new SamplingDataRecord();
		shSampling.getDataRecords().add(sdr);
		sdr.setObservationDomainId(67108864);
		sdr.setSelectorAlgorithm(1);
		sdr.setSamplingPacketInterval(1);
		sdr.setSamplingPacketSpace(1 - 1);

		// Set header for the L2IP
		SetHeader shDataRecord = new SetHeader();
		shDataRecord.setSetID(306);
		mh.getSetHeaders().add(shDataRecord);

		L2IPDataRecord l2ip = createRandomDataRecord();
		shDataRecord.getDataRecords().add(l2ip);

	return mh;
	}

}