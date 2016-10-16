package com.indix.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.indix.exception.InvalidInputException;
import com.indix.model.ParseResult;
import com.indix.model.Statement;
import com.indix.parser.Parser;

/**
 * @author Karthik Jayaraman
 *
 */
public class TautologyVerifierTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testIsTautology1() {
		expectedEx.expect(IllegalArgumentException.class);
		new TautologyVerifier().isTautology("");
	}

	/**
	 * Test method for
	 * {@link com.indix.service.TautologyVerifier#isTautology(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsTautology2() {
		final int numOfVars = 3;
		Parser parser = Mockito.mock(Parser.class);
		Statement statement = Mockito.mock(Statement.class);
		when(statement.evaluate(anyInt())).thenAnswer(new Answer<Boolean>() {

			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				int iterationCount = (Integer) invocation.getArguments()[0];
				int maxIteration = (int) Math.pow(2, numOfVars) - 1;
				if (iterationCount == maxIteration) {
					return false;
				}
				return true;
			}
		});
		ParseResult result = new ParseResult();
		result.setStatement(statement);
		result.setNumOfVariables(numOfVars);
		try {
			when(parser.parse(anyString())).thenReturn(result);
		} catch (InvalidInputException e) {
			fail();
		}
		TautologyVerifier verifier = new TautologyVerifier(parser);
		assertFalse(verifier.isTautology("(!a | (b & !a))"));
	}

}
