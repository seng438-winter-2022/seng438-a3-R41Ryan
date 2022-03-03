package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.jfree.data.*;
import org.jmock.*;
import org.jmock.Expectations;

import org.junit.Test;

public class DataUtilitiesTest {
	Mockery mockingContext;

	    @Before
	    public void setUp() throws Exception {
		mockingContext = new Mockery();
	    }


	    @Test
	    //This tests for properly calculating a row total
	    public void calculateRowTotalForTwoValues() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(2));
			one(values).getValue(0, 0);
			will(returnValue(7.5));
			one(values).getValue(0, 1);
			will(returnValue(2.5));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 10.0, .000000001d);
	    }


	    @Test
	    //This tests for calculating row total when there is no table
	    public void calculateRowTotalForNoTable() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(0));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 0, .000000001d);
	    }

	    @Test
	    //This tests for calculating a row with a zero value
	    public void calculateRowTotalWithOneZeroValue() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(0, 0);
			will(returnValue(0));
			one(values).getValue(0, 1);
			will(returnValue(2.5));
			one(values).getValue(0, 2);
			will(returnValue(5));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 7.5, .000000001d);
	    }

	    @Test
	    //This tests for calculating a row when the row only contains zero values
	    public void calculateRowTotalForZeroValues() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(0, 0);
			will(returnValue(0));
			one(values).getValue(0, 1);
			will(returnValue(0));
			one(values).getValue(0, 2);
			will(returnValue(0));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 0, .000000001d);
	    }

	    @Test
	    //This tests for calculating a row with a null value
	    public void calculateRowTotalWithOneNullValue() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(0, 0);
			will(returnValue(null));
			one(values).getValue(0, 1);
			will(returnValue(2.5));
			one(values).getValue(0, 2);
			will(returnValue(3));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 5.5, .000000001d);
	    }

	    @Test
	    //This tests for calculating a row with only null values
	    public void calculateRowTotalForNullValues() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(0, 0);
			will(returnValue(null));
			one(values).getValue(0, 1);
			will(returnValue(null));
			one(values).getValue(0, 2);
			will(returnValue(null));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 0);
		//Assert
		assertEquals(result, 0, .000000001d);
	    }

	    @Test(expected = IndexOutOfBoundsException.class)
	    //This tests for whether calculating a positive out of bounds row will throw an exception
	    public void calculateRowTotalForPositiveRowWhichDoesNotExist() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(3, 0);
			will(returnValue(null));
			one(values).getValue(3, 1);
			will(returnValue(null));
			one(values).getValue(3, 2);
			will(returnValue(null));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, 3);
		//Assert
		assertEquals(result, 0, .000000001d);

	    }

	    @Test(expected = IndexOutOfBoundsException.class)
	    //This tests for whether calculating a negative out of bounds row will throw an exception
	    public void calculateRowTotalForNegativeRowWhichDoesNotExist() {
		//Arrange
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(3));
			one(values).getValue(-2, 0);
			will(returnValue(null));
			one(values).getValue(-2, 1);
			will(returnValue(null));
			one(values).getValue(-2, 2);
			will(returnValue(null));
		    }
		});
		//Act
		double result = DataUtilities.calculateRowTotal(values, -2);
		//Assert
		assertEquals(result, 0, .000000001d);

	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    // This test the method with a null parameter, which is not permitted.
	    public void calculateRowTotalNullParamter() {
			double results = DataUtilities.calculateColumnTotal(null, 0);
			assertEquals(results, 4.75, 0.0000001d);
		}
	
		@Test
		// This tests to see if the method works with row 0 in the positive bound
 		public void testCalculateRowValidColumns() {
			Mockery context = new Mockery();
			final Values2D values = context.mock(Values2D.class);
			context.checking(new Expectations() {
				{
					one(values).getColumnCount();
					will(returnValue(5));
					one(values).getValue(0, 0);
					will(returnValue(2));
					one(values).getValue(0, 1);
					will(returnValue(3.75));
					one(values).getValue(0, 2);
					will(returnValue(-1));
					one(values).getValue(0, 3);
					will(returnValue(-0.35));
					one(values).getValue(0, 4);
					will(returnValue(-1.5));
				}
			});
			int[] validColumns = {0, 1, 2};
			double results = DataUtilities.calculateRowTotal(values, 0, validColumns);
			assertEquals(results, 4.75, 0.0000001d);
		}
	    
	    @Test(expected = IndexOutOfBoundsException.class)
		// This tests to see if the method works when array of columns is invalid
 		public void testCalculateRowInvalidNumberOfColumns() {
			Mockery context = new Mockery();
			final Values2D values = context.mock(Values2D.class);
			context.checking(new Expectations() {
				{
					one(values).getColumnCount();
					will(returnValue(5));
					one(values).getValue(0, 0);
					will(returnValue(null));
				}
			});
			int[] invalidColumns = {9};
			double results = DataUtilities.calculateRowTotal(values, 0, invalidColumns);
			assertEquals(results, 0, .000000001d);
		}
	    
	   	 @Test
		// This tests to see if the method works when one of the values is null
 		public void testCalculateRowValidColumnsNullValue() {
			Mockery context = new Mockery();
			final Values2D values = context.mock(Values2D.class);
			context.checking(new Expectations() {
				{
					one(values).getColumnCount();
					will(returnValue(5));
					one(values).getValue(0, 0);
					will(returnValue(2));
					one(values).getValue(0, 1);
					will(returnValue(null));
					one(values).getValue(0, 2);
					will(returnValue(-1));
					one(values).getValue(0, 3);
					will(returnValue(-0.35));
					one(values).getValue(0, 4);
					will(returnValue(-1.5));
				}
			});
			int[] validColumns = {0, 1, 2};
			double results = DataUtilities.calculateRowTotal(values, 0, validColumns);
			assertEquals(results, 1, 0.0000001d);
		}
	    
	    @Test
	    //This test is for checking if two equal arrays are equal.
	    public void EqualArrays() {
	       //Arrange
		double[][] arrayOne = {	{10.5, 20.24},
								{4.2, 14.52} };

		double[][] arrayTwo = {	{10.5, 20.24},
									{4.2, 14.52} };
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertTrue("Arrays should be equal",result);
	    }

	    @Test
	    //This test is for checking if two non equal arrays are equal.
	    public void NonEqualArrays() {
	       //Arrange
		double[][] arrayOne = {	{10.5, 20.24},
								{4.2, 14.52} };

		double[][] arrayTwo = {	{20.5, 25.24},
									{4.2, 14.92} };
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertFalse("Arrays should not be equal",result);
	    }

	    @Test
	    //This tests for two non-equal arrays due to negative values
	    public void NonEqualArraysWithNegativeValues() {
	       //Arrange
		double[][] arrayOne = {	{-10.5, 20.24},
								{4.2, 14.52} };

		double[][] arrayTwo = {	{10.5, 20.24},
									{4.2, 14.52} };
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertFalse("Arrays should not be equal due to one array with negative values and second array with only positive values",result);
	    }

	    @Test
	    //This tests for two equal arrays, both with empty elements
	    public void EqualArraysWithEmptyElements() {
	       //Arrange
		double[][] arrayOne = new double [2][2];

		double[][] arrayTwo = new double [2][2];
		arrayOne[0][0] = 12.4;
		arrayTwo[0][0] = 12.4;
		arrayOne[1][1] = 42.7;
		arrayTwo[1][1] = 42.7;
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertTrue("Arrays should be equal with both having same empty elements",result);
	    }

	    @Test
	    //This tests for two equal arrays, both null
	    public void EqualArraysWithNullArrays() {
	       //Arrange
		double[][] arrayOne = null;

		double[][] arrayTwo = null;
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertTrue("Arrays should be equal due to both being null arrays",result);
	    }

	    @Test
	    //This tests for equal arrays, both with empty arrays
	    public void EqualArraysWithEmptyArrays() {
	       //Arrange
		double[][] arrayOne = new double [3][3];

		double[][] arrayTwo = new double [3][3];
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertTrue("Arrays should be equal due to both being empty arrays",result);
	    }

	    @Test
	    //This tests for non equal arrays, both with differing row sizes
	    public void NonEqualArraysOfDifferentRowSizes() {
	       //Arrange
		double[][] arrayOne = {	{10.5, 20.24},
								{4.2, 14.52} };

		double[][] arrayTwo = new double [3][2];
		arrayTwo[0][0] = 10.5;
		arrayTwo[0][1] = 20.24;
		arrayTwo[1][0] = 4.2;
		arrayTwo[1][1] = 14.52;
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertFalse("Arrays should not be equal due to different row sizes",result);
	    }

	    @Test
	    //This tests for non equal arrays, both with differing row sizes
	    public void NonEqualArraysOfDifferentColumnSizes() {
	       //Arrange
		double[][] arrayOne = {	{10.5, 20.24},
								{4.2, 14.52} };

		double[][] arrayTwo = new double [2][3];
		arrayTwo[0][0] = 10.5;
		arrayTwo[0][1] = 20.24;
		arrayTwo[1][0] = 4.2;
		arrayTwo[1][1] = 14.52;
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertFalse("Arrays should not be equal due to different column sizes",result);
	    }

	    @Test
	    //This tests for equal arrays, both with negative values
	    public void EqualArraysWithNegativeValues() {
	       //Arrange
		double[][] arrayOne = {	{-10.5, 20.24},
								{4.2, -14.52} };

		double[][] arrayTwo = {	{-10.5, 20.24},
									{4.2, -14.52} };
	       //Act
		boolean result = DataUtilities.equal(arrayOne, arrayTwo);
	       //Assert
		assertTrue("Arrays should be equal with negative values",result);
	    }
	
	    @Test
	    //This tests for when the first array is null while the second array is not null
	    public void NonEqualArraysWithFirstArrayNullSecondArrayNotNull() {
	    	   //Arrange
			double[][] arrayOne = null;
			double[][] arrayTwo = {	{-10.5, 20.24},
									{4.2, -14.52} };
		       //Act
			boolean result = DataUtilities.equal(arrayOne, arrayTwo);
		       //Assert
			assertFalse("Arrays should not be equal due to first array being null and second array not being null",result);
	    }
	    
	    @Test
	    //This tests for when the first array is not null while the second array is null
	    public void NonEqualArraysWithFirstArrayNotNullSecondArrayNull() {
	    	   //Arrange
			double[][] arrayOne = {	{-10.5, 20.24},
									{4.2, -14.52} };
			double[][] arrayTwo = null;
		       //Act
			boolean result = DataUtilities.equal(arrayOne, arrayTwo);
		       //Assert
			assertFalse("Arrays should not be equal due to first array being not null and second array being null",result);
	    }
	
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
	
	@Test(expected = IllegalArgumentException.class)
	// This tests to see if an exception is thrown when the method is given a null parameter, which is not permitted.
	public void testCalculateColumnNullParameter() {
		double results = DataUtilities.calculateColumnTotal(null, 0);
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
	
	@Test(expected = IllegalArgumentException.class)
	// This tests the method using null values, which are not permitted. Thus, it should throw an illegal argument exception.
	public void testCreateNumberArrayNullParameter() {
		Number[] results = DataUtilities.createNumberArray(null);
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
	
	//clone() method tests
	@Test
	//Tests the clone method with a full 2D array
	public void testFullClone() {
		double[][] original = {{10.5, 20.24}, {4.2, 14.52}};
		double[][] clone = DataUtilities.clone(original);
		boolean result = DataUtilities.equal(original, clone);
	       //Assert
		assertTrue("Arrays should be equal", result);
	}
	
	@Test
	//Tests the clone method with a 2D array with a null entry
	public void testNullClone() {
		double[][] original = {{10.5, 20.24}, null, {4.2, 14.52}};
		double[][] clone = DataUtilities.clone(original);
		boolean result = DataUtilities.equal(original, clone);
	       //Assert
		assertTrue("Arrays should be equal", result);
	}
	
	//createNumberArray2D() test
	@Test
	//Tests the method using positive decimals
	public void testCreateNumberArray2D() {
		double[][] testArray = {{2.3, 0.4, 1.0}, {5.4}, {0.001, 8.20}};
		Number[][] result = DataUtilities.createNumberArray2D(testArray);
		assertEquals(result[0][0].doubleValue(), 2.3, 0.0000001d);
		assertEquals(result[0][1].doubleValue(), 0.4, 0.0000001d);
		assertEquals(result[0][2].doubleValue(), 1.0, 0.0000001d);
		assertEquals(result[1][0].doubleValue(), 5.4, 0.0000001d);
		assertEquals(result[2][0].doubleValue(), 0.001, 0.0000001d);
		assertEquals(result[2][1].doubleValue(), 8.20, 0.0000001d);
	}
}
