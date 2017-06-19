package br.ufrn.ioac.model;

public class Cache extends Memory {

	public Cache(int size, int blockSize) {
		super(size, blockSize);
	}

	@Override
	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < size; blockNumber++) {
			Block block = new Block(blockSize, blockNumber);
			blocks.add(block);
		}
	}

	@Override
	public String show() {
		StringBuilder builder = new StringBuilder();

		builder.append("Linha-Bloco-Endereço-Conteúdo\n");
		for (Block block : blocks) {
			for (Word word : block.getWords()) {
				builder.append(block.getAddress() + "-" +
						block.getOriginalBlock().getAddress() + "-" +
						word.getOriginalAddress() + "-" + word.getValue() + "\n");
			}
		}

		return builder.toString();
	}
}
