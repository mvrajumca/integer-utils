package com.ts.largest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.ts.largest.exception.InvalidIntegerListException;

/**
 * @author Raju
 *
 */
public class IntegerUtilsTest {

	private static final int[] integerValues = {45,15,-2,12,45,32,25,12,14,36};
	
	private IntegerUtils utils = null;
	
	@Before
	public void setup() {
		utils = new IntegerUtils();
	}
	
	@Test(expected=InvalidIntegerListException.class)
	public void testFindSecondLargestInvalidNullInput() throws Exception {
		utils.findSecondLargest(null);
	}
	
	@Test(expected=InvalidIntegerListException.class)
	public void testFindSecondLargestInvalidInput() throws Exception {
		utils.findSecondLargest(new int[0]);
	}
	
	@Test
	public void testFindSecondLargestValidInput() throws Exception {
		int result = utils.findSecondLargest(integerValues);
		assertEquals(36, result);
	}
	
	@Test
	public void testFindLargestValidInput() throws Exception {
		int result = utils.findLargest(integerValues, 2);
		assertEquals(36, result);
	}
	
	@Test(expected=InvalidIntegerListException.class)
	public void testFindLargestInvalidNullInput() throws Exception {
		utils.findLargest(null, 0);
	}
}
