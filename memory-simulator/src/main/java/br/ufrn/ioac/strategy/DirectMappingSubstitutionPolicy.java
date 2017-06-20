package br.ufrn.ioac.strategy;

import br.ufrn.ioac.interfaces.SubstitutionPolicyI;
import br.ufrn.ioac.model.Block;
import br.ufrn.ioac.model.Cache;
import br.ufrn.ioac.model.Memory;

public class DirectMappingSubstitutionPolicy implements SubstitutionPolicyI {

	@Override
	public void replace(Block newBlock, Block oldLine, Cache cache, Memory memory) {

		if ((oldLine != null) && !oldLine.isEmpty()) {
			oldLine.callBackUpdate();
		}

		int newLineAddress = newBlock.getAddress() % cache.getSize();
		Block newLine = new Block(newBlock, newLineAddress);

		cache.updateLine(newLine, newLineAddress);
	}

}
