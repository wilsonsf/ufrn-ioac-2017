package br.ufrn.ioac;

import java.util.Scanner;

import br.ufrn.ioac.exception.ConfigurationFileException;
import br.ufrn.ioac.manager.ConfigurationManager;
import br.ufrn.ioac.model.Simulator;

public class App {

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			Simulator simulator = ConfigurationManager.processConfigurationFile();

			scanner = new Scanner(System.in);

			while (true) {
				String line = scanner.nextLine();
				String[] entries = line.split(" ");
				String command = entries[0];

				Integer address;

				if (command.equalsIgnoreCase("show")) {
					System.out.println(simulator.show());
				} else if (command.equalsIgnoreCase("read")) {
					if (entries.length > 1) {
						address = Integer.valueOf(entries[1]);
						System.out.println(simulator.read(address));
					} else {
						System.out.println("Faltou endereço!");
					}
				} else if (command.equalsIgnoreCase("write")) {
					if (entries.length > 2) {
						System.out.println("Vai gravar!");
						address = Integer.valueOf(entries[1]);
						System.out.println(simulator.write(address, Integer.valueOf(entries[2])));
					} else {
						System.out.println("Faltou endereço!");
					}
				} else {
					System.out.println("Comando inválido!");
				}
			}
		} catch (ConfigurationFileException e) {
			System.err.println("Problema no arquivo de configuração!");
			System.err.println(e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
