package com.indix.model;

/**
 * @author Karthik Jayaraman
 *
 */
public enum Operator {
	AND('&', 1), OR('|', 2), NOT('!', 3);

	char c;

	/**
	 * 
	 */
	private Operator(char c, int precedence) {
		this.c = c;
	}

	public static Operator getOpForChar(char c) {
		for (Operator op : Operator.values()) {
			if (op.c == c)
				return op;
		}
		return null;
	}

}
