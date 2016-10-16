package tautology.validator.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tautology.validator.util.ParserUtils;

/**
 * @author Karthik Jayaraman
 *
 */
public class ParserUtilsTest {

	/**
	 * Test method for
	 * {@link tautology.validator.util.ParserUtils#isVariable(java.lang.Character)}.
	 */
	@Test
	public void testIsVariable() {
		assertTrue(ParserUtils.isVariable('a'));
		assertTrue(ParserUtils.isVariable('A'));
		assertFalse(ParserUtils.isVariable('1'));
		assertFalse(ParserUtils.isVariable('&'));
	}

	/**
	 * Test method for
	 * {@link tautology.validator.util.ParserUtils#isOperator(java.lang.Character)}.
	 */
	@Test
	public void testIsOperator() {
		assertTrue(ParserUtils.isOperator('&'));
		assertTrue(ParserUtils.isOperator('|'));
		assertTrue(ParserUtils.isOperator('!'));
		assertFalse(ParserUtils.isOperator('~'));
		assertFalse(ParserUtils.isOperator('a'));
	}

	/**
	 * Test method for
	 * {@link tautology.validator.util.ParserUtils#toPostFix(java.lang.String)}.
	 */
	@Test
	public void testToPostFix() {
		assertTrue(ParserUtils.toPostFix(null).equals(""));
		assertTrue(ParserUtils.toPostFix("").equals(""));
		assertTrue(ParserUtils.toPostFix("  ").equals(""));
		assertTrue(ParserUtils.toPostFix("a&b").equals("ab&"));
		assertTrue(ParserUtils.toPostFix("(a&b)|c").equals("ab&c|"));
		assertTrue(ParserUtils.toPostFix("a&(b|c)").equals("abc|&"));
		assertTrue(ParserUtils.toPostFix("a&(b|!c)").equals("abc!|&"));
	}

}
