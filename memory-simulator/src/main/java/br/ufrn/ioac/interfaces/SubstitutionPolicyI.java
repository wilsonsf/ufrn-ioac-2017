package br.ufrn.ioac.interfaces;

import br.ufrn.ioac.model.Block;
import br.ufrn.ioac.model.Cache;
import br.ufrn.ioac.model.Memory;

public interface SubstitutionPolicyI {
	public void replace(Block newBlock, Block oldLine, Cache cache, Memory memory);
}
