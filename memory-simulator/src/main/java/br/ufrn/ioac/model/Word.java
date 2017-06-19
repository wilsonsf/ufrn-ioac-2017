package br.ufrn.ioac.model;

public class Word {
	Integer originalAddress;
	Integer value;

	public Word(Integer address){
		originalAddress = address;
	}

	public Word(Integer address, Integer value) {
		this(address);
		this.value = value;
	}

	public Integer getOriginalAddress() {
		return originalAddress;
	}

	public Integer getValue(){
		return value.intValue();
	}
}
