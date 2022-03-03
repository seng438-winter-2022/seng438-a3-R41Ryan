package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange, exampleRangePositive, exampleRangeNegative, exampleRangeZero,exampleLargeRange, decimalRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-10, 10);
    	exampleRangePositive = new Range(1,100);
    	exampleRangeNegative = new Range(-100,-1);
    	exampleRangeZero = new Range(0,0);
    	exampleLargeRange = new Range(-1000000000,1000000000);
	decimalRange = new Range(-5.567,8.546);
    }

    //getCentralValue Method Testing
    
    @Test
    // This tests for the central value of a range spanning from negative to positive values
    public void centralValueMixedRange() {
        assertEquals("The central value of -10 and 10 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    // This tests for the central value of a range restricted to positive values
    public void centralValuePositiveRange() {
        assertEquals("The central value of 1 and 100 should be 50.5",
        50.5, exampleRangePositive.getCentralValue(), .000000001d);
    }
    
    @Test
    // This tests for the central value of a zero width range from 0 to 0 
    public void centralValueZeroWidthRange() {
    	assertEquals("The central value of 0 and 0 should be 0",
        0, exampleRangeZero.getCentralValue(), .000000001d);
    }
    @Test
    // This tests for the central value of a range restricted to negative numbers
    public void centralValueNegativeRange() {
        assertEquals("The central value of -100 and -1 should be -50.5",
        -50.5, exampleRangeNegative.getCentralValue(), .000000001d);
    }
    @Test
    // This tests for the central value of a range restricted to negative numbers
    public void centralValueLargeRange() {
        assertEquals("The central value of -1000000000 and 1000000000 should be 0",
        0, exampleLargeRange.getCentralValue(), .000000001d);
    }
	@Test
    // This tests that central value can return a correct number when the range includes non integers
    public void centralValueNonIntRange() {
        assertEquals("The central value of -5.567 and 8.546 should be 1.4895",
        1.4895, decimalRange.getCentralValue(), .000000001d);
    }
    
    // Contains method Testing
    
    @Test
    // This tests a range with an input variable that is within the range
    public void containsValueInRange() {
    	assertTrue("The value 0 is in the range -10 to 10", exampleRange.contains(0));
    }
    // this tests a range with an input variable that is less than the range
    @Test 
    public void containsValueLessThanRange() {
    	assertFalse("The value -11 is not in the range -10 to 10", exampleRange.contains(-11));
    }
    // this tests a range with an input variable that is greater than the range
    @Test
    public void containsValueGreaterThanRange() {
    	assertFalse("The value 11 is not in the range -10 to 10", exampleRange.contains(11));
    }
    @Test
    // this tests a range where the input value is the lower boundary
    public void containsValueIsLowerBound() {
    	assertTrue("The value -10 is in the range -10 to 10", exampleRange.contains(-10));
    }
    @Test
    // this tests a range where the input value is the upper boundary
    public void containsValueisUpperBound() {
    	assertTrue("The value 10 is in the range -10 to 10", exampleRange.contains(10));
    }
    @Test
    // This tests a zero width range to see if the value used for both boundaries is contained
    public void containsValueisUpperAndLowerBound() {
    	assertTrue("The value 0 is in the range 0 to 0", exampleRangeZero.contains(0));
    }
    
    //getLength method testing
    
    @Test
    // This tests a range that spans from negative to positive
    public void getLengthMixedRange() {
        assertEquals("The length of -10 and 10 should be 20",
        20, exampleRange.getLength(), .000000001d);
    }
    @Test
    // This tests a range that is constrained to negative values
    public void getLengthNegativeRange() {
        assertEquals("The length of -100 and -1 should be 99",
        99, exampleRangeNegative.getLength(), .000000001d);
    }
    @Test
    // This tests a range that is constrained to positive values
    public void getLengthPositiveRange() {
        assertEquals("The length of 1 and 100 should be 100",
        99, exampleRangePositive.getLength(), .000000001d);
    }
    @Test
    // This tests a zero width range from 0 to 0
    public void getLengthZeroRange() {
        assertEquals("The length of 0 and 0 should be 0",
        0, exampleRangeZero.getLength(), .000000001d);
    }
    @Test
    // This tests a zero width range from 0 to 0
    public void getLengthLargeRange() {
        assertEquals("The length of 0 and 0 should be 0",
        2000000000, exampleLargeRange.getLength(), .000000001d);
    }
	@Test
    // This tests a range with non integer boundaries
    public void getLengthNonIntRange() {
        assertEquals("The length of -5.567 and 8.546 should be 14.113",
        14.113, decimalRange.getLength(), .000000001d);
    }
    
    // getLowerBound() testing
    @Test
    // Tests a range with a negative lower bound
    public void testNegativeLowerBound() {
    	exampleRange = new Range(-2, 4);
        assertEquals("The lower bound of -2 and 4 should be -2",
        -2, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a positive lower bound
    public void testPositiveLowerBound() {
    	exampleRange = new Range(3, 10);
        assertEquals("The lower bound of 3 and 10 should be 3",
        3, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same positive upper and lower bound
    public void testSamePositiveValuesLowerBound() {
    	exampleRange = new Range(1, 1);
    	assertEquals("The lower bound of 1 and 1 should be 1",
    	1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same negative upper and lower bound
    public void testSameNegativeValuesLowerBound() {
    	exampleRange = new Range(-1, -1);
    	assertEquals("The lower bound of -1 and -1 should be -1",
    	-1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a negative decimal lower bound
    public void testNegativeDecimalLowerBound() {
    	exampleRange = new Range(-2.03, 4.15);
        assertEquals("The lower bound of -2.03 and 4.15 should be -2.03",
        -2.03, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a positive decimal lower bound
    public void testPositiveDecimalLowerBound() {
    	exampleRange = new Range(3.54, 10.002);
        assertEquals("The lower bound of 3.54 and 10.002 should be 3.54",
        3.54, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same positive decimal upper and lower bound
    public void testSamePositiveValuesDecimalLowerBound() {
    	exampleRange = new Range(1.112, 1.112);
    	assertEquals("The lower bound of 1.112 and 1.112 should be 1.112",
    	1.112, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same negative decimal upper and lower bound
    public void testSameNegativeValuesDecimalLowerBound() {
    	exampleRange = new Range(-1.112, -1.112);
    	assertEquals("The lower bound of -1.112 and -1.112 should be -1.112",
    	-1.112, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a very large lower bound
    public void testLargeLowerBound() {
    	exampleRange = new Range(1000000000, 1000000001);
    	assertEquals("The lower bound of 1000000000 and 1000000001 should be 1000000000",
    	1000000000, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a very small lower bound
    public void testSmallLowerBound() {
    	exampleRange = new Range(-1000000000, 1000000001);
    	assertEquals("The lower bound of -1000000000 and 1000000001 should be -1000000000",
    	-1000000000, exampleRange.getLowerBound(), .000000001d);
    }
    
    // getUpperBound() testing
    @Test
    // Tests a range with a negative upper bound
    public void testNegativeUpperBound() {
    	exampleRange = new Range(-2, 4);
        assertEquals("The upper bound of -2 and 4 should be 4",
        4, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a positive upper bound
    public void testPositiveUpperBound() {
    	exampleRange = new Range(3, 10);
        assertEquals("The upper bound of 3 and 10 should be 10",
        10, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same positive upper and lower bound
    public void testSamePositiveValuesUpperBound() {
    	exampleRange = new Range(1,1);
    	assertEquals("The upper bound of 1 and 1 should be 1",
    	1, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same negative upper and lower bound
    public void testSameNegativeValuesUpperBound() {
    	exampleRange = new Range(-1, -1);
    	assertEquals("The lower bound of -1 and -1 should be -1",
    	-1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a negative decimal upper bound
    public void testNegativeDecimalUpperBound() {
    	exampleRange = new Range(-4.45, -2.36);
        assertEquals("The upper bound of -2.36 and -4.45 should be -2.36",
        -2.36, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a positive decimal upper bound
    public void testPositiveDecimalUpperBound() {
    	exampleRange = new Range(3.09, 10.69);
        assertEquals("The upper bound of 3.09 and 10.69 should be 10.69",
        10.69, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same positive decimal upper and lower bound
    public void testSamePositiveValuesDecimalUpperBound() {
    	exampleRange = new Range(1.17, 1.17);
    	assertEquals("The upper bound of 1.17 and 1.17 should be 1.17",
    	1.17, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with the same negative decimal upper and lower bound
    public void testSameNegativeValuesDecimalUpperBound() {
    	exampleRange = new Range(-1.17, -1.17);
    	assertEquals("The upper bound of -1.17 and -1.17 should be -1.17",
    	-1.17, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a very large upper bound
    public void testLargeUpperBound() {
    	exampleRange = new Range(1000000000, 1000000001);
    	assertEquals("The upper bound of 1000000000 and 1000000001 should be 1000000001",
    	1000000001, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    // Tests a range with a very small upper bound
    public void testSmallUpperBound() {
    	exampleRange = new Range(-1000000001, -1000000000);
    	assertEquals("The upper bound of -1000000001 and -1000000000 should be -1000000000",
    	-1000000000, exampleRange.getUpperBound(), .000000001d);
    }
    

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
