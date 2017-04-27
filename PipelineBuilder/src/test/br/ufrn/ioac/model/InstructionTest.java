package br.ufrn.ioac.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstructionTest {

	@Test
	public void shouldCreateInstructionWith1Operand(){
		new Instruction("$s1");
	}
	
	@Test
	public void shouldCreateInstructionWith2Operands(){
		new Instruction("$s1","$s2");
	}

	@Test
	public void shouldCreateInstructionWith3Operands(){
		new Instruction("$s1","$s2","$s3");
	}
	
	@Test
	public void shouldReturnFirstOperand(){
		Instruction instruction = new Instruction("$s1","$s2");
		assertEquals(instruction.getFirstOperand(), "$s1");
	}
	
	@Test
	public void shouldReturnSecondOperand(){
		Instruction instruction = new Instruction("$s1","$s2");
		assertEquals(instruction.getSecondOperand(), "$s2");
	}
	
	@Test
	public void shouldReturnThirdOperand(){
		Instruction instruction = new Instruction("$s1","$s2","$s3");
		assertEquals(instruction.getThirdOperand(), "$s3");
	}
	
	@Test
	public void shouldHaveStageInformation(){
		Instruction instruction = new Instruction("s1");
		assertNotNull(instruction.getStage());
	}
	
	@Test
	public void shouldStartWithStageInstructionFind(){
		Instruction instruction = new Instruction("s1");
		assertEquals(instruction.getStage(), InstructionStage.InstructionFind);
	}
	
	@Test
	public void shouldAdvanceOneStage() throws Exception{
		Instruction instruction = new Instruction("s1");
		instruction.stepForward();
		assertNotEquals(instruction.getStage(), InstructionStage.InstructionFind);
	}
	
	@Test
	public void shouldAdvanceOnRightOrder() throws Exception{
		Instruction instruction = new Instruction("s1");
		instruction.stepForward();
		assertEquals(instruction.getStage(), InstructionStage.InstructionDecode);
		instruction.stepForward();
		assertEquals(instruction.getStage(), InstructionStage.Execution);
		instruction.stepForward();
		assertEquals(instruction.getStage(), InstructionStage.Memory);
		instruction.stepForward();
		assertEquals(instruction.getStage(), InstructionStage.WriteBack);
		instruction.stepForward();
		assertEquals(instruction.getStage(), InstructionStage.Done);
	}
	
}
