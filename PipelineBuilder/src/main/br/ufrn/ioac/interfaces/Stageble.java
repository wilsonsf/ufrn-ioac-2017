package br.ufrn.ioac.interfaces;

import br.ufrn.ioac.model.InstructionStage;

public interface Stageble {

	public InstructionStage currentStage();
	public void nextStage();

}
