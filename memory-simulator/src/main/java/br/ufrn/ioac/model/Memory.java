package br.ufrn.ioac.model;

import java.util.ArrayList;

public class Memory {

	protected ArrayList<Block> blocks = new ArrayList<>();
	protected int blockSize;
	protected int size;

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
	
	public boolean has(Integer address) {
		Word word = read(address);
		return ((word == null) || (word.isEmpty())) ? false : address.equals(word.getOriginalAddress());
	}

	public Word read(Integer address) {
		int memoryBlockPos = Math.floorMod(address, blockSize);

		Block block = blocks.get(memoryBlockPos);
		return block.read(address);
	}
}
