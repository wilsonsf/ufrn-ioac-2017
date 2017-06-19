package br.ufrn.ioac.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.ufrn.ioac.exception.ConfigurationFileException;
import br.ufrn.ioac.interfaces.WritingPolicyI;
import br.ufrn.ioac.model.Simulator;
import br.ufrn.ioac.model.SimulatorBuilder;

public class ConfigurationManager {

	private static Scanner scanner = null;
	private static SimulatorBuilder builder = null;

	public static Simulator processConfigurationFile() throws ConfigurationFileException {

		builder = new SimulatorBuilder();

		try {
			scanner = new Scanner(new FileInputStream("config.txt"));

			if(!scanner.hasNextLine())
				throw new ConfigurationFileException("Arquivo de configuração vazio.");

			blockSizeStep();
			cacheSizeStep();
			memorySizeStep();
			mappingStep();
			substitutionPolicyStep();
			writingPolicyStep();

		} catch (FileNotFoundException e) {
			throw new ConfigurationFileException("Não achou o arquivo de configuração (config.txt) na pasta raiz.");
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return builder.build();
	}

	/*
	 * Tamanho do bloco (em número de palavras)
	 */
	private static void blockSizeStep() throws ConfigurationFileException {
		int blockSize = scanner.nextInt();
		if (blockSize <= 0)
			throw new ConfigurationFileException("Tamanho do bloco inválido.");
		System.out.println("Palavras por bloco: " + blockSize);

		builder.withBlockSize(blockSize);
	}

	/*
	 * Numero de linhas da cache
	 */
	private static void cacheSizeStep() throws ConfigurationFileException {
		int cacheSize = scanner.nextInt();
		if (cacheSize <= 0)
			throw new ConfigurationFileException("Tamanho da cache inválido.");

		System.out.println("Tamanho da cache:" + cacheSize);
		builder.withCacheSize(cacheSize);
	}

	/*
	 * Numero de blocos da memória principal
	 */
	private static void memorySizeStep() throws ConfigurationFileException {
		int memorySize = scanner.nextInt();
		if (memorySize <= 0)
			throw new ConfigurationFileException("Tamanho da memória inválido.");

		System.out.println("Tamanho da memória: " + memorySize);
		builder.withMemorySize(memorySize);
	}

	/*
	 * Mapeamento
	 * (1 –Direto; 2 –Totalmente Associativo; 3 –Parcialmente Associativo)
	 *
	 * Numero de conjuntos
	 * (caso não seja Parcialmente Associativo, ler o valor normalmente mas desconsidere-o)
	 */
	private static void mappingStep() throws ConfigurationFileException {
		int mappingType = scanner.nextInt();
		if ((mappingType < 1) || (mappingType > 3))
			throw new ConfigurationFileException("Regra de mapeamento inválida.");

		int waySize = scanner.nextInt();
		if (waySize < 0)
			throw new ConfigurationFileException("Número de conjuntos inválido.");

		System.out.println("Tipo de mapeamento: " + mappingType);
		System.out.println("Numero de conjuntos: " + waySize);

		//TODO Mapping and n-way policy
		builder.withMappingPolicy(null);
	}

	/*
	 * Política de substituição
	 * (1 –Aleatório; 2 –FIFO; 3 –LFU; 4 –LRU)
	 */
	private static void substitutionPolicyStep() throws ConfigurationFileException {
		int substitutionPolicy = scanner.nextInt();
		if ((substitutionPolicy < 1) || (substitutionPolicy > 4))
			throw new ConfigurationFileException("Regra de substituição inválida.");

		System.out.println("Regra de substituição: "+ substitutionPolicy);

		builder.withSubstitutionPolicy(null);
	}

	/*
	 * Política de Escrita
	 * (1 –Write-back; 2 –Write-Through)
	 */
	private static void writingPolicyStep() throws ConfigurationFileException {
		int writingPolicyValue = scanner.nextInt();
		WritingPolicyI writingPolicy = WritingPolicyManager.getPolicy(writingPolicyValue);

		System.out.println("Regra de escrita: " + writingPolicyValue);

		builder.withWritingPolicy(writingPolicy);
	}
}
