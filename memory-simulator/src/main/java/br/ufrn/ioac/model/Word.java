package br.ufrn.ioac.model;

public class Word {
	int originalAddress;
	Object value = new Object();
	
	public Word(int address){
		originalAddress = address;
	}
	
	public Word(int address, Object value) {
		this(address);
		this.value = value;
	}

	public int getOriginalAddress() {
		return originalAddress;
	}
}
