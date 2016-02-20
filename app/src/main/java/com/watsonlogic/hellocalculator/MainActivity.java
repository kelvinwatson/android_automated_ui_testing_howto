package com.watsonlogic.hellocalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine;
    Button plus, equals;
    Button[] numbers;
    TextView answer;
    String expr;
    String prev=null;
    boolean answerIsShown=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button toastBtn = (Button) findViewById(R.id.toastBtn);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Toast Button!", Toast.LENGTH_SHORT).show();
            }
        });
        getCalculatorReferences();
        setCalculatorArr();
        setButtonListeners();
    }

    protected void getCalculatorReferences(){
        one=(Button)findViewById(R.id.oneBtn);
        two=(Button)findViewById(R.id.twoBtn);
        three=(Button)findViewById(R.id.threeBtn);
        four=(Button)findViewById(R.id.fourBtn);
        five=(Button)findViewById(R.id.fiveBtn);
        six=(Button)findViewById(R.id.sixBtn);
        seven=(Button)findViewById(R.id.sevenBtn);
        eight=(Button)findViewById(R.id.eightBtn);
        nine=(Button)findViewById(R.id.nineBtn);
        plus=(Button)findViewById(R.id.plusBtn);
        equals=(Button)findViewById(R.id.equalsBtn);
        answer=(TextView)findViewById(R.id.answerView);
    }

    protected void setCalculatorArr(){
        numbers = new Button[]{one,two,three,four,five,six,seven,eight,nine,plus,equals};
    }
    protected void setButtonListeners(){
        for(int k=0; k<numbers.length; k++){
            final Button b = numbers[k];
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String i = b.getText().toString();
                    if(answerIsShown){
                        setDisplay("");
                        answerIsShown=false;
                    }
                    String a = answer.getText().toString();
                    String ai=null;
                    try{
                        Integer.parseInt(a); //if numeric, append to display
                        ai=a+i;
                    }catch(NumberFormatException e){ //if not numeric, show current char
                        ai=i;
                    }
                    answer.setText(ai);
                    calculate(i);
                }
            });
        }
    }


    protected void calculate(String pressed){
        if(pressed.equals("=")) {
            //Log.d("MAIN","pressed equals");
            processEquals();
        } else if (pressed.equals("+")){
            //Log.d("MAIN","pressed plus");
            processPlus();
        } else{
            //Log.d("MAIN","pressed num");
            processNumeric(pressed);
        }
    }

    private void processEquals(){
        try{
            if(prev.matches("\\d") && !prev.equals("=") && !prev.equals("+")) {
                List<Integer> ints = generateIntArr(expr);
                prev=expr=null;
                int result = addIntegers(ints);
                setDisplay(String.valueOf(result));
                answerIsShown=true;
            }
        } catch(NullPointerException e){
            setDisplay("err");
        }
    }

    private void processPlus(){
        if(prev==null){
            expr+="0+";
            prev="+";
        } else if(prev.matches("\\d") && !prev.equals("=") && !prev.equals("+")) {
            expr += "+"; //append the plus sign
            prev="+";
        } else{
            setDisplay("err");
        }
    }

    private void processNumeric(String n){
        if(prev==null || !prev.equals("=")){
            expr = (expr == null) ? n : expr + n;
            prev=n;
        }
    }

    protected List<Integer> generateIntArr(String expr){
        List<Integer> ints = new ArrayList<Integer>();
        String pend = null;
        for(int k=0, len=expr.length(); k<len; k++){
            char c = expr.charAt(k);
            char next = 'a';
            if((k+1)<len) {
                next = expr.charAt(k + 1);
            }
            if(Character.isDigit(c) && Character.isDigit(next)){ //multi-digit number
                pend = (pend==null)? String.valueOf(c) : pend+c;
            } else if(Character.isDigit(c) && !Character.isDigit(next)) { //single digit or end of multidigit
                if (pend == null) { //single digit
                    ints.add(Character.getNumericValue(c));
                }else{ //multidigit
                    pend+=c;
                    ints.add(Integer.parseInt(pend));
                    pend=null;
                }
            }
        }
        return ints;
    }

    protected void setDisplay(String s){
        answer.setText(s);
    }

    protected int addIntegers(List<Integer> arr){
        int result=0;
        for(int i=0,len=arr.size(); i<len; ++i){
            result+=arr.get(i);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
