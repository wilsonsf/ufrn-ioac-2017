package br.ufrn.ioac.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.ufrn.ioac.interfaces.Stageble;

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
		Stageble instruction = new Instruction("s1");
		assertNotNull(instruction.currentStage());
	}

	@Test
	public void shouldStartWithStageInstructionFind(){
		Stageble instruction = new Instruction("s1");
		assertEquals(instruction.currentStage(), InstructionStage.InstructionFind);
	}

	@Test
	public void shouldAdvanceOneStage() throws Exception{
		Instruction instruction = new Instruction("s1");
		instruction.nextStage();
		assertNotEquals(instruction.currentStage(), InstructionStage.InstructionFind);
	}

	@Test
	public void shouldAdvanceOnRightOrder() throws Exception{
		Instruction instruction = new Instruction("s1");
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.InstructionDecode);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.Execution);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.Memory);
		instruction.nextStage();
		assertEquals(instruction.currentStage(), InstructionStage.WriteBack);
	}

}
