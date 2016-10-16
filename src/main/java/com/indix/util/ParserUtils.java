package com.indix.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.indix.model.Operator;

/**
 * @author Karthik Jayaraman
 *
 */
public class ParserUtils {

	private static Map<Character, Integer> precedenceMap = new HashMap<Character, Integer>();

	static {
		precedenceMap.put('!', 3);
		precedenceMap.put('&', 2);
		precedenceMap.put('|', 2);
		precedenceMap.put('(', 1);
	}

	public static boolean isVariable(Character c) {
		return Character.isLetter(c);
	}

	public static boolean isOperator(Character c) {
		return Operator.getOpForChar(c) != null;
	}

	/**
	 * 
	 * @param inFix
	 *            - expression string in infix notation
	 * @return expression string in postfix notation
	 */
	public static String toPostFix(String inFix) {
		if (inFix == null || inFix.trim().length() == 0)
			return "";
		StringBuilder postFix = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		for (Character c : inFix.toCharArray()) {
			if (isVariable(c))
				// if operand, add to out string
				postFix.append(c);
			else if (c == '(') {
				// if open brackets, add to stack
				stack.push(c);
			} else if (isOperator(c)) {
				if (stack.isEmpty()) {
					stack.push(c);
				} else {
					boolean opPrecedence = precedenceMap.get(c) > precedenceMap.get(stack.peek());
					if (stack.peek() == '(' || opPrecedence) {
						// add to stack, if stack top is open bracket or current
						// op has higher precedence than stack top
						stack.push(c);
					} else if (!opPrecedence) {
						// pop from stack to out string, if current op has
						// lower/equal precedence than stack top
						postFix.append(stack.pop());
						stack.push(c);
					}
				}
			} else if (c == ')') {
				// if encountered close brackets, pop stack into out string
				// until an open bracket is found
				while (!stack.isEmpty() && stack.peek() != '(') {
					postFix.append(stack.pop());
				}
				// yank the open bracket out of stack
				stack.pop();
			}
		}
		// pop the rest into out string
		while (!stack.isEmpty())
			postFix.append(stack.pop());
		return postFix.toString();
	}

}
