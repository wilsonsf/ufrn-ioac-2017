package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.List;

public class Cache {

	List<Block> blocks;
	private int size;
	private int blockSize;

	public Cache(int size, int blockSize) {
		this.size = size;
		this.blockSize = blockSize;
		blocks = new ArrayList<>();
	}

	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < size; blockNumber++) {
			Block block = new Block(blockSize);
			blocks.add(block);
		}
	}

	public String show() {
		initialize();
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
}
