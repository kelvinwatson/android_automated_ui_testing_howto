package com.watsonlogic.hellocalculator;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HelloCalculatorRobolectricTest {
    private MainActivity mainActivity;
    private TextView answer;
    private Button zero,one,two,three,four,five,six,seven,eight,nine,plus,equals;
    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        answer = (TextView) mainActivity.findViewById(R.id.answerView);
        zero=(Button)mainActivity.findViewById(R.id.zeroBtn);
        one=(Button)mainActivity.findViewById(R.id.oneBtn);
        two=(Button)mainActivity.findViewById(R.id.twoBtn);
        three=(Button)mainActivity.findViewById(R.id.threeBtn);
        four=(Button)mainActivity.findViewById(R.id.fourBtn);
        five=(Button)mainActivity.findViewById(R.id.fiveBtn);
        six=(Button)mainActivity.findViewById(R.id.sixBtn);
        seven=(Button)mainActivity.findViewById(R.id.sevenBtn);
        eight=(Button)mainActivity.findViewById(R.id.eightBtn);
        nine=(Button)mainActivity.findViewById(R.id.nineBtn);
        plus=(Button)mainActivity.findViewById(R.id.plusBtn);
        equals=(Button)mainActivity.findViewById(R.id.equalsBtn);
        answer=(TextView)mainActivity.findViewById(R.id.answerView);
    }

    // The test simply checks that our TextView exists and has the text "Testing Application"
    @Test
    public void validateTextViewContent() {
        TextView testingApplicationTV = (TextView) mainActivity.findViewById(R.id.testingApplicationTV);
        assertNotNull("TextView could not be found", testingApplicationTV);
        assertTrue("TextView contains incorrect text",
                "Testing Application".equals(testingApplicationTV.getText().toString()));
    }

    @Test
    public void validateNumberButtons() {
        zero.performClick();
        assertThat(answer.getText().toString(), equalTo("0"));

        one.performClick();
        assertThat(answer.getText().toString(), equalTo("1"));

        two.performClick();
        assertThat(answer.getText().toString(), equalTo("12"));

        three.performClick();
        assertThat(answer.getText().toString(), equalTo("123"));

        four.performClick();
        assertThat(answer.getText().toString(), equalTo("1234"));

        five.performClick();
        assertThat(answer.getText().toString(), equalTo("12345"));

        six.performClick();
        assertThat(answer.getText().toString(), equalTo("123456"));

        seven.performClick();
        assertThat(answer.getText().toString(), equalTo("1234567"));

        eight.performClick();
        assertThat(answer.getText().toString(), equalTo("12345678"));

        nine.performClick();
        assertThat(answer.getText().toString(), equalTo("123456789"));

        plus.performClick();
        assertThat(answer.getText().toString(), equalTo("+"));

        equals.performClick();
        assertThat(answer.getText().toString(), equalTo("="));

    }

    @Test
    public void validateAddition(){
        //5+8=13
        five.performClick();
        plus.performClick();
        eight.performClick();
        equals.performClick();
        assertThat(answer.getText().toString(), equalTo("13"));

        //39+172=211
        three.performClick();
        nine.performClick();
        plus.performClick();
        one.performClick();
        seven.performClick();
        two.performClick();
        equals.performClick();
        assertThat(answer.getText().toString(), equalTo("211"));

        //+578=578
        plus.performClick();
        five.performClick();
        seven.performClick();
        eight.performClick();
        assertThat(answer.getText().toString(), equalTo("578"));
    }

    @Test
    public void validateOddSequences(){
        //++
        equals.performClick();
        equals.performClick();
        assertThat(answer.getText().toString(), anyOf(containsString("+"), containsString("err")));

        //==
        equals.performClick();
        equals.performClick();
        assertThat(answer.getText().toString(), anyOf(containsString("="), containsString("err")));

        //=+
        equals.performClick();
        plus.performClick();
        assertThat(answer.getText().toString(), anyOf(containsString("+"), containsString("="), containsString("err")));

        //+=
        plus.performClick();
        equals.performClick();
        assertThat(answer.getText().toString(), anyOf(containsString("+"), containsString("="), containsString("err")));

        //=23+
        equals.performClick();
        two.performClick();
        three.performClick();
        assertThat(answer.getText().toString(), anyOf(containsString("23"), containsString("+"), containsString("err")));

    }
}
