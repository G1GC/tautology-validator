package tautology.validator.parser.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import tautology.validator.exception.InvalidInputException;
import tautology.validator.model.ComplexProposition;
import tautology.validator.model.Operator;
import tautology.validator.model.ParseResult;
import tautology.validator.model.SimpleProposition;
import tautology.validator.model.Statement;
import tautology.validator.parser.Parser;
import tautology.validator.util.ParserUtils;

/**
 * @author Karthik Jayaraman
 *
 */
public class SimpleParser implements Parser {

	private boolean isValid(String input) {
		// TODO: Validate input string here, if invalid return false
		// Assuming the input string is valid, as given in the problem statement
		// for the time being
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indix.parser.Parser#parse(java.lang.String)
	 */
	public ParseResult parse(String input) throws InvalidInputException {
		if (!isValid(input))
			throw new InvalidInputException();
		// strip the whitespaces
		input = input.replaceAll(" ", "");

		// convert to postfix notation
		String postfix = ParserUtils.toPostFix(input);

		// build the expression tree
		Stack<Statement> stack = new Stack<Statement>();
		Map<Character, Integer> counterMap = new HashMap<Character, Integer>();
		int varCounter = 0;
		for (Character c : postfix.toCharArray()) {
			if (ParserUtils.isVariable(c)) {
				int cnt = 0;
				if (counterMap.containsKey(c))
					cnt = counterMap.get(c);
				else {
					cnt = varCounter++;
					counterMap.put(c, cnt);
				}
				stack.push(new SimpleProposition(cnt));
			} else if (ParserUtils.isOperator(c)) {
				ComplexProposition p = new ComplexProposition(Operator.getOpForChar(c));
				if (Operator.getOpForChar(c) == Operator.NOT) {
					// Setting only the left, incase of ! operator
					p.setLeft(stack.pop());
				} else {
					p.setRight(stack.pop());
					p.setLeft(stack.pop());
				}
				stack.push(p);
			}
		}

		// return the root
		ParseResult result = new ParseResult();
		result.setStatement(stack.pop());
		result.setNumOfVariables(varCounter);
		return result;
	}

}
