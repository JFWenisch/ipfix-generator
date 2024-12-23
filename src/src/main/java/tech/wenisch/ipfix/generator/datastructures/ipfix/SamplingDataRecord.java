package tech.wenisch.ipfix.generator.datastructures.ipfix;

import tech.wenisch.ipfix.generator.exceptions.HeaderBytesException;
import tech.wenisch.ipfix.generator.exceptions.HeaderParseException;
import tech.wenisch.ipfix.generator.managers.UtilityManager;

public class SamplingDataRecord extends DataRecord {
	public static final int LENGTH = 16; // 2 bytes padding
	
	private long observationDomainId;
	private int selectorAlgorithm;
	private long samplingPacketInterval;
	private long samplingPacketSpace;
	
	public long getObservationDomainId() {
		return observationDomainId;
	}
	
	public void setObservationDomainId(long observationDomainId) {
		this.observationDomainId = observationDomainId;
	}
	
	public int getSelectorAlgorithm() {
		return selectorAlgorithm;
	}
	
	public void setSelectorAlgorithm(int selectorAlgorithm) {
		this.selectorAlgorithm = selectorAlgorithm;
	}
	
	public long getSamplingPacketInterval() {
		return samplingPacketInterval;
	}
	
	public void setSamplingPacketInterval(long samplingPacketInterval) {
		this.samplingPacketInterval = samplingPacketInterval;
	}
	
	public long getSamplingPacketSpace() {
		return samplingPacketSpace;
	}
	
	public void setSamplingPacketSpace(long samplingPacketSpace) {
		this.samplingPacketSpace = samplingPacketSpace;
	}
	
	@Override
	public int getLength(){
		return LENGTH;
	}
	
	public static SamplingDataRecord parse(byte[] data) throws HeaderParseException {
		try {
			if (data.length < LENGTH) throw new HeaderParseException("Data array too short.");
			SamplingDataRecord sdr = new SamplingDataRecord();
			// observationDomainId
			byte[] observationDomainId = new byte[4];
			System.arraycopy(data, 0, observationDomainId, 0, 4);
			sdr.setObservationDomainId(UtilityManager.fourBytesToLong(observationDomainId));
			// selectorAlgorithm
			byte[] selectorAlgorithm = new byte[2];
			System.arraycopy(data, 4, selectorAlgorithm, 0, 2);
			sdr.setSelectorAlgorithm(UtilityManager.twoBytesToInteger(selectorAlgorithm));
			// samplingPacketInterval
			byte[] samplingPacketInterval = new byte[4];
			System.arraycopy(data, 6, samplingPacketInterval, 0, 4);
			sdr.setSamplingPacketInterval(UtilityManager.fourBytesToLong(samplingPacketInterval));
			// samplingPacketSpace
			byte[] samplingPacketSpace = new byte[4];
			System.arraycopy(data, 10, samplingPacketSpace, 0, 4);
			sdr.setSamplingPacketSpace(UtilityManager.fourBytesToLong(samplingPacketSpace));
			return sdr;
		} catch (Exception e) {
			throw new HeaderParseException("Parse error: " + e.getMessage());
		}
	}
	
	public byte[] getBytes() throws HeaderBytesException {
		try {
			byte[] data = new byte[LENGTH];
			// observationDomainId
			System.arraycopy(UtilityManager.longToFourBytes(getObservationDomainId()), 0, data, 0, 4);
			// selectorAlgorithm
			System.arraycopy(UtilityManager.integerToTwoBytes(getSelectorAlgorithm()), 0, data, 4, 2);
			// samplingPacketInterval
			System.arraycopy(UtilityManager.longToFourBytes(getSamplingPacketInterval()), 0, data, 6, 4);
			// samplingPacketSpace
			System.arraycopy(UtilityManager.longToFourBytes(getSamplingPacketSpace()), 0, data, 10, 4);
			return data;
		} catch (Exception e) {
			throw new HeaderBytesException("Error while generating the bytes: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[SamplingDataRecord]: ");
		sb.append(" Observation domain ID: ");
		sb.append(observationDomainId);
		sb.append(", Selector algorithm: ");
		sb.append(selectorAlgorithm);
		sb.append(", Sampling packet interval: ");
		sb.append(samplingPacketInterval);
		sb.append(", Sampling packet space: ");
		sb.append(samplingPacketSpace);
		
		return sb.toString();
	}
}
