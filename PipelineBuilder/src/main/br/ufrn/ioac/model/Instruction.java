package br.ufrn.ioac.model;

public class Instruction {
	
	private String firstOperand = "";
	private String secondOperand = "";
	private String thirdOperand = "";
	
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

}
