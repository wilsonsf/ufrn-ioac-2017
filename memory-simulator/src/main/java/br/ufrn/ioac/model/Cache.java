package br.ufrn.ioac.model;

public class Cache extends Memory {

	public Cache(int size, int blockSize) {
		super(size, blockSize);
	}

	@Override
	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < size; blockNumber++) {
			Block block = new Block(blockSize, null);
			for(int i = 0; i < blockSize; i++) {
				block.getWords().add(new Word(null, null));
			}
			blocks.add(block);
		}
	}

	@Override
	public String show() {
		StringBuilder builder = new StringBuilder();

		builder.append("Linha-Bloco-Endereço-Conteúdo\n");
		for (Block block : blocks) {
			for (Word word : block.getWords()) {
				builder.append(block.getAddress() + "-" + block.getOriginalBlock().getAddress() + "-"
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
		int linePos = Math.floorMod(memoryBlockPos, size);

		Block block = blocks.get(linePos);
		return block.read(address);
	}
}
