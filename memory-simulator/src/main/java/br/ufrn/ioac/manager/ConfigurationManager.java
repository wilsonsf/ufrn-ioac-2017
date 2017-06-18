package br.ufrn.ioac.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.ufrn.ioac.exception.ConfigurationFileException;
import br.ufrn.ioac.interfaces.WritingPolicyI;
import br.ufrn.ioac.model.Memory;
import br.ufrn.ioac.model.Simulator;

public class ConfigurationManager {
	
	@SuppressWarnings("unused")
	public static Simulator processConfigurationFile(String filePath) throws ConfigurationFileException {
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("config.txt"));
			
			if(!scanner.hasNextLine()) 
				throw new ConfigurationFileException("Arquivo de configuração vazio.");
			
//			   Tamanho do bloco (em número de palavras)
			int blockSize = scanner.nextInt();
			if (blockSize <= 0)
				throw new ConfigurationFileException("Tamanho do bloco inválido.");
			
//			   Numero de linhas da cache
			int cacheSize = scanner.nextInt();
			if (cacheSize <= 0)
				throw new ConfigurationFileException("Tamanho da cache inválido.");
			
			Memory cache = new Memory(cacheSize, 1);
			
//			   Numero de blocos da memória principal
			int memorySize = scanner.nextInt();
			if (memorySize <= 0)
				throw new ConfigurationFileException("Tamanho da memória inválido.");
			
			Memory memory = new Memory(memorySize, 1);
			
//			   Mapeamento 
//			   	(1 –Direto; 2 –Totalmente Associativo; 3 –Parcialmente Associativo)
			int mappingType = scanner.nextInt();
			if (mappingType < 1 || mappingType > 3)
				throw new ConfigurationFileException("Regra de mapeamento inválida.");
			
//			   Numero de conjuntos 
//			   	(caso não seja Parcialmente Associativo, ler o valor normalmente mas desconsidere-o)
			int waySize = scanner.nextInt();
			if (waySize < 0)
				throw new ConfigurationFileException("Número de conjuntos inválido.");
			
//			   Política de substituição 
//			   	(1 –Aleatório; 2 –FIFO; 3 –LFU; 4 –LRU)
			int substitutionPolicy = scanner.nextInt();
			if (substitutionPolicy < 1 || substitutionPolicy > 4)
				throw new ConfigurationFileException("Regra de substituição inválida.");
			
//			   Política de Escrita
//			   	(1 –Write-back; 2 –Write-Through)
			int writingPolicyValue = scanner.nextInt();
			WritingPolicyI writingPolicy = WritingPolicyManager.getPolicy(writingPolicyValue);
			
			
		} catch (FileNotFoundException e) {
			throw new ConfigurationFileException("Não achou o arquivo de configuração (config.txt) na pasta raiz.");
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return null;
	}
}
