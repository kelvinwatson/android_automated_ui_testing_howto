package com.watsonlogic.hellocalculator;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

public class HelloCalculatorJUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity ma;

    public HelloCalculatorJUnitTest() {
        super("com.watsonlogic.hellocalculator", MainActivity.class);
    }

    public HelloCalculatorJUnitTest(Class<MainActivity> activityClass, MainActivity ma) {
        super(activityClass);
        this.ma = ma;
    }

    public HelloCalculatorJUnitTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ma = getActivity();
    }

    @Test
    public void testAddIntegers() {
        int[] arr = {1, 2, 3};
        assertEquals(6, ma.addIntegers(arr));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


}
