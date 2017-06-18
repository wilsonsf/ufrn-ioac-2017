package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.List;

public class Block {

	List<Word> words = new ArrayList<Word>();
	int size;

	public Block(int size) {
		this.size = size;
	}

	@Deprecated
	public void initialize(int blockNumber) {
		for (int i = 0; i < size; i++) {
			Word word = new Word(blockNumber + i, Integer.valueOf(blockNumber + i));
			words.add(word);
		}
	}
}