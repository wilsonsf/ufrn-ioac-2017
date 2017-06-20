package br.ufrn.ioac.strategy;

import br.ufrn.ioac.interfaces.MappingPolicyI;

public class DirectMappingStrategy implements MappingPolicyI {

	@Override
	public Integer getPosition(Integer address, Integer blockSize, Integer cacheSize, Integer waySize) {
		return (address / blockSize) % cacheSize;
	}

}
