package tautology.validator.model;

/**
 * @author Karthik Jayaraman
 *
 */
public class ComplexProposition implements Statement {
	private Statement left;
	private Statement right;
	private Operator op;

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	public Statement getLeft() {
		return left;
	}

	public void setLeft(Statement left) {
		this.left = left;
	}

	public Statement getRight() {
		return right;
	}

	public void setRight(Statement right) {
		this.right = right;
	}

	public ComplexProposition(Operator op, Statement left, Statement right) {
		if (op == null)
			throw new IllegalArgumentException("Operator cannot be null!");
		this.op = op;
		this.left = left;
		this.right = right;
	}

	public ComplexProposition(Operator op) {
		this(op, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indix.model.Statement#evaluate(int)
	 */
	public boolean evaluate(int data) {
		if (data < 0)
			return false;
		switch (op) {
		case AND:
			if (left == null || right == null)
				throw new IllegalArgumentException("No or invalid operands!");
			return left.evaluate(data) && right.evaluate(data);
		case OR:
			if (left == null || right == null)
				throw new IllegalArgumentException("No or invalid operands!");
			return left.evaluate(data) || right.evaluate(data);
		case NOT:
			if (left == null && right == null)
				throw new IllegalArgumentException("No or invalid operand!");
			// alternatively we can restrict to store the negated variable only
			// on right or left to avoid the below check
			Statement st = (left != null) ? left : right;
			return !st.evaluate(data);
		}
		return false;
	}

}
