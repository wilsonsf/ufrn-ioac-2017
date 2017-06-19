package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.List;

public class Block {

	private ArrayList<Word> words;
	private int size;
	private Integer address;
	private Block originalBlock = null;

	private Block(int size) {
		this.size = size;
		words = new ArrayList<Word>(size);
	}

	public Block(int size, Integer address){
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

	public Word read(int address) {
		int wordPos = Math.floorMod(address, size);
		return words.get(wordPos);
	}
}