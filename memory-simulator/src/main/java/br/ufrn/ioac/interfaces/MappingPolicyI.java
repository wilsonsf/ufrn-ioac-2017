package br.ufrn.ioac.interfaces;

public interface MappingPolicyI {
	public Integer getPosition(Integer address, Integer blockSize, Integer cacheSize, Integer waySize);

}
