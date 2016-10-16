package tautology.validator.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tautology.validator.model.ComplexProposition;
import tautology.validator.model.Operator;
import tautology.validator.model.SimpleProposition;

/**
 * @author Karthik Jayaraman
 *
 */
public class ComplexPropositionTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testEvaluate1() {
		expectedEx.expect(IllegalArgumentException.class);
		new ComplexProposition(null);
	}

	@Test
	public void testEvaluate2() {
		ComplexProposition prop = new ComplexProposition(Operator.AND);
		assertFalse(prop.evaluate(-1));
	}

	@Test
	public void testEvaluate31() {
		expectedEx.expect(IllegalArgumentException.class);
		ComplexProposition prop = new ComplexProposition(Operator.AND);
		prop.evaluate(1);
	}

	@Test
	public void testEvaluate32() {
		expectedEx.expect(IllegalArgumentException.class);
		ComplexProposition prop = new ComplexProposition(Operator.AND);
		prop.setLeft(new SimpleProposition(0));
		prop.evaluate(1);
	}

	@Test
	public void testEvaluate33() {
		ComplexProposition prop = new ComplexProposition(Operator.AND);
		prop.setLeft(new SimpleProposition(0));
		prop.setRight(new SimpleProposition(1));
		assertFalse(prop.evaluate(1));
		assertTrue(prop.evaluate(3));
	}

	@Test
	public void testEvaluate41() {
		expectedEx.expect(IllegalArgumentException.class);
		ComplexProposition prop = new ComplexProposition(Operator.OR);
		prop.evaluate(1);
	}

	@Test
	public void testEvaluate42() {
		expectedEx.expect(IllegalArgumentException.class);
		ComplexProposition prop = new ComplexProposition(Operator.OR);
		prop.setLeft(new SimpleProposition(0));
		prop.evaluate(1);
	}

	@Test
	public void testEvaluate43() {
		ComplexProposition prop = new ComplexProposition(Operator.OR);
		prop.setLeft(new SimpleProposition(0));
		prop.setRight(new SimpleProposition(1));
		assertFalse(prop.evaluate(4));
		assertTrue(prop.evaluate(3));
	}

	@Test
	public void testEvaluate51() {
		expectedEx.expect(IllegalArgumentException.class);
		ComplexProposition prop = new ComplexProposition(Operator.NOT);
		prop.evaluate(1);
	}

	@Test
	public void testEvaluate52() {
		ComplexProposition prop = new ComplexProposition(Operator.NOT);
		prop.setLeft(new SimpleProposition(0));
		assertFalse(prop.evaluate(1));
		assertTrue(prop.evaluate(4));
	}

}
