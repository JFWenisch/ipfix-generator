package tech.wenisch.ipfix.generator.datastructures.networking;
import java.util.StringTokenizer;

import tech.wenisch.ipfix.generator.exceptions.UtilityException;
import tech.wenisch.ipfix.generator.managers.UtilityManager;

public class MacAddress {
	private int firstOctet;
	private int secondOctet;
	private int thirdOctet;
	private int fourthOctet;
	private int fifthOctet;
	private int sixthOctet;
	
	public MacAddress() {
	}
	
	public MacAddress(String address) throws UtilityException {
		StringTokenizer st = new StringTokenizer(address, ":");
		if (st.countTokens() != 6) {
			throw new UtilityException("6 octets in a MAC address string are required.");
		}
		int i = 0;
		while (st.hasMoreTokens()) {
			int temp = Integer.parseInt(st.nextToken(), 16);
			if ((temp < 0) || (temp > 255)) {
				throw new UtilityException("Address is in incorrect format.");
			}
			switch (i) {
			case 0: firstOctet = temp; ++i; break;
			case 1: secondOctet = temp; ++i; break;
			case 2: thirdOctet = temp; ++i; break;
			case 3: fourthOctet = temp; ++i; break;
			case 4: fifthOctet = temp; ++i; break;
			case 5: sixthOctet = temp; ++i; break;
			}
		}
	}
	
	public MacAddress(byte[] address) throws UtilityException {
		if (address.length < 6) {
			throw new UtilityException("6 bytes are required.");
		}
		firstOctet = UtilityManager.oneByteToInteger(address[0]);
		secondOctet = UtilityManager.oneByteToInteger(address[1]);
		thirdOctet = UtilityManager.oneByteToInteger(address[2]);
		fourthOctet = UtilityManager.oneByteToInteger(address[3]);
		fifthOctet = UtilityManager.oneByteToInteger(address[4]);
		sixthOctet = UtilityManager.oneByteToInteger(address[5]);
	}
	
	public String toString() {
		return UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(firstOctet)) + ":" + UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(secondOctet)) + ":" + UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(thirdOctet)) + ":" + UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(fourthOctet)) + ":" + UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(fifthOctet)) + ":" + UtilityManager.prependZeroIfNeededForMacAddress(Integer.toHexString(sixthOctet));
	}
	
	public byte[] getBytes() throws UtilityException {
		byte[] result = new byte[6];
		result[0] = UtilityManager.integerToOneByte(firstOctet);
		result[1] = UtilityManager.integerToOneByte(secondOctet);
		result[2] = UtilityManager.integerToOneByte(thirdOctet);
		result[3] = UtilityManager.integerToOneByte(fourthOctet);
		result[4] = UtilityManager.integerToOneByte(fifthOctet);
		result[5] = UtilityManager.integerToOneByte(sixthOctet);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MacAddress other = (MacAddress) obj;
		if (firstOctet != other.firstOctet) return false;
		if (secondOctet != other.secondOctet) return false;
		if (thirdOctet != other.thirdOctet) return false;
		if (fourthOctet != other.fourthOctet) return false;
		if (fifthOctet != other.fifthOctet) return false;		
		if (sixthOctet != other.sixthOctet) return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		return (firstOctet << 40) + (secondOctet << 32) + (thirdOctet << 24) + (fourthOctet << 16) + (fifthOctet << 8) + (sixthOctet); 
	}
}
