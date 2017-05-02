package br.ufrn.ioac.model;

import static org.junit.Assert.assertEquals;

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

	private Instruction addInstructionBuilder() {
		return new Instruction("add","$s0","$s1","$s2");
	}

}
