package tech.wenisch.ipfix.generator.datastructures.ipfix;

import tech.wenisch.ipfix.generator.exceptions.HeaderBytesException;

public interface IPFIXEntity {
	public String toString();
	public byte[] getBytes() throws HeaderBytesException;
}
