package org.jfree.data.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import junit.framework.TestCase;
import org.junit.*;

public class DataUtilitiesTest  {
	private Values2D values2D;
	private static final double EPSILON = 0.0001;
	
	@Before
	public void setUp() {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}
	
	@After
	public void tearDown() {
		values2D = null;
	}

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testgetCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 6.0);
		keyvalues.addValue((Comparable) 1.0, 11.0);
		keyvalues.addValue((Comparable) 2.0, 3.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages((KeyedValues) keyvalues);
	}
	
	@Test
    public void testValidDataTableValidColumnIndex() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, "Row1", "Column1");
        data.addValue(2, "Row2", "Column1");
        data.addValue(3, "Row3", "Column1");
        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals("Unexpected sum", 6.0, result, 0.0000001d);
    }

	@Test(expected = NullPointerException.class)
	public void testNullDataTable() {
	    Values2D data = null;
	    int column = 0;
	    DataUtilities.calculateColumnTotal(data, column);
	}

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidColumnIndexNegative() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        DataUtilities.calculateColumnTotal(data, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidColumnIndexGreaterThanMax() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        DataUtilities.calculateColumnTotal(data, Integer.MAX_VALUE);
    }

    @Test
    public void testDataTableWithOneValue() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, "Row1", "Column1");
        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals("Unexpected sum", 1.0, result, 0.0000001d);
    }

    @Test
    public void testEmptyDataTable() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals("Unexpected sum", 0.0, result, 0.0000001d);
    }

    @Test
    public void testColumnIndexZero() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, "Row1", "Column1");
        data.addValue(2, "Row2", "Column2");
        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals("Unexpected sum", 3.0, result, 0.0000001d);
    }

    @Test
    public void testColumnIndexMaxColumnIndex() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, "Row1", "Column1");
        data.addValue(2, "Row2", "Column2");
        double result = DataUtilities.calculateColumnTotal(data, 1);
        assertEquals("Unexpected sum", 2.0, result, 0.0000001d);
    }
	
    @Test
    public void testValidDataWithOneDoubleValue(){
    double[] data = {5.0};
    Number[] expected = {5.0};
    assertArrayEquals("Unexpected array of Number objects", expected, DataUtilities.createNumberArray(data));
    }

    @Test
    public void testValidDataWithMultipleDoubleValues(){
    double[] data = {2.0, 4.0, 6.0};
    Number[] expected = {2.0, 4.0, 6.0};
    assertArrayEquals("Unexpected array of Number objects", expected, DataUtilities.createNumberArray(data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullData(){
    double[] data = null;
    DataUtilities.createNumberArray(data);
    }

    @Test
    public void testEmptyData(){
    double[] data = {};
    Number[] expected = {};
    assertArrayEquals("Unexpected array of Number objects", expected, DataUtilities.createNumberArray(data));
    }

    @Test
    public void testValidDataWithTwoDoubleValues(){
    double[] data = {3.0, 5.0};
    Number[] expected = {3.0, 5.0};
    assertArrayEquals("Unexpected array of Number objects", expected, DataUtilities.createNumberArray(data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDataWithMaximumAllowedValues() {
        // create a 2D array with maximum allowed values
        double[][] data = new double[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                data[i][j] = Double.MAX_VALUE;
            }
        }
        
        DataUtilities.createNumberArray2D(data);
    }
    
    @Test
    public void testCreateNumberArray2D_valid_oneElement() {
        double[][] input = {{1.0}};
        Number[][] expected = {{1.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArray2D_valid_multipleElements() {
        double[][] input = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArray2D_invalid_nullInput() {
        double[][] input = null;
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray2D(input);
        });
    }

    @Test
    public void testCreateNumberArray2D_boundary_minElements() {
        double[][] input = {{1.0}};
        Number[][] expected = {{1.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expected, result);
    }

    @Test(timeout=5000)
    public void testCreateNumberArray2D_boundary_maxElements() {
        double[][] data = new double[1000][1000];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = i * j * 0.5;
            }
        }
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull(result);
        assertEquals(result.length, data.length);
        assertEquals(result[0].length, data[0].length);
        assertEquals(result[500][500].doubleValue(), 125000.0, EPSILON);
    }

 // Test case for valid data - KeyedValues instance with one key-value pair
    @Test
    public void testGetCumulativePercentages_OneKeyValuePair() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue("0", 0.3125);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(expected.getValue("0"), result.getValue("0"));
    }

    // Test case for valid data - KeyedValues instance with multiple key-value pairs
    @Test
    public void testGetCumulativePercentages_MultipleKeyValuePairs() {
    	DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        data.addValue("1", 9);
        data.addValue("2", 2);
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue("0", 0.3125);
        expected.addValue("1", 0.875);
        expected.addValue("2", 1.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(expected.getValue("0"), result.getValue("0"));
        assertEquals(expected.getValue("1"), result.getValue("1"));
        assertEquals(expected.getValue("2"), result.getValue("2"));
    }

    // Test case for invalid data - null KeyedValues instance
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_NullData() {
        KeyedValues data = null;
        DataUtilities.getCumulativePercentages(data);
    }

    // Test case for invalid data - empty KeyedValues instance
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_EmptyData() {
        KeyedValues data = new DefaultKeyedValues();
        DataUtilities.getCumulativePercentages(data);
    }
    
    
    
}