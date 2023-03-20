package org.jfree.data.test;

import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
	private Range rangeObjectUnderTest;
	
	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}
	
	@Test
    public void testIntersects_ValidLowerValidUpperIntersects() {
        Range range = new Range(2.0, 20.0);
        assertTrue(rangeObjectUnderTest.intersects(range.getLowerBound(), range.getUpperBound()));
    }

    @Test
    public void testIntersects_ValidLowerValidUpperNotIntersects() {
        Range range = new Range(11.0, 20.0);
        assertFalse(rangeObjectUnderTest.intersects(range.getLowerBound(), range.getUpperBound()));
    }

    @Test
    public void testIntersects_ValidLowerValidUpperEqualsToLower() {
        Range range = new Range(1.0, 2.0);
        assertTrue(rangeObjectUnderTest.intersects(range.getLowerBound(), range.getUpperBound()));
    }

    @Test
    public void testIntersects_ValidLowerValidUpperEqualsToUpper() {
        Range range = new Range(1.0, 10.0);
        assertTrue(rangeObjectUnderTest.intersects(range.getLowerBound(), range.getUpperBound()));
    }

    @Test
    public void testIntersects_ValidLowerValidUpperContainsRange() {
        Range range = new Range(1.0, 11.0);
        assertTrue(rangeObjectUnderTest.intersects(range.getLowerBound(), range.getUpperBound()));
    }

    @Test
    public void testIntersects_MinimumDoubleValueForBothLowerAndUpper() {
        assertFalse(rangeObjectUnderTest.intersects(Double.MIN_VALUE, Double.MIN_VALUE));
    }

    @Test
    public void testIntersects_MaximumDoubleValueForBothLowerAndUpper() {
        assertFalse(rangeObjectUnderTest.intersects(Double.MAX_VALUE, Double.MAX_VALUE));
    }

    @Test
    public void testIntersects_CriticalValueForLowerValidUpper() {
        assertFalse(rangeObjectUnderTest.intersects(Double.NaN, 6.0));
    }

    @Test
    public void testIntersects_ValidLowerCriticalValueForUpper() {
        assertFalse(rangeObjectUnderTest.intersects(6.0, Double.NaN));
    }

    @Test
    public void testIntersects_CriticalValueForBothLowerAndUpper() {
        assertFalse(rangeObjectUnderTest.intersects(Double.NaN, Double.NaN));
    }

    @Test
    public void testIntersects_MinimumValueForLowerMaximumValueForUpper() {
        assertTrue(rangeObjectUnderTest.intersects(Double.MIN_VALUE, Double.MAX_VALUE));
    }

    @Test
    public void testIntersects_MaximumValueForLowerMinimumValueForUpper() {
        assertFalse(rangeObjectUnderTest.intersects(Double.MAX_VALUE, Double.MIN_VALUE));
    }
    
    @Test
    public void testConstrain_ValidValueLessThanLowerBound() {
        double value = -1.0;
        double expected = 1.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValidValueWithinRange() {
        double value = 5.0;
        double expected = 5.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValidValueGreaterThanUpperBound() {
        double value = 12.0;
        double expected = 10.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValueEqualsLowerBound() {
        double value = 1.0;
        double expected = 1.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValueEqualsUpperBound() {
        double value = 10.0;
        double expected = 10.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValueEqualsLowerBoundMinusOne() {
        double value = 0.0;
        double expected = 1.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testConstrain_ValueEqualsUpperBoundPlusOne() {
        double value = 11.0;
        double expected = 10.0;
        double result = rangeObjectUnderTest.constrain(value);
        assertEquals(expected, result, 0.000000001d);
    }

    @Test
    public void testEquals_LowerMinusOne() {
        Range r = new Range(0, 10);
        assertFalse("Ranges with different lower bounds should not be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testEquals_LowerSameUpperSame() {
        Range r = new Range(1, 10);
        assertTrue("Ranges with the same bounds should be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testEquals_LowerPlusOne() {
        Range r = new Range(2, 10);
        assertFalse("Ranges with different lower bounds should not be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testEquals_UpperMinusOne() {
        Range r = new Range(1, 9);
        assertFalse("Ranges with different upper bounds should not be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testEquals_UpperSameLowerSame() {
        Range r = new Range(1, 10);
        assertTrue("Ranges with the same bounds should be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testEquals_UpperPlusOne() {
        Range r = new Range(1, 11);
        assertFalse("Ranges with different upper bounds should not be equal",
                    rangeObjectUnderTest.equals(r));
    }

    @Test
    public void testContains_validValue1() {
        Range range = new Range(1, 10);
        assertTrue(range.contains(1.0));
    }
    
    @Test
    public void testContains_validValue2() {
        Range range = new Range(1, 10);
        assertTrue(range.contains(5.5));
    }
    
    @Test
    public void testContains_validValue3() {
        Range range = new Range(1, 10);
        assertTrue(range.contains(10.0));
    }
    
    @Test
    public void testContains_invalidValue1() {
        Range range = new Range(1, 10);
        assertFalse(range.contains(-10));
    }
    
    @Test
    public void testContains_invalidValue2() {
        Range range = new Range(1, 10);
        assertFalse(range.contains(100));
    }
    
    @Test
    public void testContains_boundaryValue1() {
        Range range = new Range(1, 10);
        assertTrue(range.contains(1 - 0.001));
    }
    
    @Test
    public void testContains_boundaryValue2() {
        Range range = new Range(1, 10);
        assertTrue(range.contains(10 + 0.001));
    }
    
    @Test
    public void testGetCentralValue_case1() {
        Range range = new Range(0, 0);
        double expected = 0;
        double actual = range.getCentralValue();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetCentralValue_case2() {
        Range range = new Range(1, 10);
        double expected = 5.5;
        double actual = range.getCentralValue();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetCentralValue_case3() {
        Range range = new Range(-10, -1);
        double expected = -5.5;
        double actual = range.getCentralValue();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetCentralValue_case4() {
        Range range = new Range(-10, 10);
        double expected = 0;
        double actual = range.getCentralValue();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetCentralValue_case5() {
        Range range = new Range(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        double actual = range.getCentralValue();
        assertTrue(Double.isNaN(actual));
    }

    @Test
    public void testGetCentralValue_case6() {
        Range range = new Range(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        double actual = range.getCentralValue();
        assertTrue(Double.isNaN(actual));
    }

    @Test
    public void testGetCentralValue_case7() {
        Range range = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        double actual = range.getCentralValue();
        assertTrue(Double.isNaN(actual));
    }
    
}
