package br.ufrn.ioac.model;

import br.ufrn.ioac.interfaces.MappingPolicyI;
import br.ufrn.ioac.interfaces.SubstitutionPolicyI;
import br.ufrn.ioac.interfaces.WritingPolicyI;

@SuppressWarnings("unused")
public class Simulator {

	private Integer blockSize;
	private Integer cacheSize;
	private Integer memorySize;

	private Memory memory;
	private Cache cache;

	private MappingPolicyI mapping;
	private SubstitutionPolicyI substitution;
	private WritingPolicyI writing;

	@SuppressWarnings("deprecation")
	public Simulator(Integer blockSize, Integer cacheSize, Integer memorySize,
			MappingPolicyI mapping, SubstitutionPolicyI substitution, WritingPolicyI writing) {
		this.blockSize = blockSize;
		cache = new Cache(cacheSize, blockSize);
		this.cacheSize = cacheSize;
		this.memorySize = memorySize;
		memory = new Memory(memorySize, blockSize);
		this.mapping = mapping;
		this.substitution = substitution;
		this.writing = writing;

		memory.initialize();
		cache.initialize();
	}

	public String show() {
		StringBuilder builder = new StringBuilder();
		builder.append("CACHE L1\n");
		builder.append(cache.show());
		builder.append("MEMÓRIA PRINCIPAL\n");
		builder.append(memory.show());

		return builder.toString();
	}

	public String read(Integer address) {
		StringBuilder builder = new StringBuilder();
		builder.append("Read " + address + " -> ");
		if (cache.has(address)){
			builder.append("HIT ");
			builder.append("linha" + cache.read(address));
		} else {
			builder.append("MISS ");
		}

		return builder.toString();
	}
}
