package br.ufrn.ioac.model;

import java.util.HashSet;
import java.util.Set;

import br.ufrn.ioac.interfaces.Stageble;

public class Instruction implements Stageble {

	private String name = "";
	private String firstOperand = "";
	private String secondOperand = "";
	private String thirdOperand = "";
	private InstructionStage stage = InstructionStage.InstructionFind;

	static private Set<String> instructionNames = new HashSet<String>();

	static {
		// ADD, SUB, BEQ, BNE, LW, SW e JUMP
		instructionNames.add("add"); // writeback delay
		instructionNames.add("sub"); // writeback delay
		instructionNames.add("beq"); // no label - jump to
		instructionNames.add("bne"); // number of instruction
		instructionNames.add("lw"); // memory delay
		instructionNames.add("sw"); // no delay
		instructionNames.add("j");  // no delay
	}


	public Instruction(String name, String first) {
		this.name = name;
		firstOperand = first;
	}

	@Deprecated
	public Instruction(String first) {
		firstOperand = first;
	}

	public Instruction(String name, String first, String second) {
		this(name,first);
		secondOperand  = second;
	}

	public Instruction(String name, String first, String second, String third) {
		this(name,first,second);
		thirdOperand = third;
	}

	public String getInstructionName() {
		return name;
	}

	public String getFirstOperand() {
		return firstOperand;
	}

	public String getSecondOperand() {
		return secondOperand;
	}

	public String getThirdOperand() {
		return thirdOperand;
	}

	@Override
	public InstructionStage currentStage() {
		return stage;
	}

	@Override
	public void nextStage() {
		switch (stage) {
		case InstructionFind:
			stage = InstructionStage.InstructionDecode;
			break;
		case InstructionDecode:
			stage = InstructionStage.Execution;
			break;
		case Execution:
			stage = InstructionStage.Memory;
			break;
		case Memory:
			stage = InstructionStage.WriteBack;
			break;
		case WriteBack:
			break;
		default:
			// throw new Exception("Estágio de instrução indefinida, não é possível avançar.");
		}
	}

	public String log() {
		return "";
	}

}
