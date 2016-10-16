package tautology.validator.model;

/**
 * @author Karthik Jayaraman
 *
 */
public class ParseResult {

	private Statement statement;

	private int numOfVariables;

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public int getNumOfVariables() {
		return numOfVariables;
	}

	public void setNumOfVariables(int numOfVariables) {
		this.numOfVariables = numOfVariables;
	}

}
