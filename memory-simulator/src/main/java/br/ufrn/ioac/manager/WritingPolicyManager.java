package br.ufrn.ioac.manager;

import br.ufrn.ioac.exception.ConfigurationFileException;
import br.ufrn.ioac.interfaces.WritingPolicyI;

public class WritingPolicyManager {
	public static WritingPolicyI getPolicy (int serial) throws ConfigurationFileException {
		switch (serial) {
		case 1:	return null;
			
		case 2: return null;
			
		default:
			throw new ConfigurationFileException("Regra de escrita inválida.");
		}
	}
}
