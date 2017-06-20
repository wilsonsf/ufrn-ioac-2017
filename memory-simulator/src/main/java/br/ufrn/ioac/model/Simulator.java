package br.ufrn.ioac.model;

import br.ufrn.ioac.interfaces.MappingPolicyI;
import br.ufrn.ioac.interfaces.SubstitutionPolicyI;
import br.ufrn.ioac.interfaces.WritingPolicyI;
import br.ufrn.ioac.strategy.DirectMappingStrategy;
import br.ufrn.ioac.strategy.DirectMappingSubstitutionPolicy;

@SuppressWarnings("unused")
public class Simulator {

	private Integer blockSize;
	private Integer cacheSize;
	private Integer memorySize;
	private Integer waySize;

	private Memory memory;
	private Cache cache;

	private MappingPolicyI mapping = new DirectMappingStrategy();
	private SubstitutionPolicyI substitution = new DirectMappingSubstitutionPolicy();
	private WritingPolicyI writing;

	@SuppressWarnings("deprecation")
	public Simulator(Integer blockSize, Integer cacheSize, Integer memorySize, MappingPolicyI mapping,
			SubstitutionPolicyI substitution, WritingPolicyI writing) {
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

	public Simulator(Integer blockSize, Integer cacheSize, Integer memorySize, Integer waySize, MappingPolicyI mapping,
			SubstitutionPolicyI substitution, WritingPolicyI writing) {
		this(blockSize, cacheSize, memorySize, mapping, substitution, writing);
		this.waySize = waySize;
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
		if (cache.has(address)) {
			builder.append("HIT ");
			builder.append("linha " + cache.read(address).getBlockAddress());
		} else {
			builder.append("MISS -> ");
			Integer destinyLineAddress = mapping.getPosition(address, blockSize, cacheSize, waySize);
			builder.append("alocado na linha " + destinyLineAddress);

			Block lineToBlock = cache.getLine(destinyLineAddress);
			if ((lineToBlock != null) && !lineToBlock.isEmpty()) {
				builder.append("-> bloco " + lineToBlock.getOriginalBlock().getAddress() + " substituido");
			}
			// must check if it uses writeback now to update values

			Block blockToLine = memory.findBlock(address);

			substitution.replace(blockToLine, lineToBlock, cache, memory);
		}

		return builder.toString();
	}

	public String write(Integer address, Integer newValue) {
		if (cache.has(address)) {
			cache.read(address).updateValue(newValue);
		}

		return "trocou!";
	}
}
