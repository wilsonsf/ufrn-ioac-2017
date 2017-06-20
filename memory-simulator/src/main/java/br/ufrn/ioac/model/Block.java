package br.ufrn.ioac.model;

import java.util.ArrayList;

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

	public Block(Block other) {
		words = copyWords(other);
		address = other.getAddress();
		size = other.getSize();
		originalBlock = other;
	}

	public Block(Block other, Integer address) {
		this(other);
		this.address = address;
	}
	private ArrayList<Word> copyWords(Block other) {
		ArrayList<Word> copy = new ArrayList<Word>();
		for (Word word : other.getWords()) {
			copy.add(word);
		}
		return copy;
	}

	@Deprecated
	public void initialize(int blockNumber) {
		for (int i = 0; i < size; i++) {
			Word word = new Word((blockNumber*size) + i, Integer.valueOf(0));
			word.updateBlock(this);
			words.add(word);
		}
	}

	public ArrayList<Word> getWords(){
		return words;
	}

	public int getSize() {
		return size;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public void setOriginalBlock(Block block) {
		originalBlock = block;
	}

	public Block getOriginalBlock() {
		return originalBlock;
	}

	public void callBackUpdate() {
		updateWords();
	}

	private void updateWords() {
		for (int it = 0; it < words.size(); it++) {
			originalBlock.getWords().set(it, words.get(it));
		}
	}

	public Word read(int address) {
		int wordPos = Math.floorMod(address, size);
		return words.get(wordPos);
	}

	public boolean isEmpty() {
		return (address == null) || (words == null) || words.isEmpty();
	}
}