package br.ufrn.ioac.model;

import java.util.LinkedList;
import java.util.List;

public class Pipeline {

	private List<Instruction> instructions = new LinkedList<Instruction>();
	private Instruction instruction;

	public void add(Instruction instruction) {
		instructions.add(instruction);
	}

	public String logState() {
		return "";
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public Integer countInstructions() {
		return instructions.size();
	}

	public Instruction getCurrentInstruction() {
		return instructions.isEmpty() ? null : instructions.get(0);
	}

}
