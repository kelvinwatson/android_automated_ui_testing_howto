package com.watsonlogic.hellocalculator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloCalculatorEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    String[] nums;
    Button one, two, three, four, five, six, seven, eight, nine;
    Button plus, equals;
    Button[] numbers;
    TextView answer;
    String expr;
    String prev = null;
    boolean answerIsShown = false;

    @Before
    public void setUp() throws Exception {
        setButtonsArr();
    }

    public void setButtonsArr() {
        numbers = new Button[]{one, two, three, four, five, six, seven, eight, nine, plus, equals};
        nums = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }

    @Test
    public void requiredViews() {
        onView(withText("Testing Application")).check(matches(isDisplayed()));
    }

    @Test
    public void checkToastButton() {
        onView(withId(R.id.toastBtn)).perform(click());
    }

    @Test
    public void checkNumberButtons() {
        onView(withId(R.id.zeroBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("0")));
        onView(withId(R.id.oneBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("1")));
        onView(withId(R.id.twoBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("12")));
        onView(withId(R.id.threeBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("123")));
        onView(withId(R.id.fourBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("1234")));
        onView(withId(R.id.fiveBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("12345")));
        onView(withId(R.id.sixBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("123456")));
        onView(withId(R.id.sevenBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("1234567")));
        onView(withId(R.id.eightBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("12345678")));
        onView(withId(R.id.nineBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("123456789")));
    }

    @Test
    public void checkNonNumberButtons() {
        onView(withId(R.id.plusBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("+")));
        onView(withId(R.id.equalsBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("=")));
    }

    @Test
    public void checkAddition() {
        //5+8=13
        onView(withId(R.id.fiveBtn)).perform(click());
        onView(withId(R.id.plusBtn)).perform(click());
        onView(withId(R.id.eightBtn)).perform(click());
        onView(withId(R.id.equalsBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("13")));
        //39+172=211
        onView(withId(R.id.threeBtn)).perform(click());
        onView(withId(R.id.nineBtn)).perform(click());
        onView(withId(R.id.plusBtn)).perform(click());
        onView(withId(R.id.oneBtn)).perform(click());
        onView(withId(R.id.sevenBtn)).perform(click());
        onView(withId(R.id.twoBtn)).perform(click());
        onView(withId(R.id.equalsBtn)).perform(click());
        onView(withId(R.id.answerView)).check(matches(withText("211")));
    }
}