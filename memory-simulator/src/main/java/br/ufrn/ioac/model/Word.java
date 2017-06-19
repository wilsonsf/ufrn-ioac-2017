package br.ufrn.ioac.model;

public class Word {
	int originalAddress;
	Integer value;

	public Word(int address){
		originalAddress = address;
	}

	public Word(int address, Integer value) {
		this(address);
		this.value = value;
	}

	public int getOriginalAddress() {
		return originalAddress;
	}

	public int getValue(){
		return value.intValue();
	}
}
