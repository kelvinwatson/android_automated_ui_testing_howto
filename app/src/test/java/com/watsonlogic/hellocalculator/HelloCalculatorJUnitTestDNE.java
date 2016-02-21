package com.watsonlogic.hellocalculator;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * JUnit test that does NOT invoke emulator
 */
@SmallTest
public final class HelloCalculatorJUnitTestDNE {

    private MainActivity ma;

    @Before
    public void setUp() throws Exception {
        ma = new MainActivity();
    }

    @Test
    public void testAddIntegers() {
        List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(6, ma.addIntegers(arr));
    }
}
