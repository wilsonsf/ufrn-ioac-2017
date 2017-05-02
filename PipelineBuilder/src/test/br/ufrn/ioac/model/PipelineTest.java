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
	public void shouldLogCurentState() {
		Pipeline pipeline = new Pipeline();
		pipeline.add(addInstructionBuilder());

		String logState = "IF:\t" + "add $s0, $s1, $s2\n" +
				"ID:\t" + "0\n" +
				"EX:\t" + "0\n" +
				"MEM:\t" + "0\n" +
				"WB:\t" + "0\n";
		assertNotEquals(pipeline.logState(), null);
		assertNotEquals(pipeline.logState(), "");
		assertEquals(pipeline.logState(), logState);
	}

	private Instruction addInstructionBuilder() {
		return new Instruction("add","$s0","$s1","$s2");
	}

}
