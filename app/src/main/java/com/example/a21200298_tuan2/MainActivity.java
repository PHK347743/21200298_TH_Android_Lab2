package com.example.a21200298_tuan2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    TextView displayInput, displayOutput, displayHistory;
    private Button btnClear, btnDelete, btnToggleSign, btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnDecimal;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initializeViews();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText("");
                displayOutput.setText("0");
                displayHistory.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer str = new StringBuffer(displayInput.getText());
                if(str.length() > 0){
                    str.deleteCharAt(str.length()-1);
                    displayInput.setText(str);
                    if(str.length() > 0) calculateResult(); else displayOutput.setText("0");
                }
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"0");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"1");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"2");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"3");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"4");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"5");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"6");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"7");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"8");
                calculateResult();
                displayHistory.setText("");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInput.setText(displayInput.getText()+"9");
                displayHistory.setText("");
                calculateResult();
            }
        });
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayInput.getText().length() > 0)
                    if(Character.isDigit(displayInput.getText().charAt(displayInput.getText().length()-1)))
                        displayInput.setText(displayInput.getText()+".");
            }
        });
        btnToggleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chức năng đổi dấu số
                if(displayInput.getText().length() > 0) {
                    String currentInput = displayInput.getText().toString();
                    if(currentInput.startsWith("-")) {
                        displayInput.setText(currentInput.substring(1));
                    } else {
                        displayInput.setText("-" + currentInput);
                    }
                    calculateResult();
                } else if(!displayOutput.getText().equals("0")) {
                    // Nếu không có input nhưng có kết quả, lấy kết quả và đổi dấu
                    String result = displayOutput.getText().toString();
                    if(result.startsWith("-")) {
                        displayInput.setText(result.substring(1));
                    } else {
                        displayInput.setText("-" + result);
                    }
                    calculateResult();
                }
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHistory.setText("");
                if(displayOutput.getText()!="0" && displayInput.getText()=="")
                {
                    displayInput.setText(displayOutput.getText()+"-");
                }
                if(displayInput.getText().length()==0) displayInput.setText(displayInput.getText()+"-");
                else if(Character.isDigit(displayInput.getText().charAt(displayInput.getText().length()-1)) || displayInput.getText().charAt(displayInput.getText().length()-1)=='/' || displayInput.getText().charAt(displayInput.getText().length()-1)=='x' )  displayInput.setText(displayInput.getText()+"-");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayInput.getText().length() >0) if(Character.isDigit(displayInput.getText().charAt(displayInput.getText().length()-1)) ) displayInput.setText(displayInput.getText()+"+");
                if(displayOutput.getText()!="0" && displayInput.getText()=="")
                {
                    displayInput.setText(displayOutput.getText()+"+");
                }
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayInput.getText().length() >0) if(Character.isDigit(displayInput.getText().charAt(displayInput.getText().length()-1)))  displayInput.setText(displayInput.getText()+"x");
                if(displayOutput.getText()!="0" && displayInput.getText()=="")
                {
                    displayInput.setText(displayOutput.getText()+"x");
                }
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayInput.getText().length() >0) if(Character.isDigit(displayInput.getText().charAt(displayInput.getText().length()-1)))  displayInput.setText(displayInput.getText()+"/");
                if(displayOutput.getText()!="0" && displayInput.getText()=="")
                {
                    displayInput.setText(displayOutput.getText()+"/");
                }
            }
        });
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayInput.getText().length() > 1 ) calculateResult();
                displayHistory.setText((String)displayInput.getText()+" =");
                displayInput.setText("");
            }
        });
    }
    private ArrayList<String> operationList;
    private ArrayList<Double> numberList;
    public int addOperation(String input) {
        operationList = new ArrayList<>();
        addNumber(this.displayInput.getText().toString());
        if(this.displayInput.getText().charAt(0)=='-') numberList.set(0,-numberList.get(0));
        char[] cArray = input.toCharArray();
        for (int i = 1; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    operationList.add(cArray[i] + "");
                    break;
                case '-':
                    operationList.add(cArray[i] + "");
                    break;
                case 'x':
                    operationList.add(cArray[i] + "");
                    if(i<cArray.length-1) if(cArray[i+1] =='-' && i+1 <cArray.length-1 )
                    {
                        numberList.set(operationList.size(),-numberList.get(operationList.size()));
                        i++;
                    }
                    break;
                case '/':
                    operationList.add(cArray[i] + "");
                    if(i<cArray.length-1) if(cArray[i+1] =='-' && i+1 <cArray.length-1 )
                    {
                        numberList.set(operationList.size(),-numberList.get(operationList.size()));
                        i++;
                    }
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput) {
        numberList = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            numberList.add(Double.valueOf(matcher.group(1)));
        }
    }
    public void calculateResult()
    {
        DecimalFormat df = new DecimalFormat("###.#######");
        double result = 0;
        addOperation(displayInput.getText().toString());
        if(numberList.size()==1)  result = numberList.get(0);
        if(operationList.size()>numberList.size() ||operationList.size()<0){
            displayOutput.setText("Input error!!");
        }else {
            for (int i = 0; i < (numberList.size() - 1); i++) {
                switch (operationList.get(i)) {
                    case "x":
                        numberList.set(i, numberList.get(i) * numberList.get(i + 1));
                        numberList.remove(i+1); operationList.remove(i);
                        i--;
                        break;
                    case "/":
                        numberList.set(i, numberList.get(i) / numberList.get(i + 1));
                        numberList.remove(i+1); operationList.remove(i);
                        i--;
                    default:
                        break;
                }
            }
            result = numberList.get(0);
            for (int i = 0; i < (numberList.size() - 1); i++) {
                switch (operationList.get(i)) {
                    case "+":
                        result = result + numberList.get(i + 1);
                        break;
                    case "-":
                        result = result - numberList.get(i + 1);
                        break;
                    default:
                        break;
                }
            }
            displayOutput.setText(df.format(result) + "");
        }
    }
    public void initializeViews()
    {
        displayInput = (TextView) findViewById(R.id.input);
        displayOutput = (TextView) findViewById(R.id.output);
        btnClear = (Button) findViewById(R.id.AC);
        btnDecimal = (Button)findViewById(R.id.thapphan);
        btnDelete = (Button) findViewById(R.id.Delete);
        btnToggleSign = (Button) findViewById(R.id.toggleSign);
        btnAdd = (Button) findViewById(R.id.daucong);
        btnSubtract = (Button) findViewById(R.id.dautru);
        btnMultiply = (Button) findViewById(R.id.phepnhan);
        btnDivide = (Button) findViewById(R.id.phepchia);
        btnEquals = (Button) findViewById(R.id.result);
        btn0 = (Button) findViewById(R.id.num0);
        btn1 = (Button) findViewById(R.id.num1);
        btn2 = (Button) findViewById(R.id.num2);
        btn3 = (Button) findViewById(R.id.num3);
        btn4 = (Button) findViewById(R.id.num4);
        btn5 = (Button) findViewById(R.id.num5);
        btn6 = (Button) findViewById(R.id.num6);
        btn7 = (Button) findViewById(R.id.num7);
        btn8 = (Button) findViewById(R.id.num8);
        btn9 = (Button) findViewById(R.id.num9);
        displayHistory = (TextView)findViewById(R.id.iptemp);
    }
}
