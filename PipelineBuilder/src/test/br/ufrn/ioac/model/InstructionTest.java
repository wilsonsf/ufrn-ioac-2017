package br.ufrn.ioac.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class InstructionTest {

	private Instruction instruction;

	@Before
	public void prepareInstruction(){
		instruction = new Instruction("add","$s0","$s1","$s2");
	}

	@Test
	public void shouldCreateInstructionWithNameAndOneOperand(){
		Instruction instruction = new Instruction("j","$s1");
		assertNotEquals(instruction, null);
	}

	@Test
	public void shouldCreateInstructionWithNameAndTwoOperands(){
		Instruction instruction = new Instruction("addi","$s1","5");
		assertNotEquals(instruction, null);
	}

	@Test
	public void shouldCreateInstructionWithNameAndThreeOperands(){
		assertEquals(instruction.getInstructionName(), "add");
		assertNotEquals(instruction, null);
	}

	@Test
	public void shouldReturnInstructionName(){
		assertNotEquals(instruction.getInstructionName(), null);
		assertNotEquals(instruction.getInstructionName(), "");
		assertEquals(instruction.getInstructionName(), "add");
	}
	@Test
	public void shouldReturnFirstOperand(){
		assertEquals(instruction.getFirstOperand(), "$s0");
	}

	@Test
	public void shouldReturnSecondOperand(){
		assertEquals(instruction.getSecondOperand(), "$s1");
	}

	@Test
	public void shouldReturnThirdOperand(){
		assertEquals(instruction.getThirdOperand(), "$s2");
	}

	@Test
	public void shouldHaveStageInformation(){
		assertNotNull(instruction.currentStage());
	}

	@Test
	public void shouldStartWithStageInstructionFind(){
		assertEquals(instruction.currentStage(), InstructionStage.InstructionFind);
	}

	@Test
	public void shouldAdvanceOneStage() throws Exception{
		instruction.nextStage();
		assertNotEquals(instruction.currentStage(), InstructionStage.InstructionFind);
	}

	@Test
	public void shouldAdvanceOnRightOrder() throws Exception{
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.InstructionDecode);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.Execution);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.Memory);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.WriteBack);
	}

	@Test
	public void shouldLog() {
		assertNotEquals("", instruction.log());
	}

}
