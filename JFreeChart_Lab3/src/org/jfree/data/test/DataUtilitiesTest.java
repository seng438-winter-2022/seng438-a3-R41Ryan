package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.jfree.data.*;
import org.jmock.*;
import org.jmock.Expectations;

import org.junit.Test;

public class DataUtilitiesTest {
	
	@Test
	// This is just a basic test with positive values to see if it works as intended using column 0
	public void testBasicCalculateColumnTotal() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(7.5));
				one(values).getValue(1,0);
				will(returnValue(2.5));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(results, 10.0, 0.0000001d);
	}
	
	@Test
	// This tests to see if null values are ignored when calculating the total by adding a single null value in a column.
	public void testCalculateColumnTotalWithNull() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(7.5));
				one(values).getValue(1,0);
				will(returnValue(null));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(results, 7.5, 0.0000001d);
	}
	
	@Test
	// This tests to see if null values are ignored when calculating the total by having an entire column null
	public void testCalculateColumnTotalWithAllNulls() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(null));
				one(values).getValue(1,0);
				will(returnValue(null));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(results, 0, 0.0000001d);
	}
	
	@Test
	// This tests to see if the calculated value is 0 when there are no rows.
	public void testCalculateColumnTotalWithNoRows() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(0));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(results, 0, 0.0000001d);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	// This tests to see if an IndexOutOfBoundsException is thrown when entering the column -1
	public void testCalculateColumnTotalOutOfBoundsNegative() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, -1);
				will(returnValue(null));
				one(values).getValue(1, -1);
				will(returnValue(null));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, -1);
		assertEquals(results, 0, 0.0000001d);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	// This tests to see if an IndexOutOfBoundsException is thrown when entering a column greater than the maximum index allows (in this case, column 2)
	public void testCalculateColumnTotalOutOfBoundsPositive() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 2);
				will(returnValue(null));
				one(values).getValue(1, 2);
				will(returnValue(null));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 2);
		assertEquals(results, 0, 0.0000001d);
	}
	
	@Test
	// This tests to see if the method still works as intended when selecting the positive bound (in this case, column 1)
	public void testCalculateColumnPositiveBound() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 1);
				will(returnValue(-1));
				one(values).getValue(1, 1);
				will(returnValue(-0.35));
			}
		});
		double results = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(results, -1.35, 0.0000001d);
	}
	
	@Test
	// This tests to see if the method still works as intended when selecting the positive bound (in this case, column 1)
	public void testCalculateColumnValidRows() {
		Mockery context = new Mockery();
		final Values2D values = context.mock(Values2D.class);
		context.checking(new org.jmock.Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 0);
				will(returnValue(2));
				one(values).getValue(1, 0);
				will(returnValue(3.75));
				one(values).getValue(2, 0);
				will(returnValue(-1));
				one(values).getValue(3, 0);
				will(returnValue(-0.35));
				one(values).getValue(4, 0);
				will(returnValue(-1.5));
			}
		});
		int[] validRows = {0, 1, 2};
		double results = DataUtilities.calculateColumnTotal(values, 0, validRows);
		assertEquals(results, 4.75, 0.0000001d);
	}
	
	@Test
	// This tests the method using positive integers and decimals
	public void testCreateNumberArrayPositiveValues() {
		double[] testArray = {1.5, 2.244, 3.325, 2, 5, 23.4, 2.00001, 0};
		Number[] results = DataUtilities.createNumberArray(testArray);
		assertEquals(results[0].doubleValue(), 1.5, 0.0000001d);
		assertEquals(results[1].doubleValue(), 2.244, 0.0000001d);
		assertEquals(results[2].doubleValue(), 3.325, 0.0000001d);
		assertEquals(results[3].doubleValue(), 2, 0.0000001d);
		assertEquals(results[4].doubleValue(), 5, 0.0000001d);
		assertEquals(results[5].doubleValue(), 23.4, 0.0000001d);
		assertEquals(results[6].doubleValue(), 2.00001, 0.0000001d);
		assertEquals(results[7].doubleValue(), 0, 0.0000001d);
	}
	
	@Test
	// This tests the method using negative integers and decimals
	public void testCreateNumberArrayNegativeValues() {
		double[] testArray = {-1.5, -2.244, -3.325, -2, -5, -23.4, -2.00001, 0};
		Number[] results = DataUtilities.createNumberArray(testArray);
		assertEquals(results[0].doubleValue(), -1.5, 0.0000001d);
		assertEquals(results[1].doubleValue(), -2.244, 0.0000001d);
		assertEquals(results[2].doubleValue(), -3.325, 0.0000001d);
		assertEquals(results[3].doubleValue(), -2, 0.0000001d);
		assertEquals(results[4].doubleValue(), -5, 0.0000001d);
		assertEquals(results[5].doubleValue(), -23.4, 0.0000001d);
		assertEquals(results[6].doubleValue(), -2.00001, 0.0000001d);
		assertEquals(results[7].doubleValue(), -0, 0.0000001d);
	}
	
	@Test
	// This tests the method using only positive integers
	public void testGetCumulativePercentagesPositiveIntegers() {
		Mockery context = new Mockery();
		final KeyedValues values = context.mock(KeyedValues.class);
		context.checking(new org.jmock.Expectations() {
			{
				allowing(values).getItemCount();
				will(returnValue(3));
				allowing(values).getKey(0);
				will(returnValue("2010 Plagiarism Incidents"));
				allowing(values).getKey(1);
				will(returnValue("2011 Plagiarism Incidents"));
				allowing(values).getKey(2);
				will(returnValue("2012 Plagiarism Incidents"));
				allowing(values).getIndex("2010 Plagiarism Incidents");
				will(returnValue(0));
				allowing(values).getIndex("2011 Plagiarism Incidents");
				will(returnValue(1));
				allowing(values).getIndex("2012 Plagiarism Incidents");
				will(returnValue(2));
				allowing(values).getValue(0);
				will(returnValue(10));
				allowing(values).getValue(1);
				will(returnValue(5));
				allowing(values).getValue(2);
				will(returnValue(5));
			}
		});
		KeyedValues results = DataUtilities.getCumulativePercentages(values);
		assertEquals(results.getValue(0).doubleValue(), 0.5, 0.001d);
		assertEquals(results.getValue(1).doubleValue(), 0.75, 0.001d);
		assertEquals(results.getValue(2).doubleValue(), 1.0, 0.001d);
	}
	
	@Test
	// This tests the method using doubles
	public void testGetCumulativePercentagesDoubles() {
		Mockery context = new Mockery();
		final KeyedValues values = context.mock(KeyedValues.class);
		context.checking(new org.jmock.Expectations() {
			{
				allowing(values).getItemCount();
				will(returnValue(3));
				allowing(values).getKey(0);
				will(returnValue("2010 Score"));
				allowing(values).getKey(1);
				will(returnValue("2011 Score"));
				allowing(values).getKey(2);
				will(returnValue("2012 Score"));
				allowing(values).getIndex("2010 Score");
				will(returnValue(0));
				allowing(values).getIndex("2011 Score");
				will(returnValue(1));
				allowing(values).getIndex("2012 Score");
				will(returnValue(2));
				allowing(values).getValue(0);
				will(returnValue(2234.643));
				allowing(values).getValue(1);
				will(returnValue(100.34));
				allowing(values).getValue(2);
				will(returnValue(3345));
			}
		});
		KeyedValues results = DataUtilities.getCumulativePercentages(values);
		assertEquals(results.getValue(0).doubleValue(), 0.3934, 0.001d);
		assertEquals(results.getValue(1).doubleValue(), 0.4111, 0.001d);
		assertEquals(results.getValue(2).doubleValue(), 1.0, 0.001d);
	}
	
	@Test
	// This tests the methods to see if it still works as intended with a negative integer.
	public void testGetCumulativePercentagesNegativeIntegers() {
		Mockery context = new Mockery();
		final KeyedValues values = context.mock(KeyedValues.class);
		context.checking(new org.jmock.Expectations() {
			{
				allowing(values).getItemCount();
				will(returnValue(3));
				allowing(values).getKey(0);
				will(returnValue("2010 Plagiarism Incidents"));
				allowing(values).getKey(1);
				will(returnValue("2011 Plagiarism Incidents"));
				allowing(values).getKey(2);
				will(returnValue("2012 Plagiarism Incidents"));
				allowing(values).getIndex("2010 Plagiarism Incidents");
				will(returnValue(0));
				allowing(values).getIndex("2011 Plagiarism Incidents");
				will(returnValue(1));
				allowing(values).getIndex("2012 Plagiarism Incidents");
				will(returnValue(2));
				allowing(values).getValue(0);
				will(returnValue(10));
				allowing(values).getValue(1);
				will(returnValue(5));
				allowing(values).getValue(2);
				will(returnValue(-5));
			}
		});
		KeyedValues results = DataUtilities.getCumulativePercentages(values);
		assertEquals(results.getValue(0).doubleValue(), 1.0, 0.001d);
		assertEquals(results.getValue(1).doubleValue(), 1.5, 0.001d);
		assertEquals(results.getValue(2).doubleValue(), 1.0, 0.001d);
	}
}
