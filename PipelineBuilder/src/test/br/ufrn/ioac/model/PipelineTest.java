package br.ufrn.ioac.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class PipelineTest {

	private Pipeline pipeline;

	@Before
	public void before(){
		pipeline = new Pipeline();
	}

	@Test
	public void shouldAcceptAnInstruction(){
		pipeline.add(addInstructionBuilder());
		pipeline.advanceCycle();
		assertEquals(pipeline.countInstructions(), Integer.valueOf(1));
		assertEquals("add $s0, $s1, $s2", pipeline.getCurrentInstruction().toString());
	}

	@Test
	public void shouldAcceptManyInstructions(){
		pipeline.add(addInstructionBuilder());
		pipeline.add(addInstructionBuilder());
		assertEquals(pipeline.countInstructions(), Integer.valueOf(2));
	}

	@Test
	public void shouldLogFirstState() {
		pipeline.add(addInstructionBuilder());
		pipeline.advanceCycle();

		String logState = "IF:\t" + "add $s0, $s1, $s2\n" +
				"ID:\t" + "0\n" +
				"EX:\t" + "0\n" +
				"MEM:\t" + "0\n" +
				"WB:\t" + "0\n";
		assertNotEquals(null, pipeline.logState());
		assertNotEquals("", pipeline.logState());
		assertEquals(logState, pipeline.logState());
	}

	@Test
	public void shouldLogStateWithTwoInstructions(){
		pipeline.add(addInstructionBuilder());
		pipeline.add(subInstructionBuilder());

		pipeline.advanceCycle();
		pipeline.advanceCycle();

		String logState = "IF:\t" + "sub $t0, $t1, $t2\n" +
				"ID:\t" + "add $s0, $s1, $s2\n" +
				"EX:\t" + "0\n" +
				"MEM:\t" + "0\n" +
				"WB:\t" + "0\n";
		assertEquals(logState, pipeline.logState());

	}

	@Test
	public void shouldLogStateWithThreeInstructions(){
		pipeline.add(addInstructionBuilder());
		pipeline.add(subInstructionBuilder());
		pipeline.add(jInstructionBuilder());

		pipeline.advanceCycle();
		pipeline.advanceCycle();
		pipeline.advanceCycle();

		String logState = "IF:\t" + "j 5\n" +
				"ID:\t" + "sub $t0, $t1, $t2\n" +
				"EX:\t" + "add $s0, $s1, $s2\n" +
				"MEM:\t" + "0\n" +
				"WB:\t" + "0\n";
		assertEquals(logState, pipeline.logState());

	}

	@Test
	public void shouldLogStateWithMaxInstructions(){
		pipeline.add(addInstructionBuilder());
		pipeline.add(subInstructionBuilder());
		pipeline.add(jInstructionBuilder());
		pipeline.add(new Instruction("beq","$s1","$s2","9"));
		pipeline.add(new Instruction("sw", "$s1", "0($s3)"));

		pipeline.advanceCycle();
		pipeline.advanceCycle();
		pipeline.advanceCycle();
		pipeline.advanceCycle();
		pipeline.advanceCycle();

		String logState = "IF:\t" + "sw $s1, 0($s3)\n" +
				"ID:\t" + "beq $s1, $s2, 9\n" +
				"EX:\t" + "j 5\n" +
				"MEM:\t" + "sub $t0, $t1, $t2\n" +
				"WB:\t" + "add $s0, $s1, $s2\n";
		assertEquals(logState, pipeline.logState());
	}

	private Instruction addInstructionBuilder() {
		return new Instruction("add","$s0","$s1","$s2");
	}

	private Instruction subInstructionBuilder() {
		return new Instruction("sub","$t0","$t1","$t2");
	}

	private Instruction jInstructionBuilder() {
		return new Instruction("j", "5");
	}

}
