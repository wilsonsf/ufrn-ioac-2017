package br.ufrn.ioac.model;

import br.ufrn.ioac.interfaces.MappingPolicyI;
import br.ufrn.ioac.interfaces.SubstitutionPolicyI;
import br.ufrn.ioac.interfaces.WritingPolicyI;

public class SimulatorBuilder {

	private Integer blockSize = null;
	private Integer cacheSize = null;
	private Integer memorySize = null;

	//	private Memory memory = null;
	//	private Cache cache = null;

	private MappingPolicyI mapping = null;
	private SubstitutionPolicyI substitution = null;
	private WritingPolicyI writing = null;

	public SimulatorBuilder withBlockSize(int size) {
		blockSize = size;
		return this;
	}

	public SimulatorBuilder withCacheSize(int size) {
		cacheSize = size;
		return this;
	}

	public SimulatorBuilder withMemorySize(int size) {
		memorySize = size;
		return this;
	}

	//	public SimulatorBuilder withMemory(Memory memory) {
	//		this.memory = memory;
	//		return this;
	//	}

	//	public SimulatorBuilder withCache(Cache cache) {
	//		this.cache = cache;
	//		return this;
	//	}

	public SimulatorBuilder withMappingPolicy(MappingPolicyI policy) {
		mapping = policy;
		return this;
	}

	public SimulatorBuilder withSubstitutionPolicy(SubstitutionPolicyI policy) {
		substitution = policy;
		return this;
	}

	public SimulatorBuilder withWritingPolicy(WritingPolicyI policy) {
		writing = policy;
		return this;
	}

	public Simulator build() {
		return new Simulator(blockSize, cacheSize, memorySize, mapping, substitution, writing);
	}

}
