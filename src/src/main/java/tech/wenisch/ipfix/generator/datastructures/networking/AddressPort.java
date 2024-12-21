package tech.wenisch.ipfix.generator.datastructures.networking;
public class AddressPort {
	private Address address;
	private int port;
	
	public AddressPort(Address address, int port) {
		this.address = address;
		this.port = port;
	}

	public Address getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}
}
