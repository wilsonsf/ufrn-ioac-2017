package br.ufrn.ioac.interfaces;

import br.ufrn.ioac.model.Word;

public interface MappingPolicyI {
	public int getPosition(int blockSize, Word word, int waySize);
}
