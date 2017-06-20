package br.ufrn.ioac.model;

import java.util.ArrayList;

public class Memory {

	private ArrayList<Block> blocks = new ArrayList<>();
	private int blockSize;
	private int size;

	public Memory(int size, int blockSize) {
		this.size = size;
		this.blockSize = blockSize;
	}

	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < size; blockNumber++) {
			Block block = new Block(blockSize, blockNumber);
			block.initialize(blockNumber);
			blocks.add(block);
		}
	}

	public String show() {
		StringBuilder builder = new StringBuilder();

		builder.append("Bloco-Endereço-Conteúdo\n");
		for (Block block : blocks) {
			for (Word word : block.getWords()) {
				builder.append(block.getAddress() + "-"
						+ word.getOriginalAddress() + "-" + word.getValue() + "\n");
			}
		}

		return builder.toString();
	}

	public boolean has(Integer wordAaddress) {
		return (wordAaddress < (blockSize*size)) ? true : false;
	}

	public Block findBlock(Integer wordAddress) {
		return blocks.get(wordAddress / blockSize);
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getSize() {
		return size;
	}
}
