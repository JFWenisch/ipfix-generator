package tech.wenisch.ipfix.generator.datastructures;

import tech.wenisch.ipfix.generator.util.HeaderBytesException;

public interface IPFIXEntity {
	public String toString();
	public byte[] getBytes() throws HeaderBytesException;
}
