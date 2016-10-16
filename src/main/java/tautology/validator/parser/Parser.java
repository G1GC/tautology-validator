package tautology.validator.parser;

import tautology.validator.exception.InvalidInputException;
import tautology.validator.model.ParseResult;

/**
 * @author Karthik Jayaraman
 *
 */
public interface Parser {

	public ParseResult parse(String input) throws InvalidInputException;

}
