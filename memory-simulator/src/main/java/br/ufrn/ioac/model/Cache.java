package br.ufrn.ioac.model;

public class Cache extends Memory {

	public Cache(int size, int blockSize) {
		super(size, blockSize);
	}

	@Override
	@Deprecated
	public void initialize() {
		for (int blockNumber = 0; blockNumber < getSize(); blockNumber++) {
			Block block = new Block(getBlockSize(), null);
			for(int i = 0; i < getBlockSize(); i++) {
				block.getWords().add(new Word(null, null));
			}
			getBlocks().add(block);
		}
	}

	@Override
	public String show() {
		StringBuilder builder = new StringBuilder();

		builder.append("Linha-Bloco-Endereço-Conteúdo\n");
		for (Block block : getBlocks()) {
			if (!block.isEmpty()) {
				for (Word word : block.getWords()) {
					builder.append(block.getAddress() + "-" + block.getOriginalBlock().getAddress() + "-"
							+ word.getOriginalAddress() + "-" + word.getValue() + "\n");
				}
			}
		}

		return builder.toString();
	}

	@Override
	public boolean has(Integer address) {
		Word word = read(address);
		return ((word == null) || (word.isEmpty())) ? false : address.equals(word.getOriginalAddress());
	}

	public Word read(Integer address) {
		Block block = findLine(address);
		return block == null ? null : block.read(address);
	}

	public Block findLine(Integer wordAddress) {
		int memoryBlockPos = wordAddress / getBlockSize();// Math.floorMod(address, blockSize);
		int linePos = memoryBlockPos % getSize();

		if (getBlocks().size() > linePos)
			return getBlocks().get(linePos);
		else
			return null;
	}

	public Block getLine(Integer lineAddress) {
		return getBlocks().get(lineAddress % getSize());
	}

	public void updateLine(Block newLine, Integer position) {
		getBlocks().set(position, newLine);
	}

}
