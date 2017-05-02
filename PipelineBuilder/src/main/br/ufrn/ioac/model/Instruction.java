package br.ufrn.ioac.model;

import br.ufrn.ioac.interfaces.Stageble;

public class Instruction implements Stageble {

	private String firstOperand = "";
	private String secondOperand = "";
	private String thirdOperand = "";
	private InstructionStage stage = InstructionStage.InstructionFind;


	public Instruction(String first, String second) {
		this(first);
		secondOperand = second;
	}

	public Instruction(String first) {
		firstOperand = first;
	}

	public Instruction(String first, String second, String third) {
		this(first,second);
		thirdOperand  = third;
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

}
