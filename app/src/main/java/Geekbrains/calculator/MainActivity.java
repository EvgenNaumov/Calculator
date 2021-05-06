package Geekbrains.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.radiobutton.MaterialRadioButton;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity<listOperation> extends AppCompatActivity {

    private static final String PARAM = "PARAM";
    private static final String PARAM_Text1 = "";
    private static final String PARAM_TextTotal = "";

    //имя настроек приложения
    public static final String NameSharedPref = "CALC";

    public static final String appTheme = "APP_THEME";

    private static final int AppThemeLight  = 0;
    private static final int AppThemeDark   = 1;
    private static final int AppThemeDef    = 2;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonAdd;
    private Button buttonReduce;
    private Button buttonDivide;
    private Button buttonMult;
    private Button buttonEqual;
    private Button buttonCancel;
    private Button buttonPercent;
    private Button buttonDot;
    private Button buttonComma;
    private Button buttonMark;
    private Button buttonDel;

    private InitMain initMain;
    private MainLogic mainLogic;
    private ListOperation listOperation = ListOperation.getInstance();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARAM, listOperation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listOperation = savedInstanceState.getParcelable(PARAM);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.AppThemeLight));
        setContentView(R.layout.activity_main);

        initView();
        initThemeChoser();

        setParamDisplay();
    }

    private void setParamDisplay(){
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        mainLogic.calculation(textView1,textView2,false);
    }


    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private void setAppTheme(int codeStyle){
        SharedPreferences sharedPreferences = getSharedPreferences(NameSharedPref,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(appTheme,codeStyle);
        editor.apply();

    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(NameSharedPref,MODE_PRIVATE);
        return sharedPreferences.getInt(appTheme,codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle){
        switch (codeStyle){
            case AppThemeLight:
                    return R.style.AppThemeLight;
            case AppThemeDark:
                return R.style.AppThemeDark;
            default:
                return R.style.AppThemeDefault;
        }
    }

    private void initThemeChoser() {
        iniRAdioButton(findViewById(R.id.radiobutton1),AppThemeLight);
        iniRAdioButton(findViewById(R.id.radiobutton2),AppThemeDark);
        RadioGroup rg = findViewById(R.id.radio_group);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(AppThemeLight))).setChecked(true);
    }

    private void iniRAdioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAppTheme(codeStyle);
                Toast.makeText(getApplicationContext(),"codeStyle " + Integer.toString(codeStyle),Toast.LENGTH_LONG).show();
                recreate();
            }
        });
    }

    private void initView() {
        final TextView textView1 = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);
        mainLogic = new MainLogic();

        button0 = findViewById(R.id.button0);
//        InitView.addView(button0,textView1);
        button0.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button0.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button9 = findViewById(R.id.button9);
//        InitView.addView(button9,textView1);
        button9.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button9.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button8 = findViewById(R.id.button8);
//        InitView.addView(button9,textView1);
        button8.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button8.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button7 = findViewById(R.id.button7);
//        InitView.addView(button7,textView1);
        button7.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button7.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button6 = findViewById(R.id.button6);
//        InitView.addView(button6,textView1);
        button6.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button6.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button5 = findViewById(R.id.button5);
//        InitView.addView(button5,textView1);
        button5.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button5.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button4 = findViewById(R.id.button4);
//        InitView.addView(button4,textView1);
        button4.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button4.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button3 = findViewById(R.id.button3);
//        InitView.addView(button3,textView1);
        button3.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button3.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button2 = findViewById(R.id.button2);
//        InitView.addView(button2,textView1);
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button2.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        button1 = findViewById(R.id.button1);
//        InitView.addView(button1,textView1);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = button1.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        buttonPercent = findViewById(R.id.percent_button);
//        InitView.addView(buttonPercent,textView1);
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonPercent.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        buttonComma = findViewById(R.id.CommaButton);
//        InitView.addView(buttonComma,textView1);
        buttonComma.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonComma.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                }
            }
        });

        buttonMult = findViewById(R.id.multiply_button);
//        InitView.addView(buttonMult,textView1);
        buttonMult.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonMult.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                }
            }
        });

        buttonReduce = findViewById(R.id.reduce_button);
//        InitView.addView(buttonReduce,textView1);
        buttonReduce.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonReduce.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay( textView1);
                }
            }
        });

        buttonAdd = findViewById(R.id.add_button);
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonAdd.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay( textView1);
                }
            }
        });

        buttonEqual = findViewById(R.id.equal_buttom);
//        InitView.addView(buttonEqual,textView1);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonEqual.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,true);
                }
            }
        });

        buttonCancel = findViewById(R.id.cancel_button);
//        InitView.addView(buttonCancel,textView1);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonCancel.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,true);
                }
            }
        });

        buttonDivide = findViewById(R.id.divide_button);
//        InitView.addView(buttonDivide,textView1);
        buttonDivide.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonDivide.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay( textView1);
                }
            }
        });

        buttonMark = findViewById(R.id.mark_button);
//        InitView.addView(buttonMark,textView1);
        buttonMark.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonMark.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });

        buttonDel = findViewById(R.id.del_buttom);
//        InitView.addView(buttonDel,textView1);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String buttonText = buttonDel.getText().toString();
                String showText = textView1.getText().toString();
                if (mainLogic.canAddtoList(buttonText,showText)) {
                    mainLogic.addOperationToList(buttonText);
                    mainLogic.textShowOnDisplay(textView1);
                    mainLogic.calculation(textView1,textView2,false);
                }
            }
        });
    }


}