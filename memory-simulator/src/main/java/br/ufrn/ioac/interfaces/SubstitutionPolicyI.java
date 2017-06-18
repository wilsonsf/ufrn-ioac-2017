package br.ufrn.ioac.interfaces;

import java.util.Collection;

import br.ufrn.ioac.model.Block;

public interface SubstitutionPolicyI {
	public void replace(Block block, Collection<Block> destiny);
}
