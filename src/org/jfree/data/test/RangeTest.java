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
	
	
    @Test
    public void testCombine_NullRanges() {
        Range result = Range.combine(null, null);
        assertNull(result);
    }

    @Test
    public void testCombine_NullRange1() {
        Range range2 = new Range(1.0, 2.0);
        Range result = Range.combine(null, range2);
        assertEquals(range2, result);
    }

    @Test
    public void testCombine_NullRange2() {
        Range range1 = new Range(1.0, 2.0);
        Range result = Range.combine(range1, null);
        assertEquals(range1, result);
    }

    @Test
    public void testCombine_BothRanges() {
        Range range1 = new Range(1.0, 2.0);
        Range range2 = new Range(3.0, 4.0);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(1.0, 4.0);
        assertEquals(expected, result);
    }

    @Test
    public void testCombine_Range1LargerThanRange2() {
        Range range1 = new Range(3.0, 4.0);
        Range range2 = new Range(1.0, 2.0);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(1.0, 4.0);
        assertEquals(expected, result);
    }

    @Test
    public void testCombine_Range2LargerThanRange1() {
        Range range1 = new Range(1.0, 3.0);
        Range range2 = new Range(2.0, 4.0);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(1.0, 4.0);
        assertEquals(expected, result);
    }

    @Test
    public void testCombine_RangesOverlap() {
        Range range1 = new Range(1.0, 2.0);
        Range range2 = new Range(1.5, 3.0);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(1.0, 3.0);
        assertEquals(expected, result);
    }

    @Test
    public void testCombine_Range1LowerThanRange2() {
        Range range1 = new Range(1.0, 2.0);
        Range range2 = new Range(0.0, 1.5);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(0.0, 2.0);
        assertEquals(expected, result);
    }
    @Test
    public void testExpandToInclude_NullRange() {
        Range range = null;
        double value = 5.0;
        Range expected = new Range(value, value);
        Range actual = Range.expandToInclude(range, value);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExpandToInclude_ValueGreaterThanRange() {
        Range range = new Range(2.0, 6.0);
        double value = 8.0;
        Range expected = new Range(2.0, 8.0);
        Range actual = Range.expandToInclude(range, value);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExpandToInclude_ValueInsideRange() {
        Range range = new Range(2.0, 6.0);
        double value = 3.0;
        Range expected = new Range(2.0, 6.0);
        Range actual = Range.expandToInclude(range, value);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExpandToInclude_ValueOnLowerBound() {
        Range range = new Range(2.0, 6.0);
        double value = 2.0;
        Range expected = new Range(2.0, 6.0);
        Range actual = Range.expandToInclude(range, value);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testExpandToInclude_ValueOnUpperBound() {
        Range range = new Range(2.0, 6.0);
        Range expandedRange = Range.expandToInclude(range, 6.0);
        Assert.assertEquals("Unexpected expanded range", range, expandedRange);
    }

    @Test
    public void testExpandToInclude_ValueBelowLowerBound() {
        Range range = new Range(2.0, 6.0);
        Range expandedRange = Range.expandToInclude(range, -2.0);
        Range expectedRange = new Range(-2.0, 6.0);
        Assert.assertEquals("Unexpected expanded range", expectedRange, expandedRange);
    }

    @Test
    public void testExpandToInclude_ValueAboveUpperBound() {
        Range range = new Range(2.0, 6.0);
        Range expandedRange = Range.expandToInclude(range, 10.0);
        Range expectedRange = new Range(2.0, 10.0);
        Assert.assertEquals("Unexpected expanded range", expectedRange, expandedRange);
    }
    @Test
    public void testExpandWithMargins() {
        Range range = new Range(2, 6);
        double lowerMargin = 0.25;
        double upperMargin = 0.5;
        Range expected = new Range(1, 8);
        Range actual = Range.expand(range, lowerMargin, upperMargin);
        assertEquals(expected, actual);
    }
    @Test
    public void testExpandWithEqualMargins() {
        Range range = new Range(0, 10);
        double margin = 0.5;
        Range expected = new Range(-5, 15);
        Range actual = Range.expand(range, margin, margin);
        assertEquals(expected, actual);
    }
    @Test
    public void testExpandWithZeroMargins() {
        Range range = new Range(3, 7);
        double lowerMargin = 0;
        double upperMargin = 0;
        Range expected = new Range(3, 7);
        Range actual = Range.expand(range, lowerMargin, upperMargin);
        assertEquals(expected, actual);
    }
    @Test
    public void testExpandWithLargeMargins() {
        Range range = new Range(-5, 5);
        double margin = 0.75;
        Range expected = new Range(-15.0, 15.0);
        Range actual = Range.expand(range, margin, margin);
        assertEquals(expected, actual);
    }
    @Test
    public void testExpandRangeWithMargins() {
        Range originalRange = new Range(0.5, 3.5);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expectedRange = new Range(0.05, 3.95);

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        assertEquals(expectedRange, expandedRange);
    }

    @Test
    public void testExpandZeroLengthRange() {
        Range originalRange = new Range(0.0, 0.0);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expectedRange = new Range(0.0, 0.0);

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        assertEquals(expectedRange, expandedRange);
    }

    @Test
    public void testExpandInfiniteRange() {
        Range originalRange = new Range(Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expectedRange = new Range(Double.NEGATIVE_INFINITY, Double.MAX_VALUE);

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        assertEquals(expectedRange, expandedRange);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpandNullRange() {
        Range originalRange = null;
        double lowerMargin = 0.25;
        double upperMargin = 0.5;

        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);

        // exception should be thrown before reaching this point
        fail("Expected IllegalArgumentException but no exception was thrown.");
    }

    @Test
    public void testHashCodeForRangeWithSameLowerAndUpper() {
        Range range = new Range(0.0, 0.0);
        int expected = 31;
        int actual = range.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCodeForRangeWithDifferentLowerAndUpper() {
        Range range = new Range(1.0, 2.0);
        int expected = -535610400;
        int actual = range.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCodeForRangeWithNegativeLowerAndUpper() {
        Range range = new Range(-1.0, -2.0);
        int expected = 1894476323;
        int actual = range.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCodeWithNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        int expectedHashCode = 961;
        int actualHashCode = range.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void testHashCodeWithPositiveInfinity() {
        Range range = new Range(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        int expectedHashCode = 2146959367;
        int actualHashCode = range.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void testHashCodeWithNegativeInfinity() {
        Range range = new Range(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        int expectedHashCode = -1048575;
        int actualHashCode = range.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void testShiftWithPositiveDeltaAndZeroCrossingAllowed1() {
        Range base = new Range(0, 10);
        double delta = 5;
        boolean allowZeroCrossing = true;

        Range expected = new Range(5, 15);
        Range actual = Range.shift(base, delta, allowZeroCrossing);

        assertEquals(expected, actual);
    }

    @Test
    public void testShiftWithPositiveDeltaAndZeroCrossingNotAllowed() {
        Range base = new Range(-5, 5);
        double delta = 2;
        boolean allowZeroCrossing = true;

        Range expected = new Range(-3, 7);
        Range actual = Range.shift(base, delta, allowZeroCrossing);

        assertEquals(expected, actual);
    }

    @Test
    public void testShiftWithPositiveDeltaAndZeroCrossingAllowed() {
        Range base = new Range(-3, 3);
        double delta = 4;
        boolean allowZeroCrossing = true;

        Range expected = new Range(1, 7);
        Range actual = Range.shift(base, delta, allowZeroCrossing);

        assertEquals(expected, actual);
    }

    @Test
    public void testShiftWithNegativeDeltaAndZeroCrossingAllowed() {
        Range base = new Range(-2, 4);
        double delta = -3;
        boolean allowZeroCrossing = true;

        Range expected = new Range(-5, 1);
        Range actual = Range.shift(base, delta, allowZeroCrossing);

        assertEquals(expected, actual);
    }

    @Test
    public void testShiftCase5() {
        Range base = new Range(0, 10);
        Range shifted = Range.shift(base, 5, false);
        Range expected = new Range(5, 10);
        assertEquals(expected, shifted);
    }

    @Test
    public void testShiftCase6() {
        Range base = new Range(-5, 5);
        Range shifted = Range.shift(base, 2, false);
        Range expected = new Range(-3, 5);
        assertEquals(expected, shifted);
    }

    @Test
    public void testShiftCase7() {
        Range base = new Range(-3, 3);
        Range shifted = Range.shift(base, 4, false);
        Range expected = new Range(0, 7);
        assertEquals(expected, shifted);
    }

    @Test
    public void testShiftCase8() {
        Range base = new Range(-2, 4);
        Range shifted = Range.shift(base, -3, false);
        Range expected = new Range(-5, 1);
        assertEquals(expected, shifted);
    }
}
 	   
}
