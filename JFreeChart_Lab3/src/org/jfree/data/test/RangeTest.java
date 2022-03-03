package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange, exampleRangePositive, exampleRangeNegative, exampleRangeZero,exampleLargeRange, decimalRange, NaNRange;
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
    	NaNRange = new Range(Double.NaN,Double.NaN);
    }
    
    //	getCentralValue Method Testing    
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
    
    //	getLength method testing
    
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
    
    //	getLowerBound() testing
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
    
    //	getUpperBound() testing
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
    // Test for the range constructor, this time intentionally passing incompatible arguments.
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerGreaterThanUpper() {
    	exampleRange = new Range(1, -1);
    }
    // Tests for the intersects method
    @Test
    public void testIntersectsRangeLowerBound() {
    	assertTrue("The range -15,-5 should intersect with the range -10,10",
    	exampleRange.intersects(-15,5));
    }
    @Test
    public void testIntersectsRangeIntersectsUpperBound() {
    	assertTrue("The range 5,15 should intersect with the range -10,10",
    	exampleRange.intersects(5,15));
    }
    @Test
    public void testIntersectsRangeBelow() {
    	assertFalse("The range -15,-11 should not intersect with the range -10,10",
    	exampleRange.intersects(-15,-11));
    }
    @Test
    public void testIntersectsRangeAbove() {
    	assertFalse("The range 11,15 should not intersect with the range -10,10",
    	exampleRange.intersects(11,15));
    }
    @Test
    public void testIntersectsRangeArgsSwapped() {
    	assertFalse("The range 5,-15 is an unsupported argument value",
    	exampleRange.intersects(5,-15));
    }
    @Test
    public void testIntersectsOverload() {
    	Range intersectsRange = new Range(-15,-5);
    	assertTrue("The range -15,-5 should intersect with the range -10,10",
    	exampleRange.intersects(intersectsRange));
    }
    
    //	Tests for the constrain method
    @Test
    public void testConstrainValueInRange() {
    	assertEquals("The value 0 is in the range -10,10",
    	0, exampleRange.constrain(0),.000000001d);
    }
    @Test
    public void testConstrainValueAboveRange() {
    	assertEquals("The value 10 is closest to the value given: 11",
    	10, exampleRange.constrain(11),.000000001d);
    }
    @Test
    public void testConstrainValueBelowRange() {
    	assertEquals("The value -10 is closest to the value given: -11",
    	-10, exampleRange.constrain(-11),.000000001d);
    }
    
    //	Tests for combine method
    @Test
    public void testCombineRange2Null() {
    	assertTrue("If range 2 is null range 1 is returned",
		exampleRange.equals(Range.combine(exampleRange,null)));
    }
    @Test
    public void testCombineRange1Null() {
    	assertTrue("If range 1 is null range 2 is returned",
		exampleRange.equals(Range.combine(null,exampleRange)));
    }
    @Test
    public void testCombine2ValidRanges() {
    	Range combinedRange = new Range(-10,100);
    	assertTrue("Expects a range from -10 to 100",
		combinedRange.equals(Range.combine(exampleRangePositive,exampleRange)));
    }
    @Test
    public void testCombineBothNull() {
    	assertNull("If range 2 is null range 1 is returned",
		Range.combine(null,null));
    }
    
    //Tests for combinedIgnoringNaN
    @Test
    public void testCombineIgnoringNaNRange2Null() {
    	assertTrue("If range 2 is null range 1 is returned",
		exampleRange.equals(Range.combineIgnoringNaN(exampleRange,null)));
    }
    @Test
    public void testCombineIgnoringNaNRange1Null() {
    	assertTrue("If range 1 is null range 2 is returned",
		exampleRange.equals(Range.combineIgnoringNaN(null,exampleRange)));
    }
    @Test
    public void testCombineIgnoringNaNRange1NullRange2NaN() {
    	assertNull("If range 1 is null and range 2 NaN, null returned",
		Range.combineIgnoringNaN(null,NaNRange));
    }
    @Test
    public void testCombineIgnoringNaNBothNull() {
    	assertNull("If range 1 is null and range 2 null, null returned",
		Range.combineIgnoringNaN(null,null));
    }
    @Test
    public void testCombineIgnoringNaNRange1NaNRange2Null() {
    	assertNull("If range 1 is NaN and range 2 null, null returned",
		Range.combineIgnoringNaN(NaNRange,null));
    }
    
    @Test
    public void testCombineIgnoringNaNBothValidRanges() {
    	Range combinedRange = new Range(-10,100);
    	assertTrue("Expects combined range of -10, 100",
		combinedRange.equals(Range.combineIgnoringNaN(exampleRange,exampleRangePositive)));
    }
    @Test
    public void testCombineIgnoringNaNRange1NaNRange2Valid() {
    	assertTrue("If range 1 is NaN and range 2 valid, range 2 returned",
    	exampleRange.equals(Range.combineIgnoringNaN(NaNRange,exampleRange)));
    }
    @Test
    public void testCombineIgnoringNaNRange1ValidRange2NaN() {
    	assertTrue("If range 1 is valid and range 2 NaN, range 1 returned",
    	exampleRange.equals(Range.combineIgnoringNaN(exampleRange,NaNRange)));
    }
    @Test
    public void testCombineIgnoringNaNBothNaN() {
    	assertNull("If range 1 is valid and range 2 NaN, null returned",
		Range.combineIgnoringNaN(NaNRange,NaNRange));
    }
    
    // tests for expandToInclude
    @Test
    public void testExpandToIncludeRangeNull() {
    	Range newRange = new Range(0,0);
    	assertTrue("If the range is Null the returned range will be zero width with upper and lower bound equal to the passed value",
    	newRange.equals(Range.expandToInclude(null,0)));
    }
    @Test
    public void testExpandToIncludeValueInRange() {
    	assertTrue("value 0 is in example range so return original range",
    	exampleRange.equals(Range.expandToInclude(exampleRange,0)));
    }
    @Test
    public void testExpandToIncludeValueBelowRange() {
    	Range newRange = new Range(-15,10);
    	assertTrue("the value -15 is below range so return expanded range -15 to 10",
    	newRange.equals(Range.expandToInclude(exampleRange,-15)));
    }
    @Test
    public void testExpandToIncludeValueAboveRange() {
    	Range newRange = new Range(-10,15);
    	assertTrue("the value 15 is aboverange so return expanded range -10 to 15",
    	newRange.equals(Range.expandToInclude(exampleRange,15)));
    }
    
    // tests for expand
    @Test
    public void testExpandIncreaseBothRanges() {
    	Range newRange = new Range(-15,20);
    	assertTrue("Expanding the range -10, 10 by 25% in both directiosn shoudl yield -15, 15",
    	newRange.equals(Range.expand(exampleRange,0.25,0.5)));
    }
    @Test
    public void testExpandReverseLowerAndUpper() {
    	Range newRange = new Range(-10,10);
    	assertTrue("Expanding each range by -100% should swap the lower and upper and result in the same range -10, 10",
    	newRange.equals(Range.expand(exampleRange,-1,-1)));
    }
    
    // tests for shift
    @Test
    public void testShiftPositiveDelta() {
    	Range newRange = new Range(-5,15);
    	assertTrue("Shifting a positive delta of 5 should yield a range of -5, 15",
    	newRange.equals(Range.shift(exampleRange, 5)));
    }
    @Test
    public void testShiftDeltaGreaterThanLowerBoundToZero() {
    	Range newRange = new Range(0,25);
    	assertTrue("Shifting a positive delta of 15 with no zero crossing should yield a range of -5, 15",
    	newRange.equals(Range.shift(exampleRange, 15)));
    }
    @Test
    public void testShiftDeltaGreaterThanUpperBoundToZero() {
    	Range newRange = new Range(-25,0);
    	assertTrue("Shifting a positive of 15 with no zero crossing should yield a range of -5, 15",
    	newRange.equals(Range.shift(exampleRange, -15)));
    }
    @Test
    public void testShiftRangeWithZeroBound() {
    	Range newRange = new Range(10,10);
    	assertTrue("Shifting a positive of 5 should yield a range of -5, 15",
    	newRange.equals(Range.shift(exampleRangeZero, 10)));
    }
    @Test
    public void testShiftAllowZeroCrossing() {
    	Range newRange = new Range(5,25);
    	assertTrue("Shifting a positive of 15 with zero crossing enabled should yield a range of 5, 25",
    	newRange.equals(Range.shift(exampleRange, 15, true)));
    }
    
    
    //test for scale
    @Test(expected = IllegalArgumentException.class)
    public void testScaleNegativeScale() {
    	Range.scale(exampleRange,-2);
    }
    @Test
    public void testScalePositiveScale() {
    	Range newRange = new Range(-20,20);
    	assertTrue("scaling a range from -10,10 by 2 should yield a range of -20,20",
    	newRange.equals(Range.scale(exampleRange,2)));
    }
    
    //tests for equals
    @Test
    public void testEqualsObjectNotARange() {
    	String str = "TEST";
    	assertFalse("trying to equate a range to a string returns false",
		exampleRange.equals(str));
    }
    @Test
    public void testEqualsUpperRangeNotEquals() {
    	Range newRange = new Range(-10,100);
    	assertFalse("the upper bound of 100 is not equal to the upper bound of 10",
		exampleRange.equals(newRange));
	}
    @Test
    public void testEqualsLowerRangeNotEquals() {
    	Range newRange = new Range(-100,10);
    	assertFalse("the lower bound of -100 is not equal to the lower bound of -10",
		exampleRange.equals(newRange));
	}
    
    //tests for isNaNRange
    @Test
    public void testIsNaNRangeLowerIsNaN() {
    	Range newRange = new Range(Double.NaN,0);
    	assertFalse("The range NaN,0 should return false for isNaNRange",
    	newRange.isNaNRange());
    }
    @Test
    public void testIsNaNRangeUpperIsNaN() {
    	Range newRange = new Range(0,Double.NaN);
    	assertFalse("The range 0,NaN should return false for isNaNRange",
    	newRange.isNaNRange());
    }
    @Test
    public void testIsNaNRangeBothNaN() {
    	assertTrue("The range NaN,NaN should return true for isNaNRange",
    	NaNRange.isNaNRange());
    }
    @Test
    public void testIsNaNRangeValidRange() {
    	assertFalse("The range -10,10 should return false for isNaNRange",
    	exampleRange.isNaNRange());
    }
    
    // test toString method
    @Test
    public void testToString() {
    	String str = "Range[-10.0,10.0]";
    	assertTrue("The range -10.0,10.0 returns the string Range[-10.0,10.0]",
    	str.equals(exampleRange.toString()));
    }
      
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
