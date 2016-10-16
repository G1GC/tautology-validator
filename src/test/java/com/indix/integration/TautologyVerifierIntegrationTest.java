package com.indix.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.indix.parser.impl.SimpleParser;
import com.indix.service.TautologyVerifier;

/**
 * @author Karthik Jayaraman
 *
 */
public class TautologyVerifierIntegrationTest {

	/**
	 * Test method for
	 * {@link com.indix.service.TautologyVerifier#isTautology(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsTautology() {
		TautologyVerifier verifier = new TautologyVerifier(new SimpleParser());
		System.out.println(verifier.isTautology("(!(a | (a & a))"));
		System.out.println(verifier.isTautology("(!a | (b & !a))"));
		System.out.println(verifier.isTautology("(!a | a)"));
		System.out.println(verifier.isTautology("((a & (!b | b)) | (!a & (!b | b)))"));
		assertTrue(verifier.isTautology("(!a | (a & a))"));
		assertFalse(verifier.isTautology("(!a | (b & !a))"));
		assertTrue(verifier.isTautology("(!a | a)"));
		assertTrue(verifier.isTautology("((a & (!b | b)) | (!a & (!b | b)))"));
	}

}
