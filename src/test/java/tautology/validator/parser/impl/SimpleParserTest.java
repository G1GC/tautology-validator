package tautology.validator.parser.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tautology.validator.exception.InvalidInputException;
import tautology.validator.model.ComplexProposition;
import tautology.validator.model.Operator;
import tautology.validator.model.SimpleProposition;
import tautology.validator.parser.Parser;
import tautology.validator.parser.impl.SimpleParser;

/**
 * @author Karthik Jayaraman
 *
 */
public class SimpleParserTest {

	/**
	 * Test method for
	 * {@link tautology.validator.parser.impl.SimpleParser#parse(java.lang.String)}.
	 */
	@Test
	public void testParse1() {
		Parser parser = new SimpleParser();
		try {
			assertTrue(parser.parse("a&(b|!c)").getNumOfVariables() == 3);
		} catch (InvalidInputException e) {
			fail();
		}
	}

	@Test
	public void testParse2() {
		Parser parser = new SimpleParser();
		try {
			ComplexProposition statement = (ComplexProposition) parser.parse("a&(b|!c)").getStatement();
			assertTrue(statement.getOp() == Operator.AND);
			SimpleProposition sProp = (SimpleProposition) statement.getLeft();
			assertTrue(sProp.getPosition() == 0);
			ComplexProposition cProp = (ComplexProposition) statement.getRight();
			assertTrue(cProp.getOp() == Operator.OR);
			sProp = (SimpleProposition) cProp.getLeft();
			assertTrue(sProp.getPosition() == 1);
			cProp = (ComplexProposition) cProp.getRight();
			assertTrue(cProp.getOp() == Operator.NOT);
			sProp = (SimpleProposition) cProp.getLeft();
			assertTrue(sProp.getPosition() == 2);
		} catch (InvalidInputException e) {
			fail();
		}
	}

}
