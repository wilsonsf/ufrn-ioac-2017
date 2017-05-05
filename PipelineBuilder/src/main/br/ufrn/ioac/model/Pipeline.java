package br.ufrn.ioac.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pipeline {

	private static final int INSTRUCTION_FETCH_INDEX = 0;
	private static final int INSTRUCTION_DECODE_INDEX = 1;
	private static final int EXECUTION_INDEX = 2;
	private static final int MEMORY_INDEX = 3;
	private static final int WRITEBACK_INDEX = 4;

	private ArrayList<Instruction> originalInstructions = new ArrayList<Instruction>();
	private LinkedList<Instruction> pipeline = new LinkedList<Instruction>();
	private Instruction instruction;

	private Integer currentInstructionIndex = 0;

	public void add(Instruction instruction) {
		originalInstructions.add(instruction);
	}

	public String logState() {
		return "IF:\t" + getIFLog() + "\n" +
				"ID:\t" + getIDLog() + "\n" +
				"EX:\t" + "0\n" +
				"MEM:\t" + "0\n" +
				"WB:\t" + "0\n";
	}

	private String getIFLog() {
		return pipeline.size() <= INSTRUCTION_FETCH_INDEX ? "0" : getInstructionFetchInstruction().toString();
	}

	private Instruction getInstructionFetchInstruction() {
		return pipeline.get(INSTRUCTION_FETCH_INDEX);
	}

	private String getIDLog() {
		return pipeline.size() <= INSTRUCTION_DECODE_INDEX ? "0" : getInstructionDecodeInstruction().toString();
	}

	private Instruction getInstructionDecodeInstruction() {
		return pipeline.get(INSTRUCTION_DECODE_INDEX);
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public Integer countInstructions() {
		return originalInstructions.size();
	}

	public Instruction getCurrentInstruction() {
		return pipeline.isEmpty() ? null : pipeline.get(0);
	}

	public void advanceCycle() {
		pipeline.addFirst(originalInstructions.get(currentInstructionIndex));
		currentInstructionIndex++;
	}

}
