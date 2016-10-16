package tautology.validator.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tautology.validator.model.SimpleProposition;

/**
 * @author Karthik Jayaraman
 *
 */
public class SimplePropositionTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testEvaluate1() {
		expectedEx.expect(IllegalArgumentException.class);
		new SimpleProposition(-2);
	}

	@Test
	public void testEvaluate2() {
		SimpleProposition prop = new SimpleProposition(0);
		assertFalse(prop.evaluate(-1));
	}

	@Test
	public void testEvaluate3() {
		SimpleProposition prop = new SimpleProposition(1);
		assertTrue(prop.evaluate(2));
		assertFalse(prop.evaluate(4));
	}

}
