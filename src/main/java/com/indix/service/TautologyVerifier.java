package com.indix.service;

import static java.lang.Integer.parseInt;

import java.util.Scanner;

import com.indix.exception.InvalidInputException;
import com.indix.model.ParseResult;
import com.indix.parser.Parser;
import com.indix.parser.impl.SimpleParser;

/**
 * @author Karthik Jayaraman
 *
 */
public class TautologyVerifier {

	private Parser parser;

	/**
	 * 
	 */
	public TautologyVerifier() {
	}

	public TautologyVerifier(Parser parser) {
		this.parser = parser;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public boolean isTautology(String input) {
		if (parser == null)
			throw new IllegalArgumentException("Parser is not supplied!");
		boolean tautology = true;
		try {
			ParseResult result = parser.parse(input);
			int possibilities = (int) Math.pow(2, result.getNumOfVariables());
			for (int i = 0; i < possibilities; i++) {
				boolean res = result.getStatement().evaluate(i);
				if (!res) {
					tautology = false;
					break;
				}
			}
		} catch (InvalidInputException e) {
			System.err.println("Invalid input string!");
			tautology = false;
		}
		return tautology;
	}

	public static void main(String[] args) {
		TautologyVerifier verifier = new TautologyVerifier(new SimpleParser());
		try (Scanner scanner = new Scanner(System.in)) {
			// Take input from console
			System.out.println("Enter number of propositions to validate:");
			int numProps = parseInt(scanner.nextLine());
			System.out.println("Enter propositions:");
			String[] propositions = new String[numProps];
			int i = 0;
			while (i < numProps) {
				propositions[i] = scanner.nextLine();
				i++;
			}

			// Verify and print result to console
			System.out.println("Results:");
			for (String s : propositions)
				System.out.println(verifier.isTautology(s));
		}
	}

}
