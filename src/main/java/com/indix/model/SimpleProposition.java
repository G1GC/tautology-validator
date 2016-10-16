package com.indix.model;

/**
 * @author Karthik Jayaraman
 *
 */
public class SimpleProposition implements Statement {

	// Position of the variable
	private int position;

	public int getPosition() {
		return position;
	}

	public SimpleProposition(int position) {
		if (position < 0)
			throw new IllegalArgumentException("Position cannot be negative!");
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indix.model.Statement#evaluate(int)
	 */
	public boolean evaluate(int data) {
		if (data < 0)
			return false;
		return (data & (1 << position)) > 0;
	}

}
