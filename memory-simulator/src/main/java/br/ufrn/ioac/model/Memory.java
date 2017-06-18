package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	List<Block> blocks;
	private int memorySize;
	private int blockSize;

	public Memory(int memorySize, int blockSize) {
		this.memorySize = memorySize;
		this.blockSize = blockSize;
		blocks = new ArrayList<>();
	}

	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < memorySize; blockNumber++) {
			Block block = new Block(blockSize);
			block.initialize(blockNumber);
			blocks.add(block);
		}
	}
}
