package br.ufrn.ioac.model;

public class Word {
	Integer originalAddress;
	Integer value;
	Block block;

	public Word(Integer address){
		originalAddress = address;
	}

	public Word(Integer address, Integer value) {
		this(address);
		this.value = value;
	}
	
	public Word(Block block, Integer address, Integer value) {
		this(address, value);
		this.block = block;
	}

	public Integer getOriginalAddress() {
		return originalAddress;
	}

	public Integer getValue(){
		return value.intValue();
	}
	
	public void updateBlock(Block newBlock) {
		block = newBlock;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public boolean isEmpty() {
		return originalAddress == null;
	}
}
