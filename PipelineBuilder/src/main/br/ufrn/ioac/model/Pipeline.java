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

	private Integer currentInstructionIndex = 0;

	public void add(Instruction instruction) {
		originalInstructions.add(instruction);
	}

	public String logState() {
		return "IF:\t" + getIFLog() + "\n" +
				"ID:\t" + getIDLog() + "\n" +
				"EX:\t" + getEXLog() + "\n" +
				"MEM:\t" + getMEMLog() + "\n" +
				"WB:\t" + getWBLog() + "\n";
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

	private String getMEMLog() {
		return pipeline.size() <= MEMORY_INDEX ? "0" : getMemoryInstruction().toString();
	}

	private Instruction getMemoryInstruction() {
		return pipeline.get(MEMORY_INDEX);
	}

	private String getEXLog() {
		return pipeline.size() <= EXECUTION_INDEX ? "0" : getExecutionInstruction().toString();
	}

	private Instruction getExecutionInstruction() {
		return pipeline.get(EXECUTION_INDEX);
	}

	private String getWBLog() {
		return pipeline.size() <= WRITEBACK_INDEX ? "0" : getWriteBackInstruction().toString();
	}

	private Instruction getWriteBackInstruction() {
		return pipeline.get(WRITEBACK_INDEX);
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

	public final Integer getCurrentCycle() {
		return Integer.valueOf(currentInstructionIndex);
	}

}
