package com.watsonlogic.hellocalculator;

import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Before;
import org.junit.Test;
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
        int[] arr = {1, 2, 3};
        assertEquals(6, ma.addIntegers(arr));
    }
}
