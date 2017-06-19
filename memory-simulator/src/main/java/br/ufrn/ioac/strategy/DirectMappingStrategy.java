package br.ufrn.ioac.strategy;

import br.ufrn.ioac.interfaces.MappingPolicyI;
import br.ufrn.ioac.model.Word;

public class DirectMappingStrategy implements MappingPolicyI {

	@Override
	public int getPosition(int blockSize, Word word, int waySize) {
		return 0;
	}

}
