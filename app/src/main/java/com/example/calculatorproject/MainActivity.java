package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private TextView display2;

    double val1,val2;
    boolean addCheck,subCheck,mulCheck,divCheck,modCheck,expCheck;

    boolean check;

    public boolean initial(){
        if(display.getText().toString().equals("")) return true;
        if(display.getText().toString().equals(getString(R.string.display))) return true;
        return false;
    }

    public boolean checkNeg(){
        if(display.getText().toString().equals("-")) return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.input);
        display2=findViewById(R.id.input2);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(initial())
                    display.setText("");
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart(); // way to get cursor position

        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if(initial()) {
            display.setText(strToAdd);
        }else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + 1);
    }

    public void zeroBTN(View v){
        updateText("0");
    }
    public void oneBTN(View v){
        updateText("1");
    }
    public void twoBTN(View v){
        updateText("2");
    }
    public void threeBTN(View v){
        updateText("3");
    }
    public void fourBTN(View v){
        updateText("4");
    }
    public void fiveBTN(View v){
        updateText("5");
    }
    public void sixBTN(View v){
        updateText("6");
    }
    public void sevenBTN(View v){
        updateText("7");
    }
    public void eightBTN(View v){
        updateText("8");
    }
    public void nineBTN(View v){
        updateText("9");
    }

    public void backspaceBTN(View v){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder)display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    public void clearBTN(View v){
        display.setText("");
        display2.setText("");
        check=false;
    }

    public void pointBTN(View v){
        updateText(".");
    }

    public void addBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
        if(initial() || check) return;
        if(temp!="" && !temp.equals(R.string.display)){
            addCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" + ");
            display.setText("");
        }
    }
    public void subtractBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
//        if(initial() || check) return;

        if(initial()){
            updateText("-");
        }
        else if(temp!="" && !temp.equals(R.string.display)){
            subCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" - ");
            display.setText("");
        }
    }
    public void divideBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
        if(initial() || check) return;
        if(temp!="" && !temp.equals(R.string.display)){
            divCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" / ");
            display.setText("");
        }
    }
    public void multiplyBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
        if(initial() || check) return;
        if(temp!="" && !temp.equals(R.string.display)){
            mulCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" * ");
            display.setText("");
        }
    }

    public void modBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
        if(initial() || check) return;
        if(temp!="" && !temp.equals(R.string.display)){
            modCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" % ");
            display.setText("");
        }
    }

    public void exponentBTN(View v){
        String temp = display.getText().toString();
        if(checkNeg()) return;
        if(initial() || check) return;
        if(temp!="" && !temp.equals(R.string.display)){
            expCheck=true;check=true;
            val1=Float.parseFloat(temp);
            display2.setText(display.getText()+" ^ ");
            display.setText("");
        }
    }

    public void sqrtBTN(View v){
        String temp=display.getText().toString();
        if(checkNeg() || initial() || check) return;

        val1=Float.parseFloat(display.getText().toString());
        double res=Math.sqrt(val1);
        display.setText("");
        updateText(res+"");

    }

    public void cubrtBTN(View v){
        String temp=display.getText().toString();
        if(checkNeg() || initial() || check) return;

        val1=Float.parseFloat(display.getText().toString());
        double res=Math.cbrt(val1);
        display.setText("");
        updateText(res+"");

    }

    public void equalBTN(View v){

            if(initial() || checkNeg()) return;

            val2 = Float.parseFloat(display.getText() + "");
            double res=0;
            if (addCheck == true) {
                res = val1 + val2;
                addCheck = false;
            }
            else if (subCheck == true) {
                res = val1 - val2;
                subCheck = false;
            }
            else if (mulCheck == true) {
                res = val1 * val2;
                mulCheck = false;
            }
            else if (divCheck == true) {
                res = val1 / val2;
                divCheck = false;
            }
            else if(expCheck == true){
                res= Math.pow(val1, val2);
                expCheck=false;
            }
            else if(modCheck == true){
                res = val1%val2;
                modCheck=false;
            }

            if(check) {
                display.setText("");
                updateText(res+"");
            }
            check=false;
            display.setSelection(display.getText().length());
            display2.setText("");
    }

}