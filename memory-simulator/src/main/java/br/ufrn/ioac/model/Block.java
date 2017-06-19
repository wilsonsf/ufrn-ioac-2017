package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.List;

public class Block {

	private List<Word> words = new ArrayList<Word>();
	private int size;
	private int address;
	private Block originalBlock = null;

	private Block(int size) {
		this.size = size;
	}

	public Block(int size, int address){
		this(size);
		this.address = address;
	}

	@Deprecated
	public void initialize(int blockNumber) {
		for (int i = 0; i < size; i++) {
			Word word = new Word((blockNumber*size) + i, Integer.valueOf(0));
			words.add(word);
		}
	}

	public List<Word> getWords(){
		return words;
	}

	public int getSize() {
		return size;
	}

	public int getAddress() {
		return address;
	}

	public void setOriginalBlock(Block block) {
		originalBlock = block;
	}

	public Block getOriginalBlock() {
		return originalBlock;
	}

	public void callBackUpdate(List<Word> words) {
		originalBlock.callBackUpdate(this.words);
	}
}