package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class AdvancedCalc extends AppCompatActivity implements View.OnClickListener{

    private EditText display;
    private TextView formatError;
    private String temp = "";
    private boolean flag = false;
    private int click = 0;
    private static final int SIZE_OF_EDITTEXT = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_calc);

        display = findViewById(R.id.insert);
        display.setShowSoftInputOnFocus(false);

        formatError = findViewById(R.id.formatError);

        display.setOnClickListener(view -> {
            if(getString(R.string.display).equals(display.getText().toString())) {
                display.setText("");
            }
        });

        Button sinBTN = findViewById(R.id.sinBTN);
        Button cosBTN = findViewById(R.id.cosBTN);
        Button tanBTN = findViewById(R.id.tanBTN);
        Button lnBTN = findViewById(R.id.lnBTN);
        Button logBTN = findViewById(R.id.logBTN);
        Button percentBTN = findViewById(R.id.percentBTN);
        Button sqrtBTN = findViewById(R.id.sqrtBTN);
        Button squareBTN = findViewById(R.id.squareBTN);
        Button powerBTN = findViewById(R.id.powerBTN);

        Button clearBTN = findViewById(R.id.clearBTN);
        Button clearAllBTN = findViewById(R.id.clearAllBTN);
        Button parBTN = findViewById(R.id.parBTN);
        Button divideBTN = findViewById(R.id.divideBTN);
        Button sevenBTN = findViewById(R.id.sevenBTN);
        Button eightBTN = findViewById(R.id.eightBTN);
        Button nineBTN = findViewById(R.id.nineBTN);
        Button multiplyBTN = findViewById(R.id.multiplyBTN);
        Button fourBTN = findViewById(R.id.fourBTN);
        Button fiveBTN = findViewById(R.id.fiveBTN);
        Button sixBTN = findViewById(R.id.sixBTN);
        Button minusBTN = findViewById(R.id.minusBTN);
        Button oneBTN = findViewById(R.id.oneBTN);
        Button twoBTN = findViewById(R.id.twoBTN);
        Button threeBTN = findViewById(R.id.threeBTN);
        Button plusBTN = findViewById(R.id.plusBTN);
        Button plusMinusBTN = findViewById(R.id.plusMinusBTN);
        Button zeroBTN = findViewById(R.id.zeroBTN);
        Button dotBTN = findViewById(R.id.dotBTN);
        Button equalsBTN = findViewById(R.id.equalsBTN);

        sinBTN.setOnClickListener(this);
        cosBTN.setOnClickListener(this);
        tanBTN.setOnClickListener(this);
        lnBTN.setOnClickListener(this);
        logBTN.setOnClickListener(this);
        percentBTN.setOnClickListener(this);
        sqrtBTN.setOnClickListener(this);
        squareBTN.setOnClickListener(this);
        powerBTN.setOnClickListener(this);

        clearBTN.setOnClickListener(this);
        clearAllBTN.setOnClickListener(this);
        parBTN.setOnClickListener(this);
        divideBTN.setOnClickListener(this);
        sevenBTN.setOnClickListener(this);
        eightBTN.setOnClickListener(this);
        nineBTN.setOnClickListener(this);
        multiplyBTN.setOnClickListener(this);
        fourBTN.setOnClickListener(this);
        fiveBTN.setOnClickListener(this);
        sixBTN.setOnClickListener(this);
        minusBTN.setOnClickListener(this);
        oneBTN.setOnClickListener(this);
        twoBTN.setOnClickListener(this);
        threeBTN.setOnClickListener(this);
        plusBTN.setOnClickListener(this);
        plusMinusBTN.setOnClickListener(this);
        zeroBTN.setOnClickListener(this);
        dotBTN.setOnClickListener(this);
        equalsBTN.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        int cursorPos;
        int textLen;

        switch (view.getId()) {
            case R.id.clearBTN: // Backspace button
                click++;
                Handler handler = new Handler();
                Runnable r = () -> click = 0;

                if (click == 1) {
                    //Single click
                    cursorPos = display.getSelectionStart();
                    textLen = display.getText().length();

                    if(cursorPos != 0 && textLen != 0) {
                        SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                        selection.replace(cursorPos - 1, cursorPos, "");

                        display.setText(selection);
                        display.setSelection(cursorPos - 1);
                    }

                    handler.postDelayed(r, 250);
                } else if (click == 2) {
                    //Double click
                    click = 0;
                    display.setText("");
                }

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.clearAllBTN:
                display.setText("");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.parBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                cursorPos = display.getSelectionStart();
                int openPar = 0;
                int closedPar = 0;
                textLen = display.getText().length();

                for(int i = 0; i < cursorPos; i++) {
                    if(display.getText().toString().charAt(i) == '(')
                        openPar += 1;
                    if(display.getText().toString().charAt(i) == ')')
                        closedPar += 1;
                }
                if(openPar == closedPar || display.getText().toString().charAt(textLen - 1) == '(')
                    updateText("(");
                else if(closedPar < openPar && !(display.getText().toString().charAt(textLen - 1) == '('))
                    updateText(")");

                display.setSelection(cursorPos+1);

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.sinBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("sin(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.cosBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("cos(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.tanBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("tan(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.lnBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("ln(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.logBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("log10(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.percentBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("%");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.sqrtBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("sqrt(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.squareBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("^(2)");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.powerBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("^(");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.divideBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("÷");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.sevenBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("7");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.eightBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("8");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.nineBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("9");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.multiplyBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("×");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.fourBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("4");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.fiveBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("5");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.sixBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("6");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.minusBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("-");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.oneBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("1");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.twoBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("2");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.threeBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("3");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.plusBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("+");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.plusMinusBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("-");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.zeroBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText("0");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.dotBTN:
                if(display.length() == SIZE_OF_EDITTEXT)
                    break;
                updateText(".");

                setVisibilityToView();
                checkUIModeAndSetColor();
                checkFlag();
                break;
            case R.id.equalsBTN:
                String userExp = display.getText().toString();

                userExp = userExp.replaceAll("÷","/");
                userExp = userExp.replaceAll("×","*");

                Expression expression = new Expression(userExp);

                String result = String.valueOf(expression.calculate());
                System.out.println(result);

                if(!result.equals("NaN")) {
                    display.setText(result);
                    display.setSelection(result.length());
                }
                else {
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        formatError.setVisibility(View.VISIBLE);
                        display.setTextColor(Color.parseColor("#FF0000"));
                    }
                    else {
                        formatError.setVisibility(View.VISIBLE);
                        //display.setVisibility(View.INVISIBLE);
                        temp = display.getText().toString();
                        System.out.println(temp);
                        display.setText("");
                        flag = true;
                    }
                }
                break;
        }

    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();

        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    private void setVisibilityToView() {
        formatError.setVisibility(View.INVISIBLE);
        display.setVisibility(View.VISIBLE);
    }

    private void checkUIModeAndSetColor() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES)
            display.setTextColor(Color.parseColor("#FFFFFF"));
        else if(nightModeFlags == Configuration.UI_MODE_NIGHT_NO)
            display.setTextColor(Color.parseColor("#000000"));
    }

    private void checkFlag() {
        if(flag) {
            display.setText(temp);
            display.setSelection(temp.length());
            flag = false;
        }
    }
}
