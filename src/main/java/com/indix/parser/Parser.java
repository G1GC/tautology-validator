package com.indix.parser;

import com.indix.exception.InvalidInputException;
import com.indix.model.ParseResult;

/**
 * @author Karthik Jayaraman
 *
 */
public interface Parser {

	public ParseResult parse(String input) throws InvalidInputException;

}
