package br.ufrn.ioac;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.ufrn.ioac.model.Instruction;
import br.ufrn.ioac.model.Pipeline;

public class PipelineMain {

	public static void main(String[] args) {

		Pipeline pipeline = new Pipeline();
		try {
			Scanner scan = new Scanner(new FileInputStream("src/main/br/ufrn/ioac/Exemplo1"));

			while (scan.hasNextLine()) {
				String[] words = scan.nextLine()
						.replaceAll(",", "")
						.split(" ");
				Instruction instruction = new Instruction(words);
				pipeline.add(instruction);
				System.out.println(instruction);

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
